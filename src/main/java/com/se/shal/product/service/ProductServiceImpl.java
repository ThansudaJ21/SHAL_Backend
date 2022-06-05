package com.se.shal.product.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.ShipmentDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.dto.InputProductDto;
import com.se.shal.product.dto.VariationsDto;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.Variations;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.ShalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{
    @Autowired
    ProductDao productDao;

    @Autowired
    ShopDao shopDao;
    @Autowired
    ShipmentDao shipmentDao;
@Autowired
    OptionsDao optionsDao;
    @Autowired
    VariationDao variationDao;

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


        Product product3 = productDao.saveProduct(product1);

        for (Variations variations1 : product1.getVariations()
        ) {
            List<Options> options = optionsDao.save(variations1.getOptions());
            variations1.setProduct(product3);
            variations1.setName(variations1.getName());
            variations1.setOptions(options);
        }

        product3.getShipments().forEach(shipment -> {
            shipment.getProducts().add(product3);
        });

        return product3;
    }
}
