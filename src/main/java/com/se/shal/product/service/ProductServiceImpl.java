package com.se.shal.product.service;

import com.se.shal.product.dao.*;
import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.dto.VariationsDto;
import com.se.shal.product.entity.*;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.ShalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao productDao;
    @Autowired
    ProductAttributeDao productAttributeDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    ShipmentDao shipmentDao;
    @Autowired
    OptionsDao optionsDao;
    @Autowired
    VariationDao variationDao;
    @Autowired
    AttributeDao attributeDao;

    @Autowired
    CategoryDao categoryDao;

    @Transactional
    @Override
    public Product saveProduct(Long shopId, Product product) {
        Shop shop = shopDao.findById(shopId);
        product.setShop(shop);

//        List<Shipment> shipments = product.getShipments().stream()
//                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
//                .collect(Collectors.toList());
//        product1.setShipments(shipments);

//
//        List<ProductAttribute> productAttributes = productAttributeDao.save(product1.getProductAttributes());
//        product1.setProductAttributes(productAttributes);


//
//        for (ProductAttribute productAttribute : product1.getProductAttributes()
//        ) {
//            Attribute attribute = attributeDao.save(productAttribute.getAttribute());
//            productAttribute.setText(productAttribute.getText());
//            productAttribute.setAttribute(attribute);
//            productAttribute.setProduct(product3);
//
//        }
//
//        product3.getShipments().forEach(shipment -> {
//            shipment.getProducts().add(product3);
//        });

        return productDao.saveProduct(product);
    }
}
