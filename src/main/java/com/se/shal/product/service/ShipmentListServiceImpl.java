package com.se.shal.product.service;

import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.ShipmentDao;
import com.se.shal.product.dao.ShipmentListDao;
import com.se.shal.product.dto.InputShipmentList;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.ShipmentList;
import com.se.shal.util.ShalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentListServiceImpl implements ShipmentListService{

    @Autowired
    ShipmentListDao shipmentListDao;

    @Autowired
    ShipmentDao shipmentDao;

    @Autowired
    ProductDao productDao;

    @Transactional
    @Override
    public ShipmentList save(Long productId, InputShipmentList shipmentList) {
        ShipmentList shipmentList1 = ShalMapper.INSTANCE.getShipmentList(shipmentList);
        Product product = productDao.findById(productId);
        List<Shipment> dsgList = shipmentList.getShipments().stream()
                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
                .collect(Collectors.toList());

        shipmentList1.setShipments(dsgList);
        shipmentList1.setProduct(product);
        return shipmentListDao.save(shipmentList1);
    }
}
