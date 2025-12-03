package com.kyra.Expense.Tracker.db;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kyra.Expense.Tracker.enums.AuthProvider;
import com.kyra.Expense.Tracker.enums.Role;
import com.kyra.Expense.Tracker.utils.NamespaceUUID;
import com.kyra.Expense.Tracker.utils.UUIDGenerator;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_users_email", columnNames = "email")
        }
)
public class User extends BaseEntity implements UserDetails {

    @Column(nullable = false, length = 120)
    private String firstName;

    @Column(nullable = false, length = 120)
    private String lastName;

    @Column(nullable = false, unique = true, length = 150, updatable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false, length = 60)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private AuthProvider provider;

    @Column(nullable = false)
    @Builder.Default
    private boolean active = true;

    @Column
    private String avatarUrl;

    @Override
    protected UUID generateUUID() {
        if (Objects.nonNull(email)) {
            return UUIDGenerator.generate(NamespaceUUID.USER, email.toLowerCase().trim());
        }
        return null;
    }

    @PrePersist
    @PreUpdate
    private void normalizeEmail() {
        if (Objects.nonNull(email)) {
            email = email.toLowerCase().trim();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + role.name())
        );
    }

    @Override
    public String getPassword() {
        return this.passwordHash;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}

