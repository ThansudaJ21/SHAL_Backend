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
    //    page1
    @Convert(converter = StringListConverter.class)
    @Builder.Default
    @Column(columnDefinition = "TEXT")
    List<String> imagesPath = new ArrayList<>();

    String productName;
    String details;
    @OneToOne
    Category category;

    //  page2
    @OneToMany(mappedBy = "product")
    List<ProductAttribute> productAttributes;
    //    page3 option
    @OneToMany
    List<Variations> variations;
    //    page 4
    @OneToOne
    SalesInformation salesInformation;
    //    page 5
    @OneToMany
    List<Shipment> shipmentNames;

    @ManyToOne
    Shop shop;
}
