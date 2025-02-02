package com.davendra.buzzer.repositories;

import com.davendra.buzzer.models.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<MessageModel, Long> {
    public List<MessageModel> findByChatId(Long chatId);
}
