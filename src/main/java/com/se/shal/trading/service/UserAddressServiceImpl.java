package com.se.shal.trading.service;

import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.trading.dao.UserAddressDao;
import com.se.shal.trading.dto.InputUserAddressDto;
import com.se.shal.trading.entity.UserAddress;
import com.se.shal.trading.entity.enumeration.AddressStatus;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserAddressServiceImpl implements UserAddressService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserAddressDao userAddressDao;

    @Transactional
    @Override
    public UserAddress saveUserAddress(InputUserAddressDto inputUserAddressDto) {
        User user = userDao.findById(inputUserAddressDto.getUser());
        if (inputUserAddressDto.getId() != null) {
            UserAddress userAddress = userAddressDao.findById(inputUserAddressDto.getId());
            userAddress.setAddressName(inputUserAddressDto.getAddressName());
            userAddress.setMoo(inputUserAddressDto.getMoo());
            userAddress.setMooName(inputUserAddressDto.getMooName());
            userAddress.setHouseNumber(inputUserAddressDto.getHouseNumber());
            userAddress.setDistrict(inputUserAddressDto.getDistrict());
            userAddress.setSubDistrict(inputUserAddressDto.getSubDistrict());
            userAddress.setPostalCode(inputUserAddressDto.getPostalCode());
            userAddress.setProvince(inputUserAddressDto.getProvince());
            return userAddressDao.saveUserAddress(userAddress);
        } else {
            UserAddress newUserAddress = UserAddress.builder()
                    .addressName(inputUserAddressDto.getAddressName())
                    .moo(inputUserAddressDto.getMoo())
                    .mooName(inputUserAddressDto.getMooName())
                    .houseNumber(inputUserAddressDto.getHouseNumber())
                    .district(inputUserAddressDto.getDistrict())
                    .subDistrict(inputUserAddressDto.getSubDistrict())
                    .addressStatus(AddressStatus.ACTIVE)
                    .province(inputUserAddressDto.getProvince())
                    .postalCode(inputUserAddressDto.getPostalCode())
                    .user(user)
                    .build();
            return userAddressDao.saveUserAddress(newUserAddress);
        }
    }

    @Transactional
    @Override
    public List<UserAddress> getUserAddressByUserId(Long userId) {
        List<UserAddress> addresses = userAddressDao.findByUserId(userId);
        List<UserAddress> newAddresses = new ArrayList<>();
        addresses.forEach(address -> {
            if (!address.getAddressStatus().equals(AddressStatus.DELETE)) {
                newAddresses.add(address);
            }
        });
        return newAddresses;
    }

    @Transactional
    @Override
    public UserAddress getUserAddress(Long userAddressId) {
        UserAddress address = userAddressDao.findById(userAddressId);
        if (!address.getAddressStatus().equals(AddressStatus.DELETE)) {
            return address;
        }
        return null;
    }

    @Transactional
    @Override
    public UserAddress deleteUserAddressById(Long userAddressId) {
        UserAddress address = userAddressDao.findById(userAddressId);
        address.setAddressStatus(AddressStatus.DELETE);
        return address;
    }
}
