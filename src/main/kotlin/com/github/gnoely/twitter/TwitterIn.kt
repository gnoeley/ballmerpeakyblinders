package com.github.gnoely.twitter

import com.twitter.hbc.core.Constants
import com.twitter.hbc.core.Hosts
import com.twitter.hbc.core.HttpHosts
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint
import com.twitter.hbc.core.event.Event
import com.twitter.hbc.httpclient.auth.Authentication
import com.twitter.hbc.httpclient.auth.OAuth1
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import com.twitter.hbc.core.processor.StringDelimitedProcessor
import com.twitter.hbc.ClientBuilder
import com.twitter.hbc.httpclient.BasicClient
import com.google.common.collect.Lists
import com.twitter.hbc.core.endpoint.StatusesFirehoseEndpoint
import com.twitter.hbc.core.endpoint.StreamingEndpoint
import com.twitter.hbc.twitter4j.Twitter4jStatusClient
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import twitter4j.examples.tweets.TwitterOut
import java.util.concurrent.Executors


@Component
class TwitterIn {

    @Autowired lateinit var twitterConfig : TwitterConfiguration
    @Autowired lateinit var twitterOut : TwitterOut

    /** Set up your blocking queues: Be sure to size these properly based on expected TPS of your stream */
    private val msgQueue : BlockingQueue<String> = LinkedBlockingQueue<String>(100000)
    private val eventQueue : BlockingQueue<Event> = LinkedBlockingQueue<Event>(1000)

    /** Declare the host you want to connect to, the endpoint, and authentication (basic auth or oauth) */
    private val hosebirdHosts : Hosts = HttpHosts(Constants.STREAM_HOST)

    // Optional: set up some followings and track terms




    val numProcessingThreads : Int = 4


    private val listener1 = object : StatusListener {
        override fun onStatus(status: Status) {
            twitterOut.send(status.user.name, "Bad user, no recipe for you")
            println(status)

        }

        override fun onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

        override fun onTrackLimitationNotice(limit: Int) {}

        override fun onScrubGeo(user: Long, upToStatus: Long) {}

        override fun onStallWarning(warning: StallWarning) {}

        override fun onException(e: Exception) {}
    }

    private fun auth() : Authentication {


        return OAuth1(
                twitterConfig.getConsumerKey(),
                twitterConfig.getConsumerSecret(),
                twitterConfig.getAccessToken(),
                twitterConfig.getAccessTokenSecret())
    }


    fun connect()  {
        val hosebirdEndpoint = StatusesFilterEndpoint()

        val terms : List<String> = listOf("BalmerPeakyB")
        hosebirdEndpoint.trackTerms(terms)

        val builder = ClientBuilder()
                .name("BallmerPeakyBlinders")                              // optional: mainly for the logs
                .hosts(hosebirdHosts)
                .authentication(auth())
                .endpoint(hosebirdEndpoint)
                .processor(StringDelimitedProcessor(msgQueue))
                .eventMessageQueue(eventQueue)                          // optional: use this if you want to process client events

        val hosebirdClient = builder.build()
        // Attempts to establish a connection.

        val service = Executors.newFixedThreadPool(numProcessingThreads)


        val t4jClient = Twitter4jStatusClient(hosebirdClient, msgQueue, listOf(listener1), service)

        // Establish a connection
        t4jClient.connect()

        for (threads in 0 until numProcessingThreads) {
            // This must be called once per processing thread
            t4jClient.process()
        }

    }

}
