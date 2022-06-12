package com.se.shal.product.service;

import com.se.shal.product.dto.input.InputProductAttributeDto;
import com.se.shal.product.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeService {
    List<ProductAttribute> save( List<InputProductAttributeDto> productAttribute);
}
