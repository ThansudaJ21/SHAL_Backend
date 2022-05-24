package com.se.shal.product.dao;

import com.se.shal.product.entity.Attribute;
import com.se.shal.product.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AttributeDaoImpl implements AttributeDao{
    @Autowired
    AttributeRepository attributeRepository;

    @Override
    public Attribute save(Attribute attribute) {
        return attributeRepository.save(attribute);
    }
}
