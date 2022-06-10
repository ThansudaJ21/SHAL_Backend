package com.se.shal.shop.dao;

import com.se.shal.product.entity.Attribute;
import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.FailureReasonList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface FailureReasonListDao {
    List<FailureReasonList> save(List<FailureReasonList> failureReasonList);
}
