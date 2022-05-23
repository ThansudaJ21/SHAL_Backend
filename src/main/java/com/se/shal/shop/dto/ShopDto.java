package com.se.shal.shop.dto;

import com.se.shal.shop.entity.ShopAddress;
import com.se.shal.shop.entity.ShopStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDto {
    Long id;
    String shopName;
    String idCard;
    String shopLogoImagePath;
    String selfiePhotoWithIdCardPath;
    String promptPay;
    String email;
    ShopAddress shopAddress;
    ShopStatus shopStatus;
}
