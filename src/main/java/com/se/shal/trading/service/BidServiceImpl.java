package com.se.shal.trading.service;

import com.se.shal.line.handler.LineHandler;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
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
import com.se.shal.trading.exception.BidAmountLessThanMaxBiddingException;
import com.se.shal.trading.exception.ProductTypeNotMatchException;
import com.se.shal.trading.exception.LessThanStartingBidException;
import com.se.shal.trading.exception.UserNotExistException;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.LocalTime;
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
                            if (bid.getAuctionResult().equals(AuctionResult.LOSER)) {
                                lineHandler.pushMessageForOverTaken(auction.getMaxBidding());
                            }
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
        Bid winner = bidList.get(bidList.size() - 1);
        Hibernate.initialize(winner.getUser());
        Hibernate.initialize(winner.getAuction());
        return winner;
    }

    @Scheduled(cron = "* * * * *")
    private void auctionList() {
        List<Auction> auctions = auctionDao.findAll();
        auctions.forEach(auction -> {
            LocalTime localTime = null;
            if (auction.getTimeUnitForNextAuction().equals(TimeUnit.HOUR)) {
                localTime = LocalTime.now().plusHours(auction.getNextAuction()); //get time for start next auction e.g. 13.30 pm
            }
            if (auction.getTimeUnitForNextAuction().equals(TimeUnit.MINUTE)) {
                localTime = LocalTime.now().plusMinutes(auction.getNextAuction()); //get time for start next auction e.g. 14.30 pm
            }
//            start auction add 13.30 pm
            if (LocalTime.now().equals(localTime)) {
                if (auction.getTimeUnitForAuctionPeriod().equals(TimeUnit.MINUTE)) {
                    LocalTime time = localTime.plusMinutes(auction.getAuctionPeriod()); // plus minute 13.30 pm + 1 minute = 13.31 pm
                    if (LocalTime.now().equals(time)) { // if now = 13.31 pm
                        lineHandler.pushMessageForAuctionWinner(auction.getMaxBidding()); // send message
                        lineHandler.pushMessageForSeller(auction.getMaxBidding().getShop().getUser(), auction.getMaxBidding()); // send message
                    }
                } else if (auction.getTimeUnitForAuctionPeriod().equals(TimeUnit.HOUR)) {
                    LocalTime time = localTime.plusHours(auction.getAuctionPeriod()); // plus hour 13.30 pm + 1 minute = 14.31 pm
                    if (LocalTime.now().equals(time)) { // if now = 14.31 pm
                        lineHandler.pushMessageForAuctionWinner(auction.getMaxBidding()); // send message
                        lineHandler.pushMessageForSeller(auction.getMaxBidding().getShop().getUser(), auction.getMaxBidding()); // send message
                    }
                }
                List<Bid> bidList = bidDao.findByAuctionId(auction.getId());
                bidList.forEach(bid -> {
                    if (bid.getAuctionResult().equals(AuctionResult.LOSER)) {
                        lineHandler.pushMessageForAuctionLoser(bid);
                    }
                });
            }
        });
    }

    @Transactional
    @Override
    public List<Bid> findByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId) {
        return bidDao.findBidsByUserIdOrShopIdOrAuctionId(userId, shopId, auctionId);
    }
}

