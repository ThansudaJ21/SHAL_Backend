package com.se.shal.product.dao;

import com.se.shal.product.entity.ProductAttribute;
import com.se.shal.product.repository.ProductAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductAttributeDaoImpl implements ProductAttributeDao {
    @Autowired
    ProductAttributeRepository attributeRepository;


    @Override
    public List<ProductAttribute> save(List<ProductAttribute> productAttributes) {
        return attributeRepository.saveAll(productAttributes);
    }
}
