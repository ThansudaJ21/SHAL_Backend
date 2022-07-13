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
    UserDto findByUserId(String userId) {
        User user = userService.findByUserId(userId);
        return ShalMapper.INSTANCE.getUserDto(user);
    }
}
