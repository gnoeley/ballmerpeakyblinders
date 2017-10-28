package com.github.gnoely.recipe

import org.junit.Test




class YummlyClientTest {

    @Test
    fun testGetRecipe() {
        val recipes = YummlyClient.getRecipes("tomato")


        println(recipes)
    }

}