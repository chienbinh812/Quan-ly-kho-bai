package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.dto.UserDto;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private ModelMapper modelMapper;
    private UserService userService;
    private UserRepository userRepository;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping()
    public ResponseEntity<Page<User>> getAllUsersByPageWithFilters(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name
    ) {
        Page<User> users = userService.getAllUsersByPageWithFilters(sortBy, sortDir, page, size, id, name);
        return ResponseEntity.ok(users);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<User> getUserById(@PathVariable("id") int userId) {
//        return new ResponseEntity<User>(userService.getUserById(userId), HttpStatus.OK);
//    }
@GetMapping("{id}")
public ResponseEntity<UserDto> getEmployeeById(@PathVariable("id") Integer id) {
    User employee = userService.getUserById(id);
    UserDto userDto = modelMapper.map(employee, UserDto.class);
    return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
}

//    @GetMapping("/search")
//    public ResponseEntity<List<User>> searchProducts(@RequestParam("name") String name){
//        return ResponseEntity.ok(userService.findByName(name));
//    }

    // build create employee REST API
    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id
            , @RequestBody User user) {
        return new ResponseEntity<User>(userService.updateUser(user, id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") int id) {
        // delete employee from DB
        userService.deleteUser(id);
        return new ResponseEntity<String>("Product deleted successfully!.", HttpStatus.OK);
    }
}
