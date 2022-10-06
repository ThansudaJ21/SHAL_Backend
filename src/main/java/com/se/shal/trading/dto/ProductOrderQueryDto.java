package com.se.shal.trading.dto;

import com.se.shal.product.dto.OptionsDto;
import com.se.shal.product.dto.query.ProductQuery;
import com.se.shal.product.dto.query.QueryProductDto;
import com.se.shal.product.dto.query.QueryVariationsDto;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Shipment;
import com.se.shal.security.entity.User;
import com.se.shal.security.entity.UserDto;
import com.se.shal.trading.entity.enumeration.OrderStatus;
import com.se.shal.trading.entity.enumeration.PaymentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOrderQueryDto {
    Long id;
    LocalDateTime dateTime;
    Double totalPrice;
    Integer quantity;
    String paymentStatus;
    String orderStatus;
    String trackingNumber;
    String slipPaymentUrl;
    String shipment;
    QueryUserAddressDto userAddress;
    ProductQueryOrder products;
    UserQueryOrder users;
    ShopQueryDto shop;
    OptionsQueryDto option;
}
