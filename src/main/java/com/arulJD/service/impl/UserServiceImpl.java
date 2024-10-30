package com.arulJD.service.impl;

import com.arulJD.entity.User;
import com.arulJD.repository.UserRepository;
import com.arulJD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User findUserById(Long id) throws Exception {

        User user = userRepository.findById(id).orElse(null);
        if(user != null) return user;
        throw new Exception("User not found with id: "+id);
    }
}
