package com.se.shal.product.dto.input;

import com.se.shal.product.entity.enumeration.SaleTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.temporal.ChronoUnit;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputAuction {
    Double startingBid;
    Integer auctionPeriod;
    Integer nextAuction;
    ChronoUnit timeUnitForAuctionPeriod;
    ChronoUnit timeUnitForNextAuction;
}
