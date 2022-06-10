package com.se.shal.shop.dto;

import com.se.shal.shop.entity.FailureReason;
import com.se.shal.shop.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FailureReasonListDto {
    Long id;
    String text;
    FailureReason failureReasons;
    Long shopId;
}
