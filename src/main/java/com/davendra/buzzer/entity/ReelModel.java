package com.davendra.buzzer.entity;

import jakarta.persistence.*;
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
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;

}
