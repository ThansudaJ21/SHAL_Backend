package com.se.shal.product.service;

import com.se.shal.product.dto.UpdateProductAttributeDto;
import com.se.shal.product.dto.input.InputProductAttributeDto;
import com.se.shal.product.dto.input.InputUpdateProductAttributeDto;
import com.se.shal.product.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeService {
    List<ProductAttribute> save(Long productId, List<InputProductAttributeDto> productAttribute);
    List<ProductAttribute> getProductAttribute(Long productId);
    List<ProductAttribute> updateProductAttribute(Long productId, List<UpdateProductAttributeDto> productAttribute);
}
