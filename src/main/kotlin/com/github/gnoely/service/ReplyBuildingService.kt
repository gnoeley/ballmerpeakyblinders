package com.github.gnoely.service

import com.github.gnoely.messagegenerator.MessageGenerator
import com.github.gnoely.model.Reply
import com.github.gnoely.repository.OrderRepository
import com.github.gnoely.repository.TwitterUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReplyBuildingService {

    @Autowired lateinit var recommendationService : ProductRecommendationService

    @Autowired lateinit var twitterUserRepo: TwitterUserRepository
    @Autowired lateinit var orderService: OrderService

    fun buildReply(twitterHandle: String, ingredients: List<String>) : Reply {

        var yummlyIngredients = if (ingredients.isEmpty()) findRecommendedIngredients(twitterHandle) else ingredients
        val recipe = RecipeService.getFirstIncludingIngredients(yummlyIngredients)
        return MessageGenerator.generateMessage(recipe, yummlyIngredients)
    }

    fun findRecommendedIngredients(twitterHandle: String): List<String> {
        val userId = twitterUserRepo.findOne(twitterHandle)
        println("RECOMMENDING FOR $userId")
        val recommendedProducts = recommendationService.recommendProducts(userId.userId, 5)
        return recommendedProducts.filter { it.isIgtMatch }.flatMap { it.rawMatches.split(",") }
    }

}
