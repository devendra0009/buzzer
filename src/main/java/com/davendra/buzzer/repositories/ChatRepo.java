package com.davendra.buzzer.repositories;

import com.davendra.buzzer.entity.ChatModel;
import com.davendra.buzzer.entity.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<ChatModel, Long> {
    public Page<ChatModel> findByUsersId(Long userId, Pageable pageable);

    // use member of instead of in -> since *IN* checks if value in our entity present in the list you provided whereas *MEMBER OF* checks if value you provided present in some collection in our entity
    @Query("select c from ChatModel c where :user1 member of c.users and :user2 member of c.users")
    public ChatModel findChatByUserIds(@Param("user1") UserModel user1, @Param("user2") UserModel user2);
}