package net.javaguides.springboot.security.services;

import com.example.secure_auth_jwt.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;  // Bổ sung trường email
    private boolean admin; // Bổ sung trường admin
    @JsonIgnore
    private String password;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public static UserDetailsImpl build(net.javaguides.springboot.entity.Accounts account) {
        return new UserDetailsImpl(
                account.getId(),
                account.getUsername(),
                account.getEmail(),
                account.isAdmin(),
                account.getPassword(),
                account.getCreateAt(),
                account.getUpdateAt());
    }

    public Long getId() {
        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Thêm getter và setter cho createAt
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    // Thêm getter và setter cho updateAt
    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }
}
