package com.se.shal.product.dao;

import com.se.shal.product.entity.Attribute;

import java.util.Optional;

public interface  AttributeDao {
    Attribute save(Attribute attribute);
    Attribute findById(Long id);

    Optional<Attribute> findByName(String attribute);
}
