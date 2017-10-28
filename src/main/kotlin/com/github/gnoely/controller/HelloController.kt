package com.github.gnoely.controller

import com.github.gnoely.twitter.TwitterIn
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {

    @Autowired internal lateinit var twitterIn: TwitterIn


    @GetMapping("/hello")
    fun sayHello(): String {
        return java.lang.String.valueOf("hello")
    }



    @GetMapping("/connect")
    fun connect(): String {
        twitterIn.connect()
        return java.lang.String.valueOf("connected")
    }



}
