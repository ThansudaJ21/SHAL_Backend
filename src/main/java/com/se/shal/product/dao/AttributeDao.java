package com.se.shal.product.dao;

import com.se.shal.product.entity.Attribute;

public interface  AttributeDao {
    Attribute save(Attribute attribute);
    Attribute findById(Long id);

}
