package com.davendra.buzzer.dto.request;

import lombok.Data;

@Data
public class ChatRequest {
    // user1 will come from auth token
    private Long user2;
}
