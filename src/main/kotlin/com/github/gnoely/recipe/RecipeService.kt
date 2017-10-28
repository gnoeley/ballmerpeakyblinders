package com.github.gnoely.recipe

import com.kaloer.yummly.models.Recipe


object RecipeService {

    fun getFirstRecipeForIngredient(ingredient: String) : Recipe {
        return YummlyClient.getRecipe(ingredient)
    }


}