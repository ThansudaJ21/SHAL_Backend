package com.se.shal.shop.dao;

import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import com.se.shal.shop.repository.FailureReasonListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FailureReasonListDaoImpl implements FailureReasonListDao{
    @Autowired
    FailureReasonListRepository failureReasonListRepository;

    @Override
    public List<FailureReasonList> save(List<FailureReasonList> failureReasonList) {
        return failureReasonListRepository.saveAll(failureReasonList);
    }

}
