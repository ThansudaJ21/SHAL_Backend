package com.se.shal.util.postCodeUtil.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCodeAmphoeDto {
    String amphoe;
    @Builder.Default
    List<String> tumbons = new ArrayList<>();
}
