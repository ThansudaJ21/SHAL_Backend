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
public class QueryFailureReasonDto {
    Long id;
    String reason;
}
