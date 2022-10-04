package com.se.shal.trading.dao;

import com.se.shal.trading.entity.UserAddress;

import java.util.List;

public interface UserAddressDao {

    UserAddress saveUserAddress(UserAddress userAddress);

    UserAddress findById(Long id);
    List<UserAddress> findByUserId(Long userId);
}
