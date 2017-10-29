package com.github.gnoely.lex

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.lexruntime.AmazonLexRuntime
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder
import com.amazonaws.services.lexruntime.model.PostTextRequest
import com.amazonaws.services.lexruntime.model.PostTextResult
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component

@Component
@PropertySource("classpath:/secret/aws.properties")
class LexClient {

    var accessKeyId = ""

    var secretKey = ""

    val REGION = "us-east-1"

    var lexRuntimeAsyncClient: AmazonLexRuntime? = null

    init {
        val lexRuntimeAsyncClientBuilder = AmazonLexRuntimeClientBuilder.standard()
        lexRuntimeAsyncClientBuilder.region = REGION
        lexRuntimeAsyncClientBuilder.withCredentials(AWSStaticCredentialsProvider(object : AWSCredentials  {

            override fun getAWSAccessKeyId(): String {
                return accessKeyId
            }

            override fun getAWSSecretKey(): String {
                return secretKey
            }
        }))
        lexRuntimeAsyncClient = lexRuntimeAsyncClientBuilder.build()
    }

    fun sendTextToLex(inputText: String, sessionId: String): PostTextResult? {

        val request = PostTextRequest()
            .addSessionAttributesEntry("sessionId", sessionId)
            .addRequestAttributesEntry("requestId", "myRequestId")
            .withBotName("Recipe")
            .withBotAlias("latest")
            .withUserId("doAHashOfTwitterHandle")
            .withInputText(inputText)

        return lexRuntimeAsyncClient?.postText(request)
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
