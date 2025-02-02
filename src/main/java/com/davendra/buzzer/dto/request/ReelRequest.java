package com.davendra.buzzer.dto.request;

import com.davendra.buzzer.models.UserModel;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
public class ReelRequest {
    private String title;
    private MultipartFile video; // change this multipart afterwards
    private String description;
}
