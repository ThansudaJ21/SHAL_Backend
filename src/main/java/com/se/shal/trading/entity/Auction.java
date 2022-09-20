package com.se.shal.trading.entity;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.enumeration.TimeUnit;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Integer nextAuction;
    Double startingBid;
    Integer auctionPeriod;
    ChronoUnit timeUnitForAuctionPeriod;
    ChronoUnit timeUnitForNextAuction;
    LocalDateTime endBiddingTime;
    Boolean isNotification = false;
    Integer auctionTimes;
//    @OneToMany
//    List<Bid> bids;

    @OneToOne
    Product product;

    @OneToOne
    Bid maxBidding;
}
