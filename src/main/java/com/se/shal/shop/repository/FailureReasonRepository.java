package com.se.shal.shop.repository;

import com.se.shal.product.entity.Attribute;
import com.se.shal.shop.entity.FailureReason;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FailureReasonRepository extends JpaRepository<FailureReason, Long> {
    Optional<FailureReason> findByReason(String reason);
}
