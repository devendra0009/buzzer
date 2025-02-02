package com.davendra.buzzer.models;

import com.davendra.buzzer.dto.response.UploadedFileResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class ReelModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String video;
    private String description;

    @ManyToOne
    private UserModel user;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
