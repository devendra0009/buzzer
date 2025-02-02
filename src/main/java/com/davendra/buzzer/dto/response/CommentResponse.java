package com.davendra.buzzer.dto.response;

import com.davendra.buzzer.models.PostModel;
import com.davendra.buzzer.models.UserModel;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CommentResponse {
    private Long id;
    private String content;
    private UserModel commentedBy;
    private List<UserModel> likedBy;
    private PostModel post;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
