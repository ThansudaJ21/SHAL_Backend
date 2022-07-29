package com.se.shal.trading.Dao;

import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.repository.BidRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
