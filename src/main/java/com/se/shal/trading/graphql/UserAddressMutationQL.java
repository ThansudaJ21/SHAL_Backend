package com.se.shal.trading.graphql;

import com.se.shal.security.dao.UserDao;
import com.se.shal.trading.dto.InputUserAddressDto;
import com.se.shal.trading.dto.QuerySaveUserAddressDto;
import com.se.shal.trading.entity.UserAddress;
import com.se.shal.trading.service.UserAddressService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class UserAddressMutationQL implements GraphQLMutationResolver {
    @Autowired
    UserAddressService userAddressService;

    @Transactional
    public QuerySaveUserAddressDto saveUserAddress(InputUserAddressDto inputUserAddressDto) {
        UserAddress userAddress = userAddressService.saveUserAddress(inputUserAddressDto);
        return ShalMapper.INSTANCE.saveUserAddress(userAddress);
    }

    @Transactional
    public QuerySaveUserAddressDto updateUserAddress(InputUserAddressDto inputUserAddressDto) {
        UserAddress userAddress = userAddressService.saveUserAddress(inputUserAddressDto);
        return ShalMapper.INSTANCE.saveUserAddress(userAddress);
    }
}
