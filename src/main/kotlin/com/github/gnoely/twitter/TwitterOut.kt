/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package twitter4j.examples.tweets

import com.github.gnoely.twitter.TwitterConfiguration
import org.springframework.beans.factory.annotation.Autowired
import twitter4j.auth.AccessToken
import twitter4j.auth.RequestToken

import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import com.sun.tools.javac.tree.TreeInfo.args
import org.springframework.stereotype.Component
import twitter4j.*


/**
 * Example application that uses OAuth method to acquire access to your account.<br></br>
 * This application illustrates how to use OAuth method with Twitter4J.<br></br>
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */

@Component
class TwitterOut {

    @Autowired lateinit var config : TwitterConfiguration


    fun send(mentionUser : String , body : String) {
        // The factory instance is re-useable and thread safe.
        val factory = TwitterFactory()
        val accessToken = loadAccessToken()
        val twitter = factory.instance
        twitter.setOAuthConsumer(config.getConsumerKey(), config.getConsumerSecret())
        twitter.oAuthAccessToken = accessToken

        val status = twitter.updateStatus(buildMessage(mentionUser, body))
        println("Successfully updated the status to [" + status.text + "].")

    }

    fun buildMessage(mentionUser : String , body : String): String {
        return "@$mentionUser $body"
    }

    fun loadAccessToken() : AccessToken  {
        val token = config.getAccessToken()
        val secret = config.getAccessTokenSecret()

        return AccessToken(token, secret)
    }
}
