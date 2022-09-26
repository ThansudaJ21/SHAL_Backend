package com.se.shal.security.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String houseNumber;
    String mooName;
    String moo;
    String postalCode;
    String district;
    String subDistrict;
    String province;
}
