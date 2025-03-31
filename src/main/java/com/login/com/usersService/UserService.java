package com.login.com.usersService;

import com.login.com.userModel.User;
import com.login.com.usersRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user); // Save user to DB
    }
}
