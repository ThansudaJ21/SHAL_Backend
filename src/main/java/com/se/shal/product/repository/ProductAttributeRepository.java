package com.se.shal.product.repository;

import com.se.shal.product.entity.ProductAttribute;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Long> {
}
