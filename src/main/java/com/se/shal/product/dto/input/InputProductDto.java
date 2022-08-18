package com.se.shal.product.dto.input;

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
public class InputProductDto {
    Long id;
    String productName;
    String details;
    List<String> imagesPath;
    ProductStatus productStatus;
    String category;
    Long shopId;
    AuctionDto auction;
    Double salePrice;
    Integer storage;
    SaleTypeName saleTypeName;
    //    shipments
    List<String> shipments;
    //    variations
    List<InputVariationsDto> variations;
    //    productAttribute
    List<InputProductAttributeDto> productAttribute;
}
