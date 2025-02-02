package com.davendra.buzzer.controller;

import com.davendra.buzzer.models.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class RealtimeChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @MessageMapping("/chat/{groupId}") // here user can send the data form client side
    public MessageModel sendToUser(@Payload MessageModel messageModel, @DestinationVariable String groupId) {
        System.out.println(messageModel);
        System.out.println(groupId);
        simpMessagingTemplate.convertAndSend("/user/" + groupId + "/private", messageModel); // provide user, destination and payload
        return messageModel;
    }
}
