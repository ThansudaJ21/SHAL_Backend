package com.se.shal.product.dao;

import com.se.shal.product.entity.Attribute;

import java.util.Optional;

public interface  AttributeDao {
    Optional<Attribute> findByName(String attribute);
}
