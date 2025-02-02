package com.davendra.buzzer.dto.request;

import com.davendra.buzzer.models.PostModel;
import com.davendra.buzzer.models.UserModel;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentRequest {
    private String content;
    private Long postId;
}
