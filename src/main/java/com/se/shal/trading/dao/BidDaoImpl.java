package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BidDaoImpl implements BidDao {

    @Autowired
    BidRepository bidRepository;

    @Override
    public Long countByUserIdAndAuctionId(Long userId,Long auctionId) {
        return bidRepository.countByUserIdAndAuctionId(userId,auctionId);
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }


    @Override
    public List<Bid> findByAuctionId(Long auctionId) {
        return bidRepository.findByAuctionId(auctionId);
    }

    @Override
    public List<Bid> findBidsByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId) {
        return bidRepository.findByUserIdOrShopIdOrAuctionIdOrderByLocalDateTimeDesc(userId, shopId, auctionId);
    }
}
