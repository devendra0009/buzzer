package com.davendra.buzzer.repositories;

import com.davendra.buzzer.models.StoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StoryRepo extends JpaRepository<StoryModel, Long> {

    @Query("select s from StoryModel s where s.user.id in :userId and s.isActive = true ")
    public List<StoryModel> findActiveByUserId(Long userId);

    @Query("select s from StoryModel s where s.user.id in :userIds and s.isActive = true ")
    public List<StoryModel> findActiveByUserIdIn(List<Long> userIds);

    @Query("SELECT s FROM StoryModel s WHERE s.isActive = true AND s.deactivatedAt <= :currentTime")
    List<StoryModel> findByIsActiveTrueAndDeactivateAtBefore(@Param("currentTime") LocalDateTime currentTime);

}
