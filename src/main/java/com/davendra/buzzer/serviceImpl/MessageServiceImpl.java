package com.davendra.buzzer.serviceImpl;

import com.davendra.buzzer.dto.request.MessageRequest;
import com.davendra.buzzer.models.ChatModel;
import com.davendra.buzzer.models.MessageModel;
import com.davendra.buzzer.models.UserModel;
import com.davendra.buzzer.repositories.ChatRepo;
import com.davendra.buzzer.repositories.MessageRepo;
import com.davendra.buzzer.services.ChatService;
import com.davendra.buzzer.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private ChatService chatService;

    @Autowired
    private ChatRepo chatRepo;


    @Override
    public MessageModel createMessage(UserModel user, Long chatId, MessageRequest messageRequest) {
        ChatModel chat=chatService.findChatById(chatId);
        MessageModel newMessage=new MessageModel();
        newMessage.setChat(chat);
        newMessage.setContent(messageRequest.getContent());
        newMessage.setImage(messageRequest.getImage());
        newMessage.setUser(user);
        MessageModel savedMsg=messageRepo.save(newMessage);
        chat.getMessages().add(savedMsg);
        chatRepo.save(chat);
        return savedMsg;
    }

    @Override
    public List<MessageModel> findMessagesByChatId(Long chatId) {
        return messageRepo.findByChatId(chatId);
    }
}
