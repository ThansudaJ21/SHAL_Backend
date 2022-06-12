package com.se.shal.product.service;

import com.se.shal.product.entity.Variations;

import java.util.List;

public interface VariationsService {
    List<Variations> save(List<Variations> variations);
    void deleteVariations(Long id);
}
