package com.se.shal.product.dao;

import com.se.shal.product.entity.Options;

import javax.swing.text.html.Option;
import java.util.List;

public interface OptionsDao {
    List<Options> save(List<Options> activity);
    Options findById(Long id);
}
