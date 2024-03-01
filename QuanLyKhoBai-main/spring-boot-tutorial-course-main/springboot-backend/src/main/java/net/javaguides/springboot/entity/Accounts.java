package net.javaguides.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "accounts", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name")
})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accounts {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Size(min = 5, max = 255)
    @Column(name = "user_name")
    private String username;

    @Size(min = 6, max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Column(name = "admin", nullable = false)
    private boolean admin = false;

    @Column(name = "create_at", nullable = false, updatable = false)
    private LocalDateTime createAt = LocalDateTime.now();

    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt = LocalDateTime.now();
}
