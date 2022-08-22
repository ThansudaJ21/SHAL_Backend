package com.se.shal.shop.dto;

import com.se.shal.shop.entity.ShopAddress;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopQueryForRegisterShopDto {
    Long id;
    String shopName;
    String idCard;
    String shopLogoImagePath;
    String selfiePhotoWithIdCardPath;
    String promptPay;
    String email;
    ShopAddress shopAddress;
    String shopStatus;
    List<QueryFailureReasonLisForUserIdtDto> failureReasonLists;
//    UserQueryShop user;
}
