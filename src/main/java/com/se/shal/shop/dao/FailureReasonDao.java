package com.se.shal.shop.dao;

import com.se.shal.product.entity.Attribute;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;

import java.util.List;
import java.util.Optional;

public interface FailureReasonDao {
    Optional<FailureReason> findByReason(String reason);
    List<FailureReason> findAll();
}
