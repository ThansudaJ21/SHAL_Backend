package com.se.shal.product.service;

import com.se.shal.product.entity.Variations;

import java.util.List;

public interface VariationsService {
    List<Variations> save(Long productId,List<Variations> variations);
}
