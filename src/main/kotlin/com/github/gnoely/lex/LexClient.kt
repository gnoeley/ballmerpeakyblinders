package com.github.gnoely.lex

import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder
import com.amazonaws.services.lexruntime.model.PostTextRequest
import com.amazonaws.services.lexruntime.model.PostTextResult

class LexClient {

    var lexRuntimeAsyncClient = AmazonLexRuntimeClientBuilder.defaultClient()

    fun sendTextToLex(inputText: String, sessionId: String): PostTextResult? {

        val request = PostTextRequest()
            .addSessionAttributesEntry("sessionId", sessionId)
            .addRequestAttributesEntry("requestId", "myRequestId")
            .withBotName("Recipe")
            .withBotAlias("latest")
            .withUserId("doAHashOfTwitterHandle")
            .withInputText(inputText)

        return lexRuntimeAsyncClient.postText(request)
    }






}

fun main(args: Array<String>) {
    val lexClient = LexClient()

    val result = lexClient.sendTextToLex("i want food", "mySessionId")

    println(result?.message)

    val result2 = lexClient.sendTextToLex("beef", "mySessionId")
    println(result2?.message)

    val result3 = lexClient.sendTextToLex("onion", "mySessionId")
    println(result3?.message)


}
