package com.github.gnoely.controller

import com.github.gnoely.model.ReplyBuildingService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageInController {

    @PostMapping("/message")
    @ResponseBody
    fun sendMessage(message: String): String {
        val reply = ReplyBuildingService.buildReply("HackManchester2017", message)
        return "{reply: $reply}"
    }


}
