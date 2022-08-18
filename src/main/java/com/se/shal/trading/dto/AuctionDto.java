package com.se.shal.trading.dto;

import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionDto {
    Long id;
    Integer nextAuction;
    Double startingBid;
    Integer auctionPeriod;
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;
    List<BidDto> bids;
}
