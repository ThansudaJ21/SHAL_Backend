package com.se.shal.product.dao;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.CategoryName;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.ShipmentName;
import com.se.shal.product.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDaoImpl implements CategoryDao{
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public Category findCategoryByName(String name) {
        CategoryName dsg = CategoryName.valueOf(name);
        Category output = categoryRepository.findByCategoryName(dsg);
        return output;
    }

    @Override
    public Category getCategory(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}
