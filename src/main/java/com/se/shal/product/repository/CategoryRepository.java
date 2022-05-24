package com.se.shal.product.repository;

import com.se.shal.product.entity.Category;
import com.se.shal.product.entity.CategoryName;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.ShipmentName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCategoryName(CategoryName categoryName);
}
