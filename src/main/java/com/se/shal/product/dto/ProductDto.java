package com.se.shal.product.dto;

import com.se.shal.product.dto.input.InputAuction;
import com.se.shal.product.dto.input.InputVariationsDto;
import com.se.shal.product.dto.query.QueryVariationsDto;
import com.se.shal.product.entity.enumeration.ProductStatus;
import com.se.shal.product.entity.enumeration.SaleTypeName;
import com.se.shal.product.entity.enumeration.TimeUnit;
import com.se.shal.trading.dto.AuctionDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    Long id;
    String productName;
    String details;
    List<String> imagesPath;
    ProductStatus productStatus;
    String category;
    Long shopId;
    Double salePrice;
    Integer storage;
    SaleTypeName saleTypeName;
    InputAuction auction;
    //    shipments
    List<String> shipments;
    //    variations
    List<InputVariationsDto> variations;
    //    productAttribute
    List<ProductAttributeDto> productAttribute;
}
