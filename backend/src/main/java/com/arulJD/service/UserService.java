package com.arulJD.service;

import com.arulJD.entity.User;

import java.util.List;

public interface UserService {

    public User findUserById(Long id) throws Exception;
    public User findUserByJwt(String jwt) throws Exception;
}
