package com.se.shal.util.postCodeUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostCodeData {
    String district;
    String amphoe;
    String province;
    String zipcode;
    String district_code;
    String amphoe_code;
    String province_code;
}
