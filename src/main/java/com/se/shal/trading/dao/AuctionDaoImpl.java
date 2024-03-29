package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.repository.AuctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuctionDaoImpl implements AuctionDao {
    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public Auction findById(Long id) {
        return auctionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public Auction save(Auction auction) {
        return auctionRepository.save(auction);
    }

    @Override
    public List<Auction> findByMaxBiddingId(Long maxBiddingId) {
        return auctionRepository.findByMaxBiddingId(maxBiddingId);
    }

    @Override
    public Auction findByProductId(Long productId) {
        return auctionRepository.findByProductId(productId);
    }


}
