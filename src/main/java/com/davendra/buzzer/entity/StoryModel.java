package com.davendra.buzzer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@RequiredArgsConstructor
public class StoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String captions;
    private String image;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private UserModel user;

    private boolean isActive;

    @Column(columnDefinition = "datetime")
    private LocalDateTime deactivatedAt;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "story_seen_by_user",
            joinColumns = @JoinColumn(name = "story_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserModel> seenBy;

    @CreationTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(columnDefinition = "datetime")
    private LocalDateTime updatedAt;
}
