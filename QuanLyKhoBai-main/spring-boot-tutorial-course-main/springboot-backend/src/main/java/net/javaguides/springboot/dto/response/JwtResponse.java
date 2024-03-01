package net.javaguides.springboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtResponse {
    private String jwt;
    private Long id;
    private String username;
    private String email;
    private Boolean admin;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
