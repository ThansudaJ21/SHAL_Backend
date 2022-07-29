package com.se.shal.trading.dto;

import com.se.shal.security.entity.User;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BidDto {
    Long id;
    LocalDateTime localDateTime;
    Integer times;
    AuctionResult auctionResult;
    Double bidAmount;
    Long userId;
    Long shopId;
    Long productId;
}
