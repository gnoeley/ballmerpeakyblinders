package com.github.gnoely.service

import com.github.gnoely.lex.LexClient
import com.github.gnoely.twitter.TwitterOut
import org.springframework.beans.factory.annotation.Autowired
import twitter4j.Status
import java.util.*

class ConversationService {

    @Autowired private lateinit var twitterOut : TwitterOut
    @Autowired private lateinit var lexClient : LexClient


    private val sessions : MutableList<Session> = mutableListOf()




    fun getSession(tweet: Status) : Session? {
        var session : Session? = null

        if (tweet.inReplyToStatusId > 0 && tweet.inReplyToScreenName == "BallmerPeakyB") {
            session = sessions.find { it.lastStatusIdFromUs == tweet.inReplyToStatusId }
        }

        if (session == null) {
            session = Session(userHandle = tweet.user.screenName, userName = tweet.user.name, sessionId = UUID.randomUUID().toString())
        }
        return session
    }







}


class Session(var lastStatusIdFromUs: Long, var lastDialogState: String, val userHandle: String, val userName: String, val sessionId: String)
