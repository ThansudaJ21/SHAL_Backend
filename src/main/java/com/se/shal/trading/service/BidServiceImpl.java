package com.se.shal.trading.service;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.dao.AuctionDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.trading.dao.BidDao;
import com.se.shal.trading.dto.BidDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import com.se.shal.trading.exception.BidAmountException;
import com.se.shal.trading.exception.ProductTypeException;
import com.se.shal.trading.exception.StartingBidException;
import com.se.shal.trading.exception.UserExistException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    AuctionDao auctionDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    BidDao bidDao;

    @Transactional
    @Override
    public Bid addBid(BidDto bidDto) {
        User user = userDao.findById(bidDto.getUserId());
        Auction auction = auctionDao.findByProductId(bidDto.getProductId());
        Long countTime = bidDao.countByUserId(bidDto.getUserId());
        Shop shop = shopDao.findById(bidDto.getShopId());
        Double maxBidding = auction.getMaxBidding() == null ? 0.0 : auction.getMaxBidding().getBidAmount();
        List<Bid> bidList = bidDao.findByAuctionId(auction.getId());
        if (user != null) {
            if (bidDto.getBidAmount() > auction.getStartingBid()) {
                if (auction.getProduct().getSaleTypeName().equals(SaleTypeName.AUCTION) || auction.getProduct().getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
                    if (bidDto.getBidAmount() > maxBidding) {
                        Bid newBiding = Bid.builder()
                                .auctionResult(AuctionResult.WINNER)
                                .localDateTime(LocalDateTime.now())
                                .times(Math.toIntExact(countTime) + 1)
                                .user(user)
                                .bidAmount(bidDto.getBidAmount())
                                .shop(shop)
                                .auction(auction)
                                .build();

                        bidDao.save(newBiding);

                        for (Bid bid : bidList) {
                            bid.setAuctionResult(AuctionResult.LOSER);
                        }
                        auction.setMaxBidding(newBiding);
                        return newBiding;

                    }

                    throw new BidAmountException(maxBidding);
                }
                throw new ProductTypeException(auction.getProduct().getProductName());
            }
            throw new StartingBidException(auction.getStartingBid());
        }
        throw new UserExistException();
    }

    @Transactional
    @Override
    public Bid getCurrentBid(Long productId) {
        Product product = productDao.getProduct(productId);
//        Hibernate.initialize(product.getCurrentBid());
        return null;
    }


    private Double getMaxBidding(Long productId) {
        double max = Double.MIN_VALUE;
//        List<Bid> bidList = bidDao.findByProductId(productId);
//        for (Bid bid : bidList) {
//            max = Double.max(bid.getBidAmount(), max);
//        }
        return max;
    }

    @Transactional
    @Override
    public List<Bid> findByUserIdOrShopId(Long userId, Long shopId) {
        return bidDao.findByUserIdOrShopId(userId, shopId);
    }
}

