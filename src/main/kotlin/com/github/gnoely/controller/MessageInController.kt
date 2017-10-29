package com.github.gnoely.controller

import com.github.gnoely.model.Reply
import com.github.gnoely.service.ReplyBuildingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageInController {

    @Autowired lateinit var replyBuildingService: ReplyBuildingService

    @PostMapping("/message")
    @ResponseBody
    fun sendMessage(message: String): String {
        val reply: Reply = replyBuildingService.buildReply(message)
        return "{reply: ${reply.message}, image: ${reply.imageUrl}}"
    }


}
