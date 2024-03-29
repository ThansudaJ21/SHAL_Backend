package com.se.shal.security.service;

import com.se.shal.security.entity.User;

public interface UserService {
    User registerNewUser(User user);
    User findByUserId(String email);
    User setRoleUser(User user);
}
