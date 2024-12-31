package com.yourproduct.your_product.entity;

import com.yourproduct.your_product.auditing.BaseAuditing;
import com.yourproduct.your_product.enums.UserRoles;
import com.yourproduct.your_product.enums.UserTokenTypes;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(callSuper = false)
public class User extends BaseAuditing implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(updatable = false, nullable = false, columnDefinition = "UUID")
    private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, unique = true, updatable = false)
    private String email;

    private String password;

    private LocalDateTime passwordChangedAt;

    @Enumerated(EnumType.STRING)
    private UserRoles role;

    private String token;

    @Enumerated(EnumType.STRING)
    private UserTokenTypes tokenType;

    private LocalDateTime tokenExpiresAt;

    private Boolean locked = false;

    private Boolean enabled = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
