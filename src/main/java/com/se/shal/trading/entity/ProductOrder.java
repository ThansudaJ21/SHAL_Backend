package com.se.shal.trading.entity;

import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
import com.se.shal.product.entity.Variations;
import com.se.shal.product.entity.enumeration.ShipmentName;
import com.se.shal.security.entity.User;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    LocalDateTime dateTime;
    Double totalPrice;
    Integer quantity;
    PaymentStatus paymentStatus;
    OrderStatus orderStatus;
    String trackingNumber;
    String slipPaymentUrl;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    Shipment shipment;

    @OneToOne
    UserAddress userAddress;

    @ManyToOne
    Options options;

    @ManyToOne
    Shop shop;

    @ManyToOne
    Product products;

    @ManyToOne
    User users;
}
