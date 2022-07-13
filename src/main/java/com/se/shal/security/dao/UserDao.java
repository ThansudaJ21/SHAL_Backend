package com.se.shal.security.dao;

import com.se.shal.security.entity.User;

public interface UserDao {
    User save(User user);
    User findByEmail(String userName);
    User findByUserId(String userId);
}
