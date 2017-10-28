package com.github.gnoely.recipe

import com.kaloer.yummly.Yummly
import com.kaloer.yummly.models.Recipe
import com.kaloer.yummly.models.SearchResult

object YummlyClient {


    val yummly = Yummly("87489517", "bafc256f1e442c00e57753cdd653636d")


    fun searchForRecipeIncluding(query: String) : Recipe {
        val searchResult: SearchResult = yummly.search(query)
        return searchResult.matches[0]
    }

    fun searchForRecipeIncluding(query: String, ingredients: List<String>) : Recipe {
        val searchResult: SearchResult = yummly.search(query, ingredients)
        return searchResult.matches[0]
    }

}


