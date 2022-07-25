package com.se.shal.product.repository;

import com.se.shal.product.entity.Variations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VariationRepository extends JpaRepository<Variations, Long> {
    List<Variations> findByIdIn(List<Long> ids);

}
