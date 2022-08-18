package com.se.shal.shop.dao;

import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.repository.FailureReasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FailureReasonDaoImpl implements FailureReasonDao{

    @Autowired
    FailureReasonRepository failureReasonRepository;
    @Override
    public Optional<FailureReason> findByReason(String reason) {
        return failureReasonRepository.findByReason(reason);
    }


    @Override
    public List<FailureReason> findAll() {
        return failureReasonRepository.findAll();
    }
}
