package com.github.gnoely.service

import com.github.gnoely.recipe.YummlyClient
import com.kaloer.yummly.models.Recipe
import java.util.*


object RecipeService {

    fun getFirstRecipeForQuery(query: String) : Recipe {
        return YummlyClient.searchForRecipeIncluding(query)
    }

    fun getFirstIncludingIngredients(ingredients: List<String>): Optional<Recipe> {
        return YummlyClient.searchForRecipeIncluding("", ingredients)
    }


}
