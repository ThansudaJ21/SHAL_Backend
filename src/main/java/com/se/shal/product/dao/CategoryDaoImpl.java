package com.se.shal.product.dao;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.enumeration.CategoryName;
import com.se.shal.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
