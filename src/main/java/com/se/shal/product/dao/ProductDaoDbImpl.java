package com.se.shal.product.dao;

import com.google.common.base.Strings;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Product_;
import com.se.shal.product.entity.Variations;
import com.se.shal.product.entity.enumeration.CategoryName;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.graphql.entity.ProductFilter;
import com.se.shal.product.repository.ProductRepository;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.entity.Shop_;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("db")
public class ProductDaoDbImpl implements ProductDao{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product getProduct(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Page<Product> filterProduct(ProductFilter productFilter, PageRequest pageRequest) {
        Specification<Product> specification = getProductPredicate(productFilter);
        try {
            return productRepository.findAll(specification, pageRequest);
        } catch (IllegalArgumentException  | InvalidDataAccessApiUsageException ex) {
            return null;
        }
    }

    Specification<Product> getProductPredicate(ProductFilter filter) {
        return (Root<Product> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getProductName() != null) {
                predicates.add(cb.like(root.get(Product_.PRODUCT_NAME), "%" + filter.getProductName() + "%"));
            }
            if (!Strings.isNullOrEmpty(filter.getProductStatus())) {
                ProductStatus enumResult = ProductStatus.valueOf(filter.getProductStatus());
                predicates.add(cb.equal(root.get(Product_.PRODUCT_STATUS), enumResult));
            }
            if (!Strings.isNullOrEmpty(filter.getCategory())) {
                CategoryName enumResult = CategoryName.valueOf(filter.getCategory());
                predicates.add(cb.equal(root.get(Product_.CATEGORY), enumResult));
            }
            if (!Strings.isNullOrEmpty(filter.getSaleTypeName())) {
                SaleTypeName enumResult = SaleTypeName.valueOf(filter.getSaleTypeName());
                predicates.add(cb.equal(root.get(Product_.SALE_TYPE_NAME), enumResult));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Override
    public List<Product> findByShopId(Long shopId) {
        return productRepository.findByShopId(shopId);
    }
}
