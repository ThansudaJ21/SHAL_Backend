package com.se.shal.product.service;

import com.se.shal.product.dao.AttributeDao;
import com.se.shal.product.dao.ProductAttributeDao;
import com.se.shal.product.dto.input.InputProductAttributeDto;
import com.se.shal.product.entity.ProductAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    @Autowired
    ProductAttributeDao productAttributeDao;
    @Autowired
    AttributeDao attributeDao;

    @Transactional
    @Override
    public List<ProductAttribute> save(List<InputProductAttributeDto> productAttributes) {
        List<ProductAttribute> output = new ArrayList<>();
        for (InputProductAttributeDto productInput : productAttributes) {

            attributeDao.findByName(productInput.getAttribute().getAttribute())
                    .ifPresentOrElse(
                            (attribute) -> {
                                output.add(ProductAttribute.builder()
                                        .attribute(attribute)
                                        .text(productInput.getText())
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        return productAttributeDao.save(output);
    }
}
