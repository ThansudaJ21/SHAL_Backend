package com.se.shal.security.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    private String username;

//    Line id
    @Column(name = "USERID", length = 50, unique = true)
    @NotNull
    private String userId;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    private String password;

    @Column(name = "NAME", length = 50)
    @NotNull
    private String name;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    private String lastname;


    @Column(name = "ENABLED")
    @NotNull
    private Boolean enabled;

    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastPasswordResetDate;

    @Builder.Default
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();


}