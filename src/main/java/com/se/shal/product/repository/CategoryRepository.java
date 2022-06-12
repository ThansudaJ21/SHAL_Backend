package com.se.shal.product.repository;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.enumeration.CategoryName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(CategoryName categoryName);
}
