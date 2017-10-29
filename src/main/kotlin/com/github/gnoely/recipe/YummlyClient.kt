package com.github.gnoely.recipe

import com.github.gnoely.model.Cuisine
import com.kaloer.yummly.Yummly
import com.kaloer.yummly.models.Recipe
import com.kaloer.yummly.models.SearchResult
import java.util.*

object YummlyClient {


    val yummly = Yummly("87489517", "bafc256f1e442c00e57753cdd653636d")


    fun searchForRecipeIncluding(query: String) : Recipe {
        val searchResult: SearchResult = yummly.search(query)
        return searchResult.matches[0]
    }

    fun searchForRecipeIncluding(query: String, ingredients: List<String>) : Optional<Recipe> {
        val searchResult: SearchResult = yummly.search(query, ingredients)
        return getRecipes(searchResult)
    }

    fun searchForRecipeWithCuisine(query: String, ingredients: List<String>, cuisine: Cuisine): Optional<Recipe> {
        if (Cuisine.NONE.equals(cuisine)) {
            return searchForRecipeIncluding(query, ingredients)
        } else {
            val searchResult: SearchResult = yummly.searchWithCuisine(query, ingredients, cuisine.searchTerm)
            return getRecipes(searchResult)
        }
    }

    private fun getRecipes(searchResult: SearchResult): Optional<Recipe> {
        val recipes = searchResult.matches
        if (recipes.isEmpty()) {
            return Optional.empty()
        }
        val partialRecipe = recipes[0]
        return Optional.of(yummly.getRecipe(partialRecipe.id))
    }

}


