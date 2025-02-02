package com.davendra.buzzer.dto.request;

import com.davendra.buzzer.models.UserModel;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ChatRequest {
    // user1 will come from auth token
    private Long user2;
}
