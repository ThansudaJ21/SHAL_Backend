package com.se.shal.product.dao;

import com.se.shal.product.entity.Variations;

import java.util.List;

public interface VariationDao {
    List<Variations> save(List<Variations> variations);
    List<Variations> updateVariations(List<Variations> variations);
    List<Variations> getVariationsByIds(List<Long> ids);
    Variations getVariationsById(Long id);
    List<Variations> findAll();
    void  deleteVariationsById(Long id);
}
