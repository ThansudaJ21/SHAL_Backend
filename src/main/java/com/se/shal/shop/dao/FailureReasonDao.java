package com.se.shal.shop.dao;

import com.se.shal.product.entity.Attribute;
import com.se.shal.shop.entity.FailureReason;

import java.util.Optional;

public interface FailureReasonDao {
    Optional<FailureReason> findByReason(String reason);
}
