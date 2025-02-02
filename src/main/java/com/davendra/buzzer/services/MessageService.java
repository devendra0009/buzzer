package com.davendra.buzzer.services;

import com.davendra.buzzer.dto.request.MessageRequest;
import com.davendra.buzzer.models.ChatModel;
import com.davendra.buzzer.models.MessageModel;
import com.davendra.buzzer.models.UserModel;

import java.util.List;

public interface MessageService {
    public MessageModel createMessage(UserModel user, Long chatId, MessageRequest messageRequest);

    public List<MessageModel> findMessagesByChatId(Long chatId);
}
