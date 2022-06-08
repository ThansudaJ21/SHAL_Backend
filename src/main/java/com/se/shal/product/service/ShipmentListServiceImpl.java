package com.se.shal.product.service;

import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.ShipmentDao;
import com.se.shal.product.dao.ShipmentListDao;
import com.se.shal.product.dto.input.InputShipmentList;
import com.se.shal.product.dto.input.InputUpdateShipmentList;
import com.se.shal.product.dto.input.UpdateShipmentList;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.ShipmentList;
import com.se.shal.util.ShalMapper;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
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

    @Transactional
    @Override
    public ShipmentList getShipmentLists(Long productId, Long id) {
        Product product = productDao.findById(productId);
        ShipmentList shipmentList = shipmentListDao.findById(id);
        if (Objects.equals(product.getId(), shipmentList.getProduct().getId())){
            shipmentList = shipmentListDao.getShipmentList(id);
            Hibernate.initialize(shipmentList.getShipments());
        }
        return shipmentList;
    }

    @Transactional
    @Override
    public ShipmentList updateShipmentLists(UpdateShipmentList shipmentList) {
        ShipmentList shipmentList1 = ShalMapper.INSTANCE.getUpdateShipmentList(shipmentList);
        ShipmentList shipmentList2 = shipmentListDao.getShipmentList(shipmentList1.getId());
        List<Shipment> dsgList = shipmentList.getShipments().stream()
                .map(dsdName -> shipmentDao.findShipmentByName(dsdName))
                .collect(Collectors.toList());

        shipmentList2.setShipments(dsgList);
        return shipmentListDao.save(shipmentList2);
    }
}
