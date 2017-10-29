package com.github.gnoely.service

import com.github.gnoely.lex.LexClient
import com.github.gnoely.twitter.TwitterOut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import twitter4j.Status
import java.util.*

@Component
class ConversationService {

    @Autowired private lateinit var twitterOut : TwitterOut
    @Autowired private lateinit var lexClient : LexClient
    @Autowired private lateinit var replyBuilderService: ReplyBuildingService

    private val READY_FOR_FULFILLMENT = "ReadyForFulfillment"
    private val FAILED = "Failed"

    private val PERSONAL_RECOMMMENDATION_INENT = "PersonalRecommendation"

    private val sessions : MutableList<Session> = mutableListOf()

    private fun getSession(tweet: Status) : Session {
        var session : Session? = null

        if (tweet.inReplyToStatusId > 0 && tweet.inReplyToScreenName == "BallmerPeakyB") {
            session = sessions.find { it.lastStatusIdFromUs == tweet.inReplyToStatusId }
        }

        if (session == null) {
            session = Session(userHandle = tweet.user.screenName,
                userName = tweet.user.name,
                sessionId = UUID.randomUUID().toString())
        }
        return session
    }

    fun handleMessage(incomingStatus: Status) {
        val session = getSession(incomingStatus)
        val result = lexClient.sendTextToLex(incomingStatus.text, session.sessionId)

        if (READY_FOR_FULFILLMENT.equals(result?.dialogState)) {
            if (PERSONAL_RECOMMMENDATION_INENT.equals(result?.intentName)) {
                val reply = replyBuilderService.buildReply(session.userHandle, emptyList())
                twitterOut.sendReply(incomingStatus.id, incomingStatus.user.screenName, reply.message, reply.imageUrl)

            } else {
                val ingredients = mutableListOf<String>()
                result?.slots?.get("ingredientsOne")?.let { ingredients.add(it) }
                result?.slots?.get("ingredientsTwo")?.let { ingredients.add(it) }
                val reply = replyBuilderService.buildReply(session.userHandle, ingredients)
                twitterOut.sendReply(incomingStatus.id, incomingStatus.user.screenName, reply.message, reply.imageUrl)

            }

        } else if (FAILED.equals(result?.dialogState)){
            println("FAILED: $result $incomingStatus $session")

        } else {
            twitterOut.sendReply(incomingStatus.id, incomingStatus.user.screenName, result?.message ?: "", null)

        }
    }


}


class Session(var lastStatusIdFromUs: Long? = null,
              var lastDialogState: String? = null,
              val userHandle: String,
              val userName: String,
              val sessionId: String)
