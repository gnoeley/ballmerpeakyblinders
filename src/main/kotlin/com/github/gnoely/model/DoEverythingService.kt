package com.github.gnoely.model

import com.github.gnoely.messageconverter.TwitterMessageConverter
import com.github.gnoely.messagegenerator.MessageGenerator
import com.github.gnoely.recipe.RecipeService

object DoEverythingService {

    fun doEverything(handle: String, message: String) : String {
        val query = TwitterMessageConverter.convertMessageToQuery(handle, message)
        val ingredients = getIngredients()
        val recipe = RecipeService.getFirstRecipeForQueryIncluding(query, ingredients)
        return MessageGenerator.generateMessage(recipe, ingredients)
    }

    private fun getIngredients(): List<String> {
        // hard coded for now...want to call out to DB to get these
        return arrayListOf("chicken", "naan bread")
    }

}

fun main(args: Array<String>) {
    val message = DoEverythingService.doEverything("HackManchester2017", "Hello HackManchester2017 curry")
    println(message)
}
