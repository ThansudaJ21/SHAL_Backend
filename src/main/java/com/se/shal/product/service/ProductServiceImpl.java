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
    public Product saveProduct(Long shopId, InputProductDto product) {
        Product product1 = ShalMapper.INSTANCE.getProduct(product);

        Shop shop = shopDao.findById(shopId);
        product1.setShop(shop);

        List<Shipment> shipments = product.getShipments().stream()
                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
                .collect(Collectors.toList());
        product1.setShipments(shipments);

        List<Variations> variations = variationDao.save(product1.getVariations());
        product1.setVariations(variations);

        List<ProductAttribute> productAttributes = productAttributeDao.save(product1.getProductAttributes());
        product1.setProductAttributes(productAttributes);

        Product product3 = productDao.saveProduct(product1);

        for (Variations variations1 : product1.getVariations()
        ) {
            List<Options> options = optionsDao.save(variations1.getOptions());
            variations1.setProduct(product3);
            variations1.setName(variations1.getName());
            variations1.setOptions(options);
        }

        for (ProductAttribute productAttribute : product1.getProductAttributes()
        ) {
            Attribute attribute = attributeDao.save(productAttribute.getAttribute());
            productAttribute.setText(productAttribute.getText());
            productAttribute.setAttribute(attribute);
            productAttribute.setProduct(product3);

        }

        product3.getShipments().forEach(shipment -> {
            shipment.getProducts().add(product3);
        });

        return product3;
    }
}
