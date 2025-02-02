package com.davendra.buzzer.controller;

import com.davendra.buzzer.dto.request.ChatRequest;
import com.davendra.buzzer.models.ChatModel;
import com.davendra.buzzer.services.ChatService;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
public class ChatController {
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserService userService;

    @PostMapping("/new")
    public ChatModel createChat(@RequestHeader("Authorization") String token, @RequestBody ChatRequest chatRequest) throws Exception {
        return chatService.createChat(userService.getUserFromToken(token).getId(), chatRequest.getUser2());
    }

    @GetMapping("/get/{chatId}")
    public ChatModel getChatById(@PathVariable Long chatId) {
        return chatService.findChatById(chatId);
    }

    @GetMapping("/user/get")
    public List<ChatModel> findChatsByUserId(@RequestHeader("Authorization") String token) {
        return chatService.findChatsByUserId(userService.getUserFromToken(token).getId());
    }

}
