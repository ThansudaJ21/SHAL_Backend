package com.se.shal.product.entity;

import com.se.shal.product.entity.enumeration.CategoryName;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.shop.entity.Shop;
import com.se.shal.util.hibernate.StringListConverter;
import lombok.*;

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
    Double startingBid;
    Integer storage;
    Integer auctionPeriod;
    Integer nextAuction;
    SaleTypeName saleTypeName;
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;

//    shipment
    @ManyToMany
    List<Shipment> shipments;

//    variations
    @OneToMany(cascade = CascadeType.REMOVE)
    List<Variations> variations;

//    productAttributes
    @OneToMany
    List<ProductAttribute> productAttribute;

//     shop
    @ManyToOne
    Shop shop;
}
