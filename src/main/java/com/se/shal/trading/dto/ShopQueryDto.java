package com.se.shal.trading.dto;

import com.se.shal.shop.dto.QueryFailureReasonListDto;
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
public class ShopQueryDto {
    Long id;
    String shopName;
    String idCard;
    String shopLogoImagePath;
    String selfiePhotoWithIdCardPath;
    String promptPay;
    String email;
    ShopAddress shopAddress;
    String shopStatus;
}
