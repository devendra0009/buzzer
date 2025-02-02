package com.davendra.buzzer.dto.response;

import com.davendra.buzzer.models.UserModel;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
public class StoryResponse {
    private Long id;
    private String captions;
    private String image;
    private  boolean isActive;
    private UserModel user;
    private LocalDateTime deactivatedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
