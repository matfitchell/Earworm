package com.earworm.backendearworm;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/user")



public class UserController {

    private final UserService userService;

    public static UserRepository globalRepository;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
        globalRepository = UserService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody User user) {
      //  userService.addNewUser(user);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void UpdateUser(@PathVariable("id") Long id, @RequestParam(required = false) String username,
            @RequestParam(required = false) int zipCode, @RequestParam(required = false) String bio) {
        userService.updateUser(id, username, zipCode, bio);
    }

}
