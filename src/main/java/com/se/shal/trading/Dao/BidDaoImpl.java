package com.se.shal.trading.Dao;

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
    public Bid saveBid(Bid bid) {
        return bidRepository.save(bid);
    }

    @Override
    public List<Bid> findByProductId(Long productId) {
        return bidRepository.findByProductId(productId);
    }

    @Override
    public Bid findByProductIdAndBidAmountIsLessThan(Long productId, Double bidAmount) {
        return bidRepository.findByProductIdAndBidAmountIsLessThan(productId, bidAmount);
    }
}
