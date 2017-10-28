package com.github.gnoely.recipe

import com.kaloer.yummly.Yummly

object YummlyClient {


    val yummly = Yummly("87489517", "bafc256f1e442c00e57753cdd653636d")


    fun getRecipes(ingredient : String) : Any {

        return yummly.search(ingredient)
    }

}


