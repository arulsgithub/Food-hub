package com.arulJD.service.impl;

import com.arulJD.config.JWTProvider;
import com.arulJD.entity.User;
import com.arulJD.repository.UserRepository;
import com.arulJD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Override
    public User findUserById(Long id) throws Exception {

        User user = userRepository.findById(id).orElse(null);
        if(user != null) return user;
        throw new Exception("User not found with id: "+id);
    }

    @Override
    public User findUserByJwt(String jwt) throws Exception {

        String email = jwtProvider.getEmailFromToken(jwt);
        if(email==null) throw new Exception("Invalid JWT token");

        User user = userRepository.findByEmail(email);
        if (user==null) throw new Exception("User not found with email: "+email);

        return user;
    }
}
