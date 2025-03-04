package com.davendra.buzzer.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentRequest {
    private String content;
    private Long postId;
}
