package com.github.gnoely.twitter


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import twitter4j.StatusUpdate
import twitter4j.TwitterFactory
import twitter4j.auth.AccessToken
import javax.annotation.PostConstruct


/**
 * Example application that uses OAuth method to acquire access to your account.<br></br>
 * This application illustrates how to use OAuth method with Twitter4J.<br></br>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */

@Component
class TwitterOut {

    @Autowired lateinit var config : TwitterConfiguration
    @Autowired lateinit var limitListener : RateLimitListener


    val twitter = TwitterFactory().instance!!


    @PostConstruct
    private fun auth() {
        // The factory instance is re-useable and thread safe.
        val accessToken = loadAccessToken()
        twitter.setOAuthConsumer(config.getConsumerKey(), config.getConsumerSecret())
        twitter.oAuthAccessToken = accessToken
        twitter.addRateLimitStatusListener(limitListener)
    }

    fun sendReply(originalStatusId : Long, mentionUser : String , body : String) {

        val status = twitter.updateStatus(buildMessage(originalStatusId, mentionUser, body))
        println("Successfully updated the status to [" + status.text + "].")

    }


    private fun buildMessage(inReplyToStatusId : Long, mentionUser : String , body : String): StatusUpdate {
        val statusUpdate = StatusUpdate("@$mentionUser $body")
        statusUpdate.inReplyToStatusId(inReplyToStatusId)
        return statusUpdate
    }

    private fun loadAccessToken() : AccessToken  {
        val token = config.getAccessToken()
        val secret = config.getAccessTokenSecret()

        return AccessToken(token, secret)
    }
}
