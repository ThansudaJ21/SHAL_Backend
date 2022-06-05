package com.se.shal.product.service;

import com.se.shal.product.dao.CategoryDao;
import com.se.shal.product.entity.Category;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    CategoryDao categoryDao;

    @Transactional
    @Override
    public Category getCategory(Long id) {
        Category category = categoryDao.getCategory(id);
        Hibernate.initialize(category.getAttributes());
        return category;
    }
}
