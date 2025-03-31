package com.login.com.userController;

import com.login.com.userModel.User;
import com.login.com.usersRepository.UserRepository;
import com.login.com.usersService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")//baseUrl
public class usersController {

    @Autowired
    private UserService userService;

    // POST API to register a user
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

}
