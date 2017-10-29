package com.github.gnoely.controller

import com.github.gnoely.service.ConversationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class MessageInController {


    @Autowired lateinit var conversationService: ConversationService



    @GetMapping("/faketweetfrom/{userHandle}")
    @ResponseBody
    fun fakeTweet(
        @PathVariable(value="userHandle" ) userHandle : String ,
        @RequestParam message : String,
        @RequestParam replyToId : Long,
        @RequestParam replyToUser : String) {
        conversationService.handleMessage(userHandle, userHandle, 123, replyToId, replyToUser, message )


    }


}
