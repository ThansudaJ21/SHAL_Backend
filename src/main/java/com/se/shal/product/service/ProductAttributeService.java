package com.se.shal.product.service;

import com.se.shal.product.dto.InputProductAttributeDto;
import com.se.shal.product.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeService {
    List<ProductAttribute> save(Long productId, List<InputProductAttributeDto> productAttribute);
}
