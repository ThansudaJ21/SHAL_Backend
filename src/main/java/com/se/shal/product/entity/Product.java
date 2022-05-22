package com.se.shal.product.entity;

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
    String productName;
    String details;
    Double salePrice;
    Double startingBid;
    Integer storage;
    Integer auctionPeriod;
    Integer nextAuction;
    SaleTypeName saleTypeName;
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;

    @OneToOne
    Category category;

    @OneToMany
    List<Shipment> shipmentNames;

    @OneToMany(mappedBy="product")
    List<ProductAttribute> productAttributes;

    @Convert(converter = StringListConverter.class)
    @Builder.Default
    @Column(columnDefinition = "TEXT")
    List<String> imagesPath = new ArrayList<>();

    @ManyToOne
    Shop shop;
}
