package com.se.shal.product.repository;

import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Variations;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;

public interface OptionsRepository extends JpaRepository<Options, Long> {
    List<Options> findByIdIn(List<Long> ids);

}
