package com.se.shal.product.service;

import com.se.shal.product.dao.*;

import com.se.shal.product.dto.input.InputProductDto;
import com.se.shal.product.dto.input.InputUpdateProductDto;
import com.se.shal.product.entity.*;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.graphql.entity.ProductFilter;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.ShalMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    ShipmentDao shipmentDao;
    @Autowired
    VariationDao variationDao;
    @Autowired
    OptionsDao optionsDao;
    @Autowired
    AttributeDao attributeDao;
    @Autowired
    ProductAttributeDao productAttributeDao;

    @Transactional
    @Override
    public Product saveProduct(Long shopId, InputProductDto product) {
        Product newProduct = ShalMapper.INSTANCE.saveProduct(product);
        Shop shop = shopDao.findById(shopId);

        List<Shipment> dsgList = product.getShipments().stream()
                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
                .collect(Collectors.toList());

        newProduct.setShipments(dsgList);
        newProduct.setShop(shop);
        newProduct.setProductStatus(ProductStatus.ACTIVE);

        List<Variations> newVariations = variationDao.save(newProduct.getVariations());
        List<Variations> variations = new ArrayList<>();
        for (Variations variations1 : newVariations
        ) {
            List<Options> options = optionsDao.save(variations1.getOptions());
            variations1.setVariationName(variations1.getVariationName());
            variations1.setOptions(options);
            variations.add(variations1);
        }

        List<ProductAttribute> output = new ArrayList<>();
        for (ProductAttribute productInput : newProduct.getProductAttribute()) {
            attributeDao.findByName(productInput.getAttribute().getAttribute())
                    .ifPresentOrElse(
                            (attribute) -> {
                                output.add(ProductAttribute.builder()
                                        .attribute(attribute)
                                        .text(productInput.getText())
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        List<ProductAttribute> p = productAttributeDao.save(output);
        newProduct.setProductAttribute(p);
        newProduct.setVariations(variations);
        Product product1 = productDao.saveProduct(newProduct);
        return product1;
    }

    @Transactional
    @Override
    public Product getProduct(Long id) {
        Product product = productDao.getProduct(id);
        Hibernate.initialize(product.getVariations());
        Hibernate.initialize(product.getProductAttribute());
        Hibernate.initialize(product.getShipments());
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
                Hibernate.initialize(product.getVariations());
                Hibernate.initialize(product.getProductAttribute());
                Hibernate.initialize(product.getShipments());
                output.add(product);
            }
        }
        return output;
    }



    @Transactional
    @Override
    public Product updateProduct(InputUpdateProductDto product) {
        Product newProduct = ShalMapper.INSTANCE.updateProduct(product);
        Long id = newProduct.getId();
        Product product1 = productDao.findById(id);

        List<Shipment> dsgList = product.getShipments().stream()
                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
                .collect(Collectors.toList());

        List<Variations> updatedVariations = new ArrayList<>();
        for (Variations variations1 : newProduct.getVariations()
        ) {
            Variations variations = variationDao.findById(variations1.getId());
            for (Options options : variations.getOptions()) {
                Options options1 = optionsDao.findById(options.getId());
                options1.setOptionName(options.getOptionName());
                options1.setPrice(options.getPrice());
                options1.setStock(options.getStock());
                options1.setImage(options.getImage());
            }
            variations.setVariationName(variations1.getVariationName());
            updatedVariations.add(variations);
        }

        List<Variations> var = variationDao.save(updatedVariations);

        List<ProductAttribute> output = new ArrayList<>();
        for (ProductAttribute productInput : newProduct.getProductAttribute()) {
            attributeDao.findByName(productInput.getAttribute().getAttribute())
                    .ifPresentOrElse(
                            (attribute) -> {
                                output.add(ProductAttribute.builder()
                                        .attribute(attribute)
                                        .text(productInput.getText())
                                        .build());
                            },
                            () -> {
                                throw new RuntimeException();
                            }
                    );
        }
        List<ProductAttribute> p = productAttributeDao.save(output);
        product1.setProductAttribute(p);
        product1.setVariations(var);
        product1.setShipments(dsgList);

        product1.setProductStatus(newProduct.getProductStatus());
        product1.setProductName(newProduct.getProductName());
        product1.setDetails(newProduct.getDetails());
        product1.setImagesPath(newProduct.getImagesPath());
        product1.setCategory(newProduct.getCategory());
        product1.setNextAuction(newProduct.getNextAuction());
        product1.setSalePrice(newProduct.getSalePrice());
        product1.setStartingBid(newProduct.getStartingBid());
        product1.setStorage(newProduct.getStorage());
        product1.setAuctionPeriod(newProduct.getAuctionPeriod());
        product1.setSaleTypeName(newProduct.getSaleTypeName());
        product1.setTimeUnitForNextAuction(newProduct.getTimeUnitForNextAuction());
        product1.setTimeUnitForAuctionPeriod(newProduct.getTimeUnitForAuctionPeriod());

        Product product2 = productDao.saveProduct(product1);
        return product2;
    }

    @Transactional
    @Override
    public Product updateProductStatus(Product product) {
        Product product1 = productDao.findById(product.getId());
        if (product1.getProductStatus().equals(ProductStatus.ACTIVE)) {
            product1.setProductStatus(ProductStatus.HIDDEN);
        } else if (product1.getProductStatus().equals(ProductStatus.HIDDEN)) {
            product1.setProductStatus(ProductStatus.ACTIVE);
        }
        Hibernate.initialize(product1.getVariations());
        Hibernate.initialize(product1.getProductAttribute());
        Hibernate.initialize(product1.getShipments());
        return product1;
    }


    @Transactional
    @Override
    public Page<Product> productFilter(ProductFilter productFilter, PageRequest pageRequest) {
        return productDao.filterProduct(productFilter, pageRequest);
    }
}
