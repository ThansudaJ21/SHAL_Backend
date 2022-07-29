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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
    public Bid addBid(BidDto bid) {
        User user = userDao.findById(bid.getUserId());
//        Auction auction = auctionDao.findByProductId(bid.getProductId());
        Product product = productDao.getProduct(bid.getProductId());
        Long countTime = bidDao.countByUserId(bid.getUserId());
        Shop shop = shopDao.findById(bid.getShopId());
        if (user != null) {
            if (product.getSaleTypeName().equals(SaleTypeName.AUCTION) || product.getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
                Bid newBiding = Bid.builder()
                        .auctionResult(AuctionResult.WINNER)
                        .localDateTime(LocalDateTime.now())
                        .times(Math.toIntExact(countTime) + 1)
                        .user(user)
                        .product(product)
                        .bidAmount(bid.getBidAmount())
                        .shop(shop)
                        .build();
                bidDao.saveBid(newBiding);
                product.setCurrentBid(newBiding);
                productDao.saveProduct(product);
                return newBiding;
            }
        }
        return null;
    }


    //    @Transactional
//    @Override
//    public Auction addAuction(AuctionDto auction) {
//        Product product = productDao.getProduct(auction.getProductId());
//        User user = userDao.findById(auction.getUserId());
//        Long countTime = auctionDao.countByProductIdAndUserId(auction.getProductId(), auction.getUserId());
//        List<Long> variationsList = auction.getVariationsList();
//        Shop shop = shopDao.findById(auction.getShop());
//        List<Long> optionsList = auction.getOptionsList();
//        if (product.getSaleTypeName().equals(SaleTypeName.AUCTION) || product.getSaleTypeName().equals(SaleTypeName.AUCTIONANDSALE)) {
//            Auction newAuction = Auction.builder()
//                    .auctionResult(AuctionResult.WINNER)
//                    .localDateTime(LocalDateTime.now())
//                    .times(Math.toIntExact(countTime) + 1)
//                    .product(product)
//                    .bidAmount(auction.getBidAmount())
//                    .user(user)
//                    .variationsList(variationDao.findByIds(variationsList))
//                    .optionsList(optionsDao.findByIds(optionsList))
//                    .shop(shop)
//                    .orderStatus(O/rderStatus.AUCTION)
//                    .auctionPeriod(auction.getAuctionPeriod())
//                    .build();
//            return auctionDao.save(newAuction);
//        } else {
//            return null;
//        }
//    }

//    @Transactional
//    @Override
//    public Auction checkCurrentBid(Long productId, Double bidAmount) {
//        List<Auction> auctionList = auctionDao.findByProductId(productId);
//        for (Auction auction : auctionList) {
//
//        }
//        return null;
//    }
//
//    @Transactional
//    @Override
//    public List<Auction> findByUserIdOrProductIdOrShopId(Long userId, Long productId, Long shopId) {
//        return auctionDao.findByUserIdOrProductIdOrShopId(userId, productId, shopId);
//    }
}
