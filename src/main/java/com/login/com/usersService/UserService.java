package com.login.com.usersService;

import com.login.com.userModel.User;
import com.login.com.usersRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private Map<String, Integer> failedAttempts = new HashMap<>();
    private Map<String, Instant> blockedUsers = new HashMap<>();
    private static final int MAX_ATTEMPTS = 3;
    private static final int BLOCK_DURATION = 10; // in seconds

    public User saveUser(User user) {
        return userRepository.save(user); // Save user to DB
    }



    public Object getUser(String username, String password) {
        if (username == null || username.isEmpty()) {
            return "Please add username";
        }
        if (password == null || password.isEmpty()) {
            return "Please add the password";
        }

        // Check if the user is blocked
        if (blockedUsers.containsKey(username)) {
            Instant blockTime = blockedUsers.get(username);
            if (Instant.now().isBefore(blockTime.plusSeconds(BLOCK_DURATION))) {
                return "Account is blocked! Try again after 10 seconds.";
            } else {
                blockedUsers.remove(username); // Unblock after 10 sec
                failedAttempts.put(username, 0); // Reset failed attempts
            }
        }

        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);
        if (user.isPresent()) {
            failedAttempts.put(username, 0); // Reset failed attempts
            return user.get(); // Return user data (id, username, password)
        } else {
            int attempts = failedAttempts.getOrDefault(username, 0) + 1;
            failedAttempts.put(username, attempts);

            if (attempts >= MAX_ATTEMPTS) {
                blockedUsers.put(username, Instant.now());
                return "Too many failed attempts! Account blocked for 10 seconds.";
            } else {
                return "Invalid credentials! Attempt " + attempts + " of " + MAX_ATTEMPTS;
            }
        }
    }

    // Edit user credentials
    public Object updateUserCredentials(String oldUsername, String oldPassword, String newUsername, String newPassword) {
        if (oldUsername == null || oldUsername.isEmpty()) {
            return "Please add current username";
        }
        if (oldPassword == null || oldPassword.isEmpty()) {
            return "Please add current password";
        }
        if (newUsername == null || newUsername.isEmpty()) {
            return "Please add new username";
        }
        if (newPassword == null || newPassword.isEmpty()) {
            return "Please add new password";
        }

        // Check if user exists with current credentials
        Optional<User> user = userRepository.findByUsernameAndPassword(oldUsername, oldPassword);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setUsername(newUsername);
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
            return "User credentials updated successfully!";
        } else {
            return "Invalid current username or password!";
        }
    }
}
