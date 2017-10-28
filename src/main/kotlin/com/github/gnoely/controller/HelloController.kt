package com.github.gnoely.controller

import com.github.gnoely.twitter.TwitterClient
import com.github.gnoely.twitter.TwitterConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @Autowired internal lateinit var twitterClient: TwitterClient


    @GetMapping("/hello")
    fun sayHello(): String {
        return java.lang.String.valueOf("hello")
    }



    @GetMapping("/connect")
    fun connect(): String {
        twitterClient.connect()
        return java.lang.String.valueOf("connected")
    }



}