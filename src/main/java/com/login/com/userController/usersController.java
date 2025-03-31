package com.login.com.userController;

import com.login.com.userModel.User;
import com.login.com.usersRepository.UserRepository;
import com.login.com.usersService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    @GetMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam String username, @RequestParam String password) {
        Object response = userService.getUser(username, password);
        return ResponseEntity.ok(response);
    }


    // PUT API to update user credentials
    @PutMapping("/update")
    public ResponseEntity<Object> updateUser(@RequestBody Map<String, String> userDetails) {
        String oldUsername = userDetails.get("oldUsername");
        String oldPassword = userDetails.get("oldPassword");
        String newUsername = userDetails.get("newUsername");
        String newPassword = userDetails.get("newPassword");

        Object response = userService.updateUserCredentials(oldUsername, oldPassword, newUsername, newPassword);
        return ResponseEntity.ok(response);
    }


}
