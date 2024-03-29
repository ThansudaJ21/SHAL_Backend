package com.se.shal.product.entity;

import com.se.shal.product.entity.enumeration.CategoryName;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.shop.entity.Shop;
import com.se.shal.trading.entity.Auction;
import com.se.shal.trading.entity.Bid;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.util.hibernate.StringListConverter;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;

    @Convert(converter = StringListConverter.class)
    @Builder.Default
    @Column(columnDefinition = "TEXT")
    List<String> imagesPath = new ArrayList<>();

    String productName;
    String details;
    CategoryName category;
    ProductStatus productStatus;

    //    sale information
    Double salePrice;
    Integer storage;
    SaleTypeName saleTypeName;


    //    shipment
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Shipment> shipments;

    //    variations
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Variations> variations;

    //    productAttributes
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<ProductAttribute> productAttribute;

    //     shop
    @ManyToOne
    Shop shop;

//    @ManyToOne
//    Auction auction;
//
//    @OneToOne
//    Bid currentBid;
//
//    @ManyToMany
//    List<Bid> bids;
}
