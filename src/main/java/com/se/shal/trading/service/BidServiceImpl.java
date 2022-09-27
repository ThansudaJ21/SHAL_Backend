package com.se.shal.trading.service;

import com.se.shal.line.handler.LineHandler;
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
import com.se.shal.trading.entity.enumeration.AuctionState;
import com.se.shal.trading.exception.BidAmountLessThanMaxBiddingException;
import com.se.shal.trading.exception.ProductTypeNotMatchException;
import com.se.shal.trading.exception.LessThanStartingBidException;
import com.se.shal.trading.exception.UserNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service

@Slf4j
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
    @Autowired
    LineHandler lineHandler;

    @Transactional
    @Override
    public Bid addBid(BidDto bidDto) {
        User user = userDao.findById(bidDto.getUserId());
        Auction auction = auctionDao.findByProductId(bidDto.getProductId());
        Long countTime = bidDao.countByUserIdAndAuctionId(bidDto.getUserId(), auction.getId());
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
                        auction.setMaxBidding(newBiding);
                        for (Bid bid : bidList) {
                            bid.setAuctionResult(AuctionResult.LOSER);
//                            if (bid.getAuctionResult().equals(AuctionResult.LOSER)) {
                            lineHandler.pushMessageForOverTaken(bidList.get(bidList.size() - 1));
//                            }
                        }
                        return newBiding;
                    }
                    throw new BidAmountLessThanMaxBiddingException(maxBidding);
                }
                throw new ProductTypeNotMatchException(auction.getProduct().getProductName());
            }
            throw new LessThanStartingBidException(auction.getStartingBid());
        }
        throw new UserNotExistException();
    }


    @Transactional
    @Override
    public Bid getAuctionWinner(Long auctionId) {
        List<Bid> bidList = bidDao.findByAuctionId(auctionId);
        if (bidList.size() != 0) {
            Bid winner = bidList.get(bidList.size() - 1);
            Hibernate.initialize(winner.getUser());
            Hibernate.initialize(winner.getAuction());
            return winner;
        }
        return bidList.get(0);
    }

    @Transactional
    @Override
    public void auctionList() {
//  set end time at the first time when run server
        List<Auction> noEndBidDateAuctions = auctionDao.getNonEndBidTimeAuction();
        noEndBidDateAuctions.forEach(auction -> {
            int time = auction.getNextAuction() + auction.getAuctionPeriod();
            auction.setEndBiddingTime(LocalDateTime.now().plus(time, auction.getTimeUnitForNextAuction()));
            auction.setNextBiddingTime(LocalDateTime.now().plus(auction.getNextAuction(), auction.getTimeUnitForNextAuction()));
        });


//   get auction list by have end time and notification is false
        List<Auction> auctions = auctionDao.findEndAuctionWithoutNotification(LocalDateTime.now());
        auctions.forEach(auction -> {
            if (auction.getAuctionTimes() > 0) {
                if (!auction.getIsNotification() && auction.getMaxBidding() != null) {
                    // if auction.getIsNotification() == false and max bidding != NULL
                    lineHandler.pushMessageForAuctionWinner(auction.getMaxBidding());
                    lineHandler.pushMessageForSeller(auction.getMaxBidding().getShop().getUser(), auction.getMaxBidding());
                    List<Bid> bidList = bidDao.findByAuctionId(auction.getId());
                    bidList.forEach(bid -> {
                        if (bid.getAuctionResult().equals(AuctionResult.LOSER)) {
                            lineHandler.pushMessageForAuctionLoser(bidList.get(bidList.size() - 1));
                        }
                    });

//                    Auction updateAuction = auctionDao.findById(auction.getId());
                    auction.getMaxBidding().setAuction(null);
                    auction.setMaxBidding(null);
                    auction.setAuctionTimes(auction.getAuctionTimes() - 1);

                    auctionDao.save(auction);

                    Product product = productDao.getProduct(auction.getProduct().getId());
                    product.setStorage(auction.getProduct().getStorage() - 1);

                    auctionDao.save(auction);
                    productDao.saveProduct(product);


                    log.info("Save auction");
                    log.info("Send message");

                    int time = auction.getNextAuction() + auction.getAuctionPeriod();
                    auction.setEndBiddingTime(LocalDateTime.now().plus(time, auction.getTimeUnitForNextAuction()));
                    auction.setNextBiddingTime(LocalDateTime.now().plus(auction.getNextAuction(), auction.getTimeUnitForNextAuction()));
                } else if (auction.getMaxBidding() == null) {
                    int time = auction.getNextAuction() + auction.getAuctionPeriod();
                    auction.setEndBiddingTime(LocalDateTime.now().plus(time, auction.getTimeUnitForNextAuction()));
                    auction.setNextBiddingTime(LocalDateTime.now().plus(auction.getNextAuction(), auction.getTimeUnitForNextAuction()));
                }
            }
        });

//        List<Auction> auctions1 = auctionDao.findNextAuction(LocalDateTime.now());
//        auctions1.forEach(auction -> {
//            if (LocalDateTime.now().isBefore(auction.getNextBiddingTime())) {
//                auction.setCurrentAuctionState(AuctionState.WAITING_FOR_AUCTION);
//            } else if (LocalDateTime.now().isEqual(auction.getNextBiddingTime())) {
//                auction.setCurrentAuctionState(AuctionState.AUCTIONING);
//            }
//        });
    }

    @Transactional
    @Override
    public List<Bid> findByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId) {
        return bidDao.findBidsByUserIdOrShopIdOrAuctionId(userId, shopId, auctionId);
    }
}

