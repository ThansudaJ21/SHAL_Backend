package com.se.shal.trading.entity;

import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Variations;
import com.se.shal.security.entity.User;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.entity.enumeration.AuctionResult;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    Integer times;
    Double bidAmount;
    AuctionResult auctionResult;
    LocalDateTime localDateTime;
    @ManyToOne
    User user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    Shop shop;

    @ManyToOne
    Product product;
}
