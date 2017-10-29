package com.github.gnoely.service

import com.github.gnoely.model.Product
import com.github.gnoely.repository.ProductRepository
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender
import org.apache.mahout.cf.taste.similarity.UserSimilarity
import org.springframework.stereotype.Service
import java.io.File
import java.util.*

@Service
class ProductRecommendationService(val productRepo: ProductRepository) {

    private val recommender: UserBasedRecommender

    init {
        val userProductHistoryUrl = this.javaClass.getResource("/rec_training.csv")
        val userProductHistoryFile = userProductHistoryUrl?.let { File(it.toURI()) }

        val model = FileDataModel(userProductHistoryFile)

        val similarity: UserSimilarity = PearsonCorrelationSimilarity(model)

        val neighborhood = ThresholdUserNeighborhood(1.0, similarity, model)

        recommender = GenericUserBasedRecommender(model, neighborhood, similarity)
    }

    fun recommendProducts(userId: String, num: Int): Set<Product> {
        val userIdAsLong = userId.toLong()
        val productIds = recommender.recommend(userIdAsLong, num).map { it.itemID.toString() }
        return productRepo.findAll(productIds).toHashSet()
    }

}
