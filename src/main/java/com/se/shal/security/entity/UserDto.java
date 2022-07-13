package com.se.shal.security.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String userId;
    private String profile;
    private String name;
    private String firstname;
    private String lastname;
    private String phoneNumber;
    private Boolean enabled;
    private Date lastPasswordResetDate;
    private List<Authority> authorities = new ArrayList<>();

}
