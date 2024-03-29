package com.se.shal.trading.dto;

import com.se.shal.product.dto.ProductAttributeDto;
import com.se.shal.product.dto.query.QueryVariationsDto;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductQueryOrder {
    Long id;
    String productName;
    String details;
    List<String> imagesPath;
    ProductStatus productStatus;
    String category;
    Long shopId;
    //    sale information
    Double salePrice;
    Integer storage;
    SaleTypeName saleTypeName;
//    AuctionQueryDto auction;
//    BidQueryDto currentBid;

}
