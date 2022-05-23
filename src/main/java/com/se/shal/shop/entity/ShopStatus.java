package com.se.shal.shop.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
public class ShopStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShopStatusName shopStatusName;

    @OneToOne
    Shop shop;

    public ShopStatus() {

    }
}
