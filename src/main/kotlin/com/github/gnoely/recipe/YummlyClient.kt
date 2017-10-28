package com.github.gnoely.recipe

import com.kaloer.yummly.Yummly
import com.kaloer.yummly.models.Recipe
import com.kaloer.yummly.models.SearchResult

object YummlyClient {


    val yummly = Yummly("87489517", "bafc256f1e442c00e57753cdd653636d")


    fun getRecipe(ingredient : String) : Recipe {
        val searchResult: SearchResult = yummly.search(ingredient)
        return searchResult.matches[0]
    }

}


