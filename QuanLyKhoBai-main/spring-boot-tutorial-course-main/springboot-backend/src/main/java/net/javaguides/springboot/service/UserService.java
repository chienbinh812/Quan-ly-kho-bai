package net.javaguides.springboot.service;

import net.javaguides.springboot.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    List<User> getAllUser();
    User getUserById(int id);
    User updateUser(User user, int id);
    void deleteUser(int id);

//    List<User> findByName(String name);

    Page<User> getAllUsersByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size
                                            , Integer id, String name);
}
