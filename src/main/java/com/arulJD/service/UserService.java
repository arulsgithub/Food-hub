package com.arulJD.service;

import com.arulJD.entity.User;

import java.util.List;

public interface UserService {

    public User createUser(User user);
    public List<User> getAllUsers();
    public User findUserById(Long id) throws Exception;
}
