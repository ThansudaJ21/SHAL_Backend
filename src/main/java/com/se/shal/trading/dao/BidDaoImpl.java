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
    public Long countByUserId(Long userId) {
        return bidRepository.countByUserId(userId);
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
    public List<Bid> findByUserIdOrShopId(Long userId, Long shopId) {
        return bidRepository.findBidsByUserIdOrShopId(userId, shopId);
    }
}
