package com.se.shal.security.graphql;

import com.se.shal.security.entity.User;
import com.se.shal.security.service.UserService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class RegisterNewUserMutation implements GraphQLMutationResolver {
    @Autowired
    UserService userService;

    @Transactional
    public User registerUser(User user) {
        User newUser = userService.registerNewUser(user);
        Hibernate.initialize(newUser.getAuthorities());
        return newUser;
    }
}
