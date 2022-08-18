package com.se.shal.product.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Variations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String variationName;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    List<Options> options;
}
