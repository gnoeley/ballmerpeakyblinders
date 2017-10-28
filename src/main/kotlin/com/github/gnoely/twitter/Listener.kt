package com.github.gnoely.twitter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import twitter4j.StallWarning
import twitter4j.Status
import twitter4j.StatusDeletionNotice
import twitter4j.StatusListener
import java.time.LocalTime

@Component
class Listener : StatusListener {

    @Autowired lateinit var twitterOut : TwitterOut

    override fun onStatus(status: Status) {
        twitterOut.sendReply(status.id, status.user.screenName, " - Bad user, no recipe for you: " + LocalTime.now().toString())
        println(status)

    }

    override fun onDeletionNotice(statusDeletionNotice: StatusDeletionNotice) {}

    override fun onTrackLimitationNotice(limit: Int) {}

    override fun onScrubGeo(user: Long, upToStatus: Long) {}

    override fun onStallWarning(warning: StallWarning) {}

    override fun onException(e: Exception) {}
}
