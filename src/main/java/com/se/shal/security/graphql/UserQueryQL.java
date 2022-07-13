package com.se.shal.security.graphql;

import com.se.shal.security.entity.User;
import com.se.shal.security.entity.UserDto;
import com.se.shal.security.service.UserService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserQueryQL implements GraphQLQueryResolver {
    @Autowired
    UserService userService;

    @Transactional
    UserDto findByEmail(String email) {
        User user = userService.findByEmail(email);
        return ShalMapper.INSTANCE.getUserDto(user);
    }
}
