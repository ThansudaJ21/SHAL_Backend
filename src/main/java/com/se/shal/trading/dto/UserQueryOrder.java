package com.se.shal.trading.dto;

import com.se.shal.security.entity.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryOrder {
    private Long id;
    private String email;
    private String userId;
    private String pictureUrl;
    private String displayName;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Boolean enabled;
    private Date lastPasswordResetDate;
    private List<Authority> authorities = new ArrayList<>();

}
