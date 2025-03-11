package com.week3.MVC.service;

import com.week3.MVC.entity.User;
import com.week3.MVC.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User findByStudentId(Long id) {
        return userRepository.findById(id).orElse(null);
    }


    public void addUser(User user) {
        logger.info("Adding new user: {}", user.getUserName());
        userRepository.save(user);
    }

    public User userLogin(String userName, String password) {
        logger.info("Fetching user by username: {}", userName);
        List<User> userList = userRepository.findByUserName(userName);
        if (userList.isEmpty()) {
            logger.info("No user found with username: {}", userName);
            return null;
        }
        return userList.stream()
                .filter(user -> user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<User> getAllNonAdminUsers() {
        return userRepository.findByIsAdminAndIsDelete(0, 0); // Fetch only non-admin users where is_delete = 0
    }

    public void softDeleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setIsDelete(1); // Mark as deleted
            userRepository.save(user);
        }
    }


}
