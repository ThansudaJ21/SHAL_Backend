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
import com.se.shal.trading.dto.AuctionDto;
import com.se.shal.trading.entity.Auction;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.trading.entity.enumeration.AuctionResult;
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
    @Autowired
    VariationDao variationDao;
    @Autowired
    OptionsDao optionsDao;
    @Autowired
    ShopDao shopDao;

    @Transactional
    @Override
    public Auction auction(AuctionDto auction) {
        Product product = productDao.getProduct(auction.getProductId());
        User user = userDao.findById(auction.getUserId());
        Long countTime = auctionDao.countByProductIdAndUserId(auction.getProductId(), auction.getUserId());
        List<Long> variationsList = auction.getVariationsList();
        Shop shop = shopDao.findById(auction.getShop());
        List<Long> optionsList = auction.getOptionsList();
        if (product.getSaleTypeName().equals(SaleTypeName.AUCTION) || product.getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
            Auction newAuction = Auction.builder()
                    .auctionResult(AuctionResult.WINNER)
                    .localDateTime(LocalDateTime.now())
                    .times(Math.toIntExact(countTime) +1 )
                    .product(product)
                    .bidAmount(auction.getBidAmount())
                    .user(user)
                    .variationsList(variationDao.findByIds(variationsList))
                    .optionsList(optionsDao.findByIds(optionsList))
                    .shop(shop)
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
