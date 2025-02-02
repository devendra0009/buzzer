package com.davendra.buzzer.repositories;

import com.davendra.buzzer.models.CommentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<CommentModel, Long> {
    public List<CommentModel> findByCommentedById(Long commentById);
    public List<CommentModel> findByPostId(Long postId);
}
