package com.se.shal.trading.service;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.trading.Dao.AuctionDao;
import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.trading.entity.AuctionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    AuctionDao auctionDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;

    @Transactional
    @Override
    public Auction saveAuction(AuctionDto auction) {
        Product product = productDao.getProduct(auction.getProductId());
        User user = userDao.findById(auction.getUserId());

//        Long countTime = examResultDao.countByPatientId(patientId);
//        examResult.setTimes(Math.toIntExact(countTime) +1 );
        if (product.getSaleTypeName().equals(SaleTypeName.AUCTION) || product.getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
            Auction newAuction = Auction.builder()
                    .auctionResult(AuctionResult.WINNER)
                    .bid(auction.getBid())
                    .localDateTime(LocalDateTime.now())
                    .times(1)
                    .product(product)
                    .bidAmount(auction.getBidAmount())
                    .user(user)
                    .build();
            return auctionDao.save(newAuction);
        } else {
            return null;
        }
    }

    @Transactional
    @Override
    public List<Auction> checkBidAmount(Long productId, Double bidAmount) {
        return auctionDao.findByProductId(productId);
    }

    @Transactional
    @Override
    public List<Auction> getAuctionByProductId(Long productId) {
        return auctionDao.findByProductId(productId);
    }
}
