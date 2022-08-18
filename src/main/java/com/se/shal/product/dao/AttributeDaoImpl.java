package com.se.shal.product.dao;

import com.se.shal.product.entity.Attribute;
import com.se.shal.product.repository.AttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class AttributeDaoImpl implements AttributeDao {
    @Autowired
    AttributeRepository attributeRepository;

    @Override
    public Optional<Attribute> findByName(String attribute) {
        return attributeRepository.findByAttribute(attribute);
    }
}
