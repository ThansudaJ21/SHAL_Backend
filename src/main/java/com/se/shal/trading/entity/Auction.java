package com.se.shal.trading.entity;

import com.se.shal.product.entity.Product;
import com.se.shal.security.entity.User;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    LocalDateTime localDateTime;
    Integer times;

    Double bidAmount;

    AuctionResult auctionResult;

    @ManyToOne
    User user;

    @ManyToOne
    Product product;
}
