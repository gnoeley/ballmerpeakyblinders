package com.github.gnoely.service

import com.github.gnoely.lex.LexClient
import com.github.gnoely.twitter.TwitterOut
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.*

@Component
class ConversationService {

    @Autowired private lateinit var twitterOut : TwitterOut
    @Autowired private lateinit var lexClient : LexClient
    @Autowired private lateinit var replyBuilderService: ReplyBuildingService

    private val READY_FOR_FULFILLMENT = "ReadyForFulfillment"
    private val FAILED = "Failed"

    private val PERSONAL_RECOMMMENDATION_INENT = "PersonalRecommendation"
    private val RECIPE_STARTER_INTENT = "RecipeStarter"

    private val sessions : MutableList<Session> = mutableListOf()

    private fun getSession(
        fromScreenName: String,
        fromUserName : String,
        inReplyToStatusId : Long?,
        inReplyToScreenName : String?  ) : Session {



        var session : Session? = null

        if (inReplyToStatusId != null && inReplyToStatusId > 0 && inReplyToScreenName == "BallmerPeakyB") {
            session = sessions.find { it.lastStatusIdFromUs == inReplyToStatusId }
//            session = session?.copy(lastStatusIdFromUs = inReplyToStatusId)
        }

        if (session == null) {
            session = Session(userHandle = fromScreenName,
                userName = fromUserName,
                sessionId = UUID.randomUUID().toString())
        }
        println("Using sessionID = ${session.sessionId}")
        return session
    }

    fun handleMessage(
        fromScreenName: String,
        fromUserName : String,
        inStatusId: Long,
        inReplyToStatusId : Long?,
        inReplyToScreenName : String?,
        messageText : String ) {

        if (fromScreenName == "BallmerPeakyB") {
            return
        }

        val session = getSession(fromScreenName, fromUserName, inReplyToStatusId, inReplyToScreenName)
        val result = lexClient.sendTextToLex(messageText, session.sessionId)



        if (READY_FOR_FULFILLMENT.equals(result?.dialogState)) {
            if (PERSONAL_RECOMMMENDATION_INENT.equals(result?.intentName)) {
                val reply = replyBuilderService.buildReply(session.userHandle, emptyList())
                twitterOut.sendReply(inStatusId, fromScreenName, reply.message, reply.imageUrl)
            } else if (RECIPE_STARTER_INTENT.equals(result?.intentName)) {
                val ingredients = mutableListOf<String>()
                result?.slots?.get("ingredientsOne")?.let { ingredients.add(it) }
                result?.slots?.get("ingredientsTwo")?.let { ingredients.add(it) }
                val cuisineString = result?.slots?.get("cuisine")?: "None"
                val reply = replyBuilderService.buildReplyWithCuisine(session.userHandle, ingredients, cuisineString)
                twitterOut.sendReply(inStatusId, fromScreenName, reply.message, reply.imageUrl)
            } else {
                val ingredients = mutableListOf<String>()
                result?.slots?.get("ingredientsOne")?.let { ingredients.add(it) }
                result?.slots?.get("ingredientsTwo")?.let { ingredients.add(it) }
                val reply = replyBuilderService.buildReply(session.userHandle, ingredients)
                twitterOut.sendReply(inStatusId, fromScreenName, reply.message, reply.imageUrl)

                session.lastStatusIdFromUs

            }

        } else if (FAILED.equals(result?.dialogState)){
            println("FAILED: $result $fromScreenName $messageText $session")

        } else {
            val outStatusId = twitterOut.sendReply(inStatusId, fromScreenName, result?.message ?: "", null)
            println("outStatusId $outStatusId")
            sessions.add(session.copy(lastStatusIdFromUs = outStatusId))
        }
    }


}

data class Session(var lastStatusIdFromUs: Long? = null,
              var lastDialogState: String? = null,
              val userHandle: String,
              val userName: String,
              val sessionId: String)
