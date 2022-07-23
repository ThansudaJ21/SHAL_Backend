package com.se.shal.trading.dto;

import com.se.shal.product.dto.query.ProductQuery;
import com.se.shal.product.dto.query.QueryProductDto;
import com.se.shal.product.entity.Product;
import com.se.shal.security.entity.User;
import com.se.shal.security.entity.UserDto;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradingHistoryQueryDto {
    Long id;
    LocalDateTime dateTime;
    ProductQueryTradingHistory products;
    UserQueryTradingHistory users;
}
