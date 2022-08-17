package com.se.shal.trading.entity;

import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.enumeration.TimeUnit;
import lombok.*;

import javax.persistence.*;
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
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;

//    @OneToMany
//    List<Bid> bids;

    @OneToOne
    Product product;

    @OneToOne
    Bid maxBidding;
}
