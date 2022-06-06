package com.se.shal.product.service;

import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.SalesInformationDao;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.SalesInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class SalesInformationServiceImpl implements SalesInformationService {
    @Autowired
    SalesInformationDao salesInformationDao;
    @Autowired
    ProductDao productDao;

    @Transactional
    @Override
    public SalesInformation save(Long productId, SalesInformation salesInformation) {
        Product product = productDao.findById(productId);
        SalesInformation newSalesInformation = SalesInformation.builder()
                .salePrice(salesInformation.getSalePrice())
                .startingBid(salesInformation.getStartingBid())
                .storage(salesInformation.getStorage())
                .auctionPeriod(salesInformation.getAuctionPeriod())
                .nextAuction(salesInformation.getNextAuction())
                .saleTypeName(salesInformation.getSaleTypeName())
                .timeUnitForAuctionPeriod(salesInformation.getTimeUnitForAuctionPeriod())
                .timeUnitForNextAuction(salesInformation.getTimeUnitForNextAuction())
                .product(product)
                .build();
        return salesInformationDao.save(newSalesInformation);
    }

    @Transactional
    @Override
    public SalesInformation getSalesInformation(Long productId, Long id) {
        Product product = productDao.findById(productId);
        SalesInformation salesInformation = salesInformationDao.getSalesInformation(id);
        if (Objects.equals(product.getId(), salesInformation.getProduct().getId())) {
            salesInformation = salesInformationDao.getSalesInformation(id);
        }
        return salesInformation;
    }

    @Transactional
    @Override
    public SalesInformation updateSalesInformation(SalesInformation salesInformation) {
        SalesInformation salesInformation1 = salesInformationDao.getSalesInformation(salesInformation.getId());
        salesInformation1.setAuctionPeriod(salesInformation.getAuctionPeriod());
        salesInformation1.setSalePrice(salesInformation.getSalePrice());
        salesInformation1.setSaleTypeName(salesInformation.getSaleTypeName());
        salesInformation1.setNextAuction(salesInformation.getNextAuction());
        salesInformation1.setStartingBid(salesInformation.getStartingBid());
        salesInformation1.setStorage(salesInformation.getStorage());
        salesInformation1.setTimeUnitForAuctionPeriod(salesInformation.getTimeUnitForAuctionPeriod());
        salesInformation1.setTimeUnitForNextAuction(salesInformation.getTimeUnitForNextAuction());
        return salesInformationDao.save(salesInformation1);
    }
}
