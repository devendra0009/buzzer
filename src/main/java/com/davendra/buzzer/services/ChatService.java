package com.davendra.buzzer.services;

import com.davendra.buzzer.models.ChatModel;

import java.util.List;

public interface ChatService {
    public ChatModel createChat(Long user1, Long user2) throws Exception; // user1 is current user logged in, user2 is the user which we want to get connected with

    public ChatModel findChatById(Long chatId);

    public List<ChatModel> findChatsByUserId(Long userId);
}
