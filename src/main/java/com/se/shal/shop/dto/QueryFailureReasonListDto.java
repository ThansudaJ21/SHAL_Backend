package com.se.shal.shop.dto;

import com.se.shal.shop.entity.FailureReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryFailureReasonListDto {
    Long id;
    String text;
    FailureReason failureReasons;
    Long shopId;
}
