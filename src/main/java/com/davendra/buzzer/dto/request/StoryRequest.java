package com.davendra.buzzer.dto.request;

import com.davendra.buzzer.models.UserModel;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoryRequest {
    private String captions;
    private String image;
    private Long userId;
    private Long deactivatedAt; // set after what seconds we want to deactive the story
}
