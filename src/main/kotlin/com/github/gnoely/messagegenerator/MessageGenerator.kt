package com.github.gnoely.messagegenerator

import com.github.gnoely.model.Reply
import com.kaloer.yummly.models.Attribution
import com.kaloer.yummly.models.Recipe

object MessageGenerator {

    fun generateMessage(recipe: Recipe, ingredients: List<String>): Reply {
        val url = recipe.attribution.url
        val ingredientsString = getIngredientsString(arrayListOf())
        val message = "$ingredientsString ${recipe.name} $url"
        val images = recipe.images
        val imageUrl = if (images != null) images[0].hostedSmallUrl else null
        return Reply(message, imageUrl)
    }

    fun getIngredientsString(ingredients: List<String>) : String {
        val size = ingredients.size
        if (size == 0) return "We recommend"
        val preamble = "It looks like you buy a lot of "
        val ending = ". Based on this we recommend"
        if (size == 1) {
            return preamble + ingredients[0] + ending
        } else {
            var string = preamble
            for (i in ingredients.indices) {
                if (i < size - 2) {
                    string += "${ingredients[i]}, "
                }
                if (i == size - 2) {
                    string += "${ingredients[i]} "
                }
                if (i == size - 1) {
                    string += "and ${ingredients[i]}"
                }
            }
            return string + ending
        }
    }

}

fun main(args: Array<String>) {
    val recipe = Recipe()
    val attribution = Attribution()
    attribution.url = "recipe.com"
    recipe.attribution = attribution
    recipe.setRecipeName("Awesome chicken curry")

    val message = MessageGenerator.generateMessage(recipe, arrayListOf("chicken", "milk", "naan bread"))
    println(message)
}

