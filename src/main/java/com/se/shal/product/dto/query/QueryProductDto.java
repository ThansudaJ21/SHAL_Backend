package com.se.shal.product.dto.query;

import com.se.shal.product.dto.ProductAttributeDto;
import com.se.shal.product.dto.VariationsDto;
import com.se.shal.product.entity.ProductStatus;
import com.se.shal.product.entity.SaleTypeName;
import com.se.shal.product.entity.TimeUnit;
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
    Double startingBid;
    Integer storage;
    Integer auctionPeriod;
    Integer nextAuction;
    SaleTypeName saleTypeName;
    TimeUnit timeUnitForAuctionPeriod;
    TimeUnit timeUnitForNextAuction;
    //    shipments
    List<String> shipments;
    //    variations
    List<QueryVariationsDto> variations;
    //    productAttribute
    List<ProductAttributeDto> productAttribute;
}
