package com.se.shal.shop.graphql.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopQueryFilter {
//    String queryText;

    String shopName;
    String shopStatus;
}
