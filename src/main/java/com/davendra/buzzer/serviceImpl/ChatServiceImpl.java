package com.davendra.buzzer.serviceImpl;

import com.davendra.buzzer.models.ChatModel;
import com.davendra.buzzer.models.UserModel;
import com.davendra.buzzer.repositories.ChatRepo;
import com.davendra.buzzer.services.ChatService;
import com.davendra.buzzer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class ChatServiceImpl implements ChatService {
    @Autowired
    private ChatRepo chatRepo;

    @Autowired
    private UserService userService;

    @Override
    public ChatModel createChat(Long user1, Long user2) throws Exception {

        UserModel userModel1 = userService.findUserById(user1);
        UserModel userModel2 = userService.findUserById(user2);
        ChatModel isExist = chatRepo.findChatByUserIds(userModel1, userModel2);

        if (isExist != null) {
            return isExist;
        }

        if (Objects.equals(user1, user2)) {
            throw new Exception("Can't chat with yourself for now!");
        }

        ChatModel newChat = new ChatModel();
        newChat.getUsers().add(userModel1);
        newChat.getUsers().add(userModel2);

        return chatRepo.save(newChat);
    }

    @Override
    public ChatModel findChatById(Long chatId) {
        return chatRepo.findById(chatId).orElseThrow(() -> new NoSuchElementException("Chat not found with id " + chatId));
    }

    @Override
    public List<ChatModel> findChatsByUserId(Long userId) {
        return chatRepo.findByUsersId(userId);
    }
}
