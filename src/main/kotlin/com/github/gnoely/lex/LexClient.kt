package com.github.gnoely.lex

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.services.lexruntime.AmazonLexRuntime
import com.amazonaws.services.lexruntime.AmazonLexRuntimeClientBuilder
import com.amazonaws.services.lexruntime.model.PostTextRequest
import com.amazonaws.services.lexruntime.model.PostTextResult
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.PropertySource
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class LexClient {

//    @Autowired lateinit var awsConfiguration : AwsConfiguration

    val REGION = "us-east-1"

    var lexRuntimeAsyncClient: AmazonLexRuntime? = null

    @PostConstruct
    fun setup() {
//        val lexRuntimeAsyncClientBuilder = AmazonLexRuntimeClientBuilder.standard()
//        lexRuntimeAsyncClientBuilder.region = REGION
//        lexRuntimeAsyncClientBuilder.withCredentials(
//
//            AWSStaticCredentialsProvider(object : AWSCredentials  {
//
//            override fun getAWSAccessKeyId(): String {
//                return awsConfiguration.getAccessKeyId()
//            }
//
//            override fun getAWSSecretKey(): String {
//                return awsConfiguration.getAccessKeySecret()
//            }
//        }))
//        lexRuntimeAsyncClientBuilder.build()

        val builder = AmazonLexRuntimeClientBuilder.standard()
        builder.region = "us-east-1"


        lexRuntimeAsyncClient =    builder.build()


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
