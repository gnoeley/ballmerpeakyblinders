package com.github.gnoely.messageconverter

import com.github.gnoely.twitter.TwitterConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Component
object TwitterMessageConverter {

    @Autowired lateinit var twitterConfig : TwitterConfiguration


    fun convertMessageToQuery(message: String) : String {
        return everythingAfterKeyword(twitterConfig.getListenFor(), message)
    }

    private fun everythingAfterKeyword(handle: String, message: String) : String {
        return convertSpaces(message.split(handle + " ")[1])
    }

    private fun convertSpaces(query: String) : String {
        return query.replace(" ", "+")
    }

}

fun main(args: Array<String>) {
    val message = "Hello there HackManchester2017 I'd like some chicken tonight"
    println(TwitterMessageConverter.convertMessageToQuery(message))
}
