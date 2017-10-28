package com.github.gnoely.messageconverter


object TwitterMessageConverter {




    val handle = "@BallmerPeakyB "

    fun convertMessageToQuery(message: String) : String {
        return everythingAfterHandle(message)
    }

    private fun everythingAfterHandle(message: String) : String {
        return convertSpaces(message.split(handle)[1])
    }

    private fun convertSpaces(query: String) : String {
        return query.replace(" ", "+")
    }

}

fun main(args: Array<String>) {
    val message = "Hello there @BallmerPeakyB I'd like some chicken tonight"
    println(TwitterMessageConverter.convertMessageToQuery(message))
}
