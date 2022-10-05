package com.se.shal.trading.entity;

import com.se.shal.security.entity.User;
import com.se.shal.trading.entity.enumeration.AddressStatus;
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
    String addressName;
    String houseNumber;
    String mooName;
    String moo;
    String postalCode;
    String district;
    String subDistrict;
    String province;
    AddressStatus addressStatus;
    @ManyToOne
    User user;
}
