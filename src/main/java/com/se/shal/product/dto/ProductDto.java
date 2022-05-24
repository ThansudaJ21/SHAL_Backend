package com.se.shal.product.dto;

import com.se.shal.product.entity.*;
import com.se.shal.util.hibernate.StringListConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
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
    String category;
    Long ShopId;
}
