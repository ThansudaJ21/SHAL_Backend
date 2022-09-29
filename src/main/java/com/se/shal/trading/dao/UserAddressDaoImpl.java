package com.se.shal.trading.dao;

import com.se.shal.trading.entity.UserAddress;
import com.se.shal.trading.repository.UserAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAddressDaoImpl implements UserAddressDao {

    @Autowired
    UserAddressRepository userAddressRepository;

    @Override
    public UserAddress saveUserAddress(UserAddress userAddress) {
        return userAddressRepository.save(userAddress);
    }

    @Override
    public UserAddress findById(Long id) {
        return userAddressRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserAddress> findByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId);
    }
}
