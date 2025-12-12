package com.kyra.Expense.Tracker.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private UUID referenceId;

    @Column
    private String createdBy;

    @Column
    private String updatedBy;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Column
    private LocalDateTime deletedAt;

    protected UUID generateUUID() {
        return UUID.randomUUID();
    }

    @PrePersist
    protected void onCreate() {
        if (Objects.isNull(referenceId)) {
            referenceId = generateUUID();
        }
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;

        createdBy = "1";
        updatedBy = "1";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User loggedUser) {
            if (Objects.nonNull(loggedUser.getId())) {
                createdBy = loggedUser.getId().toString();
                updatedBy = loggedUser.getId().toString();
            }
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
        updatedBy = "1";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User loggedUser) {
            if (Objects.nonNull(loggedUser.getId())) {
                updatedBy = loggedUser.getId().toString();
            }
        }
    }
}
