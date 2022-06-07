package com.se.shal.product.service;

import com.se.shal.product.dao.*;

import com.se.shal.product.dto.VariationsDto;
import com.se.shal.product.entity.*;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.ShalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    SalesInformationDao salesInformationDao;
    @Transactional
    @Override
    public Product saveProduct(Long shopId, Product product) {
        Shop shop = shopDao.findById(shopId);
        product.setShop(shop);
        product.setProductStatus(ProductStatus.ACTIVE);
        return productDao.saveProduct(product);
    }

    @Transactional
    @Override
    public Product getProduct(Long id) {
        Product product = productDao.getProduct(id);
        SalesInformation salesInformation = salesInformationDao.getSalesInformation(id);
        if (Objects.equals(product.getId(), salesInformation.getProduct().getId())) {
            salesInformation = salesInformationDao.getSalesInformation(id);
        }
        if (salesInformation.getStorage() == 0){
            product.setProductStatus(ProductStatus.SOLD);
        }
        return product;
    }

    @Transactional
    @Override
    public List<Product> getAllProduct(Long shopId) {
        Shop shop = shopDao.findById(shopId);
        List<Product> products = productDao.findAll();
        List<Product> output = new ArrayList<>();

        for (Product product : products) {
            if (Objects.equals(product.getShop().getId(), shop.getId())) {
                output.add(product);
            }
        }
        return output;
    }

    @Transactional
    @Override
    public List<Product> productFilterByCategory(String category) {
        List<Product> products = productDao.findAll();
        List<Product> output = new ArrayList<>();
        Category c = categoryDao.findCategoryByName(category);
        for (Product product : products) {

            if (Objects.equals(product.getCategory().getCategoryName(), c.getCategoryName().getCategoryName())) {
                output.add(product);
            } else {
                return null;
            }
        }
        return output;
    }

    @Transactional
    @Override
    public Product updateProduct(Product product) {
        Long id = product.getId();
        Product product1 = productDao.findById(id);
        product1.setProductName(product.getProductName());
        product1.setDetails(product.getDetails());
        product1.setCategory(product.getCategory());
        product1.setImagesPath(product.getImagesPath());
        return productDao.saveProduct(product1);
    }
}
