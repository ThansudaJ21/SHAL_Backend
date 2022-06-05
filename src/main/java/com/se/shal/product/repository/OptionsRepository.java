package com.se.shal.product.repository;

import com.se.shal.product.entity.Options;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;

public interface OptionsRepository extends JpaRepository<Options, Long> {
}
