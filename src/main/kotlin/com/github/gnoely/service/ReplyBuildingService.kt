package com.github.gnoely.service

import com.github.gnoely.messageconverter.TwitterMessageConverter
import com.github.gnoely.messagegenerator.MessageGenerator
import com.github.gnoely.model.Reply
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReplyBuildingService {

    @Autowired lateinit var twitterMessageConverter : TwitterMessageConverter

    fun buildReply(message: String) : Reply {
        val query = twitterMessageConverter.convertMessageToQuery(message)
        val ingredients = getIngredients()
        val recipe = RecipeService.getFirstRecipeForQueryIncluding(query, ingredients)
        return MessageGenerator.generateMessage(recipe, ingredients)
    }

    private fun getIngredients(): List<String> {
        // hard coded for now...want to call out to DB to get these
        return arrayListOf()
    }

}