package com.se.shal.trading.service;

import com.se.shal.trading.dto.InputUserAddressDto;
import com.se.shal.trading.entity.ProductOrder;
import com.se.shal.trading.entity.UserAddress;

import java.util.List;

public interface UserAddressService {
    UserAddress saveUserAddress(InputUserAddressDto inputUserAddressDto);

    List<UserAddress> getUserAddressByUserId(Long userId);

    UserAddress getUserAddress(Long userAddressId);

    UserAddress deleteUserAddressById(Long userAddressId);

}
