package com.se.shal.trading.dto;

import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuctionQueryDto {
    Long id;
    Integer nextAuction;
    Double startingBid;
    Integer auctionPeriod;
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;
    ProductQueryOrder product;

}
