package com.se.shal.trading.dao;

import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import com.se.shal.trading.repository.BidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BidDaoImpl implements BidDao {

    final BidRepository bidRepository;

    @Override
    public Long countByUserIdAndAuctionId(Long userId, Long auctionId) {
        return bidRepository.countByUserIdAndAuctionId(userId, auctionId);
    }

    @Override
    public Bid save(Bid bid) {
        return bidRepository.save(bid);
    }


    @Override
    public List<Bid> findByAuctionIdAndTimes(Long auctionId, Integer time) {
        return bidRepository.findByAuctionIdAndTimes(auctionId, time);
    }

    @Override
    public List<Bid> findBidsByUserIdOrShopIdOrAuctionId(Long userId, Long shopId, Long auctionId) {
        return bidRepository.findByUserIdOrShopIdOrAuctionIdOrderByLocalDateTimeDesc(userId, shopId, auctionId);
    }

    @Override
    public List<Bid> findAllBid() {
        return bidRepository.findAll();
    }

    @Override
    public Bid findById(Long id) {
        return bidRepository.findById(id).orElse(null);
    }

    @Override
    public Bid findByAuctionIdAndAuctionResult(Long auctionId) {
        return bidRepository.findByAuctionIdAndAuctionResult(auctionId, AuctionResult.WINNER);
    }

    @Override
    public List<Bid> findByAuctionId(Long auctionId) {
        return bidRepository.findByAuctionId(auctionId);
    }
}
