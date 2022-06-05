package com.se.shal.product.service;

import com.se.shal.product.dao.AttributeDao;
import com.se.shal.product.dao.ProductAttributeDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dto.InputProductAttributeDto;
import com.se.shal.product.entity.Attribute;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.ProductAttribute;
import com.se.shal.product.entity.ShipmentList;
import com.se.shal.util.ShalMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductAttributeServiceImpl implements ProductAttributeService {
    @Autowired
    ProductAttributeDao productAttributeDao;

    @Autowired
    ProductDao productDao;

    @Autowired
    AttributeDao attributeDao;

    @Transactional
    @Override
    public List<ProductAttribute> save(Long productId, List<InputProductAttributeDto> productAttributes) {

        Product product = productDao.findById(productId);
        List<ProductAttribute> output = new ArrayList<>();
        for (InputProductAttributeDto productInput : productAttributes) {
            attributeDao.findByName(productInput.getAttribute().getAttribute())
                    .ifPresentOrElse(
                            (attribute) -> {
                                output.add(ProductAttribute.builder()
                                        .attribute(attribute)
                                        .text(productInput.getText())
                                        .product(product)
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        return productAttributeDao.save(output);
    }

    @Transactional
    @Override
    public List<ProductAttribute> getProductAttribute(Long productId) {
        List<ProductAttribute> productAttribute = productAttributeDao.findAll();

        List<ProductAttribute> output = new ArrayList<>();

        for (ProductAttribute productAttribute1 : productAttribute) {
            if (Objects.equals(productAttribute1.getProduct().getId(), productId)){
                output.add(productAttribute1);
            }
        }
       return output;
    }
}
