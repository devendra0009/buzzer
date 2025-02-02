package com.davendra.buzzer.repositories;

import com.davendra.buzzer.models.PostModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<PostModel, Long> {
    List<PostModel> findByUserId(Long userId);

    @Query("select p from PostModel p join p.savedBy u where u.id=:userId")
    List<PostModel> findBySavedBy(Long userId);
}
