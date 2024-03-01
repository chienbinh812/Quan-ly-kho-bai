package net.javaguides.springboot.service.impl;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }



    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsersByPageWithFilters(String sortBy, String sortDir, Integer page, Integer size, Integer id, String name) {
                Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<User> users = userRepository.findAll(pageable);
//        return userRepository.findUsersByPageWithFilters(pageable,id,name);
        return users;
    }

    @Override
    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
    }

//    @Override
//    public List<User> findByName(String name) {
//        List<User> users = userRepository.findByName(name);
//        return users;
//    }

    @Override
    public User updateUser(User user, int id) {
        User existingUser = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "Id", id));
        existingUser.setId(user.getId());
        existingUser.setName(user.getName());
        existingUser.setRole(user.getRole());
        userRepository.save(existingUser);
        return existingUser;
    }

    @Override
    public void deleteUser(int id) {
        userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("User", "Id", id));
        userRepository.deleteById(id);
    }
}
