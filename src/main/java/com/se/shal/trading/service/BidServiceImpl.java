package com.se.shal.trading.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.security.dao.UserDao;
import com.se.shal.security.entity.User;
import com.se.shal.shop.dao.ShopDao;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.Dao.AuctionDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.trading.Dao.BidDao;
import com.se.shal.trading.dto.BidDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import com.se.shal.trading.exception.BidAmountException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.se.shal.product.entity.Product_.auction;

@Service
public class BidServiceImpl implements BidService {

    @Autowired
    AuctionDao auctionDao;
    @Autowired
    ProductDao productDao;
    @Autowired
    UserDao userDao;
    @Autowired
    VariationDao variationDao;
    @Autowired
    OptionsDao optionsDao;
    @Autowired
    ShopDao shopDao;
    @Autowired
    BidDao bidDao;

    @Transactional
    @Override
    public Bid addBid(BidDto bidDto) {
        User user = userDao.findById(bidDto.getUserId());
        Product product = productDao.getProduct(bidDto.getProductId());
        Long countTime = bidDao.countByUserId(bidDto.getUserId());
        Shop shop = shopDao.findById(bidDto.getShopId());
        Double maxBidding = getMaxBidding(bidDto.getProductId());
        if (user != null) {
            if (product.getSaleTypeName().equals(SaleTypeName.AUCTION) || product.getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
                if (maxBidding <= bidDto.getBidAmount()) {
                    Bid newBiding = Bid.builder()
                            .auctionResult(AuctionResult.WINNER)
                            .localDateTime(LocalDateTime.now())
                            .times(Math.toIntExact(countTime) + 1)
                            .user(user)
                            .product(product)
                            .bidAmount(bidDto.getBidAmount())
                            .shop(shop)
                            .build();
                    bidDao.saveBid(newBiding);
                    product.setCurrentBid(newBiding);
                    productDao.saveProduct(product);
                    return newBiding;
                } else {
                    throw new BidAmountException(bidDto.getBidAmount());
                }
            }
        }
        return null;
    }

    @Transactional
    @Override
    public Bid getCurrentBid(Long productId) {
        Product product = productDao.getProduct(productId);
        Hibernate.initialize(product.getCurrentBid());
        return product.getCurrentBid();
    }

    public List<Bid> setLoserStatus(List<Bid> bidList, Double currentBid) {
        for (Bid bid : bidList) {
            if (currentBid > bid.getBidAmount()) {
                bid.setAuctionResult(AuctionResult.LOSER);
                bidDao.saveBid(bid);
            }
        }
        return bidList;
    }


    private Double getMaxBidding(Long productId) {
        double max = Double.MIN_VALUE;
        List<Bid> bidList = bidDao.findByProductId(productId);
        for (Bid bid : bidList) {
            max = Double.max(bid.getBidAmount(), max);
        }
        return max;
    }
}
