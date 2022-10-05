package com.se.shal.trading.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QuerySaveUserAddressDto {
    Long id;
    String addressName;
    String houseNumber;
    String mooName;
    String moo;
    String postalCode;
    String district;
    String subDistrict;
    String province;
    String addressStatus;
    Long user;
}
