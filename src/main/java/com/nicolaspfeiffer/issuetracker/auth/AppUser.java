package com.nicolaspfeiffer.issuetracker.auth;

import com.nicolaspfeiffer.issuetracker.userprofile.UserProfile;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;

import static javax.persistence.GenerationType.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "AppUser")
@Table(
        name = "app_user",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "app_user_email_unique",
                        columnNames = "email"
                )
        }
)
public class AppUser implements UserDetails {
    @SequenceGenerator(
            name = "app_user_sequence",
            sequenceName = "app_user_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            generator = "app_user_sequence",
            strategy = SEQUENCE
    )
    @Column(
            name = "id"
    )
    private Long id;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String email;

    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String password;

    @Column(
            name = "account_non_expired",
            nullable = false
    )
    private boolean accountNonExpired;

    @Column(
            name = "account_non_locked",
            nullable = false
    )
    private boolean accountNonLocked;

    @Column(
            name = "credentials_non_expired",
            nullable = false
    )
    private boolean credentialsNonExpired;

    @Column(
            name = "enabled",
            nullable = false
    )
    private boolean enabled;

    @OneToOne
    private UserProfile profile;

    public AppUser(String email,
                   String password,
                   boolean accountNonExpired,
                   boolean accountNonLocked,
                   boolean credentialsNonExpired,
                   boolean enabled) {
        this.email = email;
        this.password = password;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
