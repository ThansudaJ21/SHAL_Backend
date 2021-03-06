package com.se.shal.security.service;

import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    public User save(User user){
        return userDao.save(user);
    }
}
