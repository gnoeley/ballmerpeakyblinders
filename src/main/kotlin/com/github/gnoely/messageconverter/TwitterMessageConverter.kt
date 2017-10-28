package com.github.gnoely.messageconverter


object TwitterMessageConverter {

    fun convertMessageToQuery(handle: String, message: String) : String {
        return everythingAfterHandle(handle, message)
    }

    private fun everythingAfterHandle(handle: String, message: String) : String {
        return convertSpaces(message.split(handle + " ")[1])
    }

    private fun convertSpaces(query: String) : String {
        return query.replace(" ", "+")
    }

}

fun main(args: Array<String>) {
    val message = "Hello there HackManchester2017 I'd like some chicken tonight"
    println(TwitterMessageConverter.convertMessageToQuery("HackManchester2017", message))
}
