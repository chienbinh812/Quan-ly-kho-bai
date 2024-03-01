package net.javaguides.springboot.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class AccountDto {   
    private int id;
    private String username;
    private String password;
    private int userId;
}
