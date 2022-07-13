package com.se.shal.security.service;

import com.se.shal.line.config.LineInitComponent;
import com.se.shal.security.JwtTokenUtil;
import com.se.shal.security.dao.AuthorityDao;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import com.se.shal.security.entity.User;
import com.se.shal.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            userDetails = userDao.findByUserId(user.getUserId());
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
                    .pictureUrl(user.getPictureUrl())
                    .phoneNumber(encoder.encode(user.getPhoneNumber()))
                    .displayName(user.getDisplayName())
                    .enabled(true)
                    .build();
            userDao.save(newUser);
            lineInitComponent.setMainMenuToUser(user.getUserId());
            return newUser;

        }
    }

    @Transactional
    @Override
    public User findByUserId(String userId) {
        return userDao.findByUserId(userId);
    }

}
