package com.se.shal.shop.entity;

import com.se.shal.product.entity.Product;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String shopName;
    String idCard;
    String shopLogoImagePath;
    String selfiePhotoWithIdCardPath;
    String promptPay;
    String email;

    @OneToMany(mappedBy = "shop")
    @Builder.Default
    List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopAddress_id", referencedColumnName = "id")
    ShopAddress shopAddress;

    ShopStatusName shopStatus;

    @ManyToMany
    List<FailureReasonList> failureReasonLists;
}
