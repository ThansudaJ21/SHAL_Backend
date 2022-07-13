package com.se.shal.security.service;

import com.se.shal.line.config.LineInitComponent;
import com.se.shal.security.dao.AuthorityDao;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import com.se.shal.security.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    LineInitComponent lineInitComponent;
    @Autowired
    AuthorityDao authorityDao;

    @Override
    @Transactional
    public User registerNewUser(User user) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority buyer = authorityDao.findByName(AuthorityName.BUYER);

        User userDetails = null;
        boolean isUserExist = true;
        try {
            userDetails = userDao.findByEmail(user.getEmail());
            if (userDetails == null) {
                isUserExist = false;
            }
        } catch (UsernameNotFoundException ex) {
            isUserExist = false;
        }

        if (isUserExist) {
            lineInitComponent.setMainMenuToUser(user.getUserId());
            return userDetails;
        } else {
            User newUser = User.builder()
                    .firstname(user.getFirstname())
                    .lastname(user.getLastname())
                    .authorities(List.of(buyer))
//                    .password(encoder.encode(user.getPassword()))
                    .email(user.getEmail())
                    .userId(user.getUserId())
                    .profile(user.getProfile())
                    .phoneNumber(encoder.encode(user.getPhoneNumber()))
                    .name(user.getName())
                    .enabled(true)
                    .build();
            userDao.save(newUser);
            lineInitComponent.setMainMenuToUser(user.getUserId());
            return newUser;

        }
    }

    @Transactional
    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
