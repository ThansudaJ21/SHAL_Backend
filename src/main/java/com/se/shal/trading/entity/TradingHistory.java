package com.se.shal.trading.entity;

import com.se.shal.product.entity.Product;
import com.se.shal.security.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class TradingHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    LocalDateTime dateTime;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Product> products;

    @ManyToMany(cascade = CascadeType.ALL)
    List<User> users;
}
