package com.se.shal.util.postCodeUtil.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCodeProvinceDto {
    String province;
    @Builder.Default
    List<PostCodeAmphoeDto> amphoes = new ArrayList<>();
}
