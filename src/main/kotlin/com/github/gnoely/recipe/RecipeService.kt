package com.github.gnoely.recipe

import com.kaloer.yummly.models.Recipe


object RecipeService {

    fun getFirstRecipeForQuery(query: String) : Recipe {
        return YummlyClient.searchForRecipeIncluding(query)
    }

    fun getFirstRecipeForQueryIncluding(query: String, ingredients: List<String>): Recipe {
        return YummlyClient.searchForRecipeIncluding(query, ingredients)
    }


}