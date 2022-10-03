package com.se.shal.shop.entity;

import com.se.shal.product.entity.Product;
import com.se.shal.security.entity.User;
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
    PromptPayType promptPayType;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "shopAddress_id", referencedColumnName = "id")
    ShopAddress shopAddress;
    ShopStatusName shopStatus;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<FailureReasonList> failureReasonLists;

    @OneToOne
    User user;
}
