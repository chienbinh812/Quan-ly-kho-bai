package net.javaguides.springboot.model.mapper;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.dto.UserDto;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto temp = new UserDto();
        temp.setId(user.getId());
        temp.setName(user.getName());
        temp.setRole(user.getRole());
        return temp;
    }
}
