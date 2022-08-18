package com.se.shal.product.dto.query;

import com.se.shal.product.dto.ProductAttributeDto;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.trading.dto.AuctionQueryDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryProductDto {
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
    //    shipments
    List<String> shipments;
    //    variations
    List<QueryVariationsDto> variations;
    //    productAttribute
    List<ProductAttributeDto> productAttribute;

//    AuctionQueryDto auction;

}
