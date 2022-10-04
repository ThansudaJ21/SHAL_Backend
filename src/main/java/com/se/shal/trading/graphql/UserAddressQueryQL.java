package com.se.shal.trading.graphql;

import com.se.shal.trading.dto.InputUserAddressDto;
import com.se.shal.trading.dto.QuerySaveUserAddressDto;
import com.se.shal.trading.dto.QueryUserAddressDto;
import com.se.shal.trading.entity.UserAddress;
import com.se.shal.trading.service.UserAddressService;
import com.se.shal.util.ShalMapper;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserAddressQueryQL implements GraphQLQueryResolver {
    @Autowired
    UserAddressService userAddressService;

    @Transactional
    public List<QueryUserAddressDto> getUserAddressList(Long userId) {
        List<UserAddress> userAddress = userAddressService.getUserAddressByUserId(userId);
        return ShalMapper.INSTANCE.getUserAddress(userAddress);
    }

    @Transactional
    public QueryUserAddressDto getUserAddress(Long userId) {
        UserAddress userAddress = userAddressService.getUserAddress(userId);
        return ShalMapper.INSTANCE.getUserAddress(userAddress);
    }
}
