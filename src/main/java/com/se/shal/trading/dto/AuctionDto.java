package com.se.shal.trading.dto;

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
    LocalDateTime localDateTime;
    Integer times;
    Double bidAmount;
    AuctionResult auctionResult;

    Long userId;
    Long productId;
    List<Long> variationsList;
    List<Long> optionsList;
}
