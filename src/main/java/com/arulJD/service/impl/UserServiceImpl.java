package com.arulJD.service.impl;

import com.arulJD.entity.User;
import com.arulJD.repository.UserRepository;
import com.arulJD.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public User findUserById(Long id) throws Exception {

        User user = userRepository.findById(id).orElse(null);
        if(user != null) return user;
        throw new Exception("User not found with id: "+id);
    }
}
