package com.se.shal.product.repository;

import com.se.shal.product.entity.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
