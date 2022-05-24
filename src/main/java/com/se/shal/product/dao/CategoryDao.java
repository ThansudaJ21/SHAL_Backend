package com.se.shal.product.dao;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.Shipment;

public interface CategoryDao {
    Category findCategoryByName(String name);
    Category getCategory(Long id);
}
