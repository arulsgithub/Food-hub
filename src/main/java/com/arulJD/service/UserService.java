package com.arulJD.service;

import com.arulJD.entity.User;

public interface UserService {

    public User findUserById(Long id) throws Exception;
}
