package com.se.shal.trading.dto;

import com.se.shal.security.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InputUserAddressDto {
    Long id;
    String addressName;
    String houseNumber;
    String mooName;
    String moo;
    String postalCode;
    String district;
    String subDistrict;
    String province;
    Long user;
}
