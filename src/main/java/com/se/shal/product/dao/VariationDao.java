package com.se.shal.product.dao;

import com.se.shal.product.entity.Variations;

import java.util.List;

public interface VariationDao {
    List<Variations> save(List<Variations> variations);
    Variations findById(Long id);
    void deleteVariationsById(Long id);
}
