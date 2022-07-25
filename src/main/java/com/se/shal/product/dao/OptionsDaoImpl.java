package com.se.shal.product.dao;

import com.se.shal.product.entity.Options;
import com.se.shal.product.repository.OptionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OptionsDaoImpl implements OptionsDao{

    @Autowired
    OptionsRepository optionsRepository;
    @Override
    public List<Options> save(List<Options> activity) {
        return optionsRepository.saveAll(activity);
    }

    @Override
    public Options findById(Long id) {
        return optionsRepository.findById(id).orElse(null);
    }

    @Override
    public List<Options> findByIds(List<Long> ids) {
        return optionsRepository.findByIdIn(ids);
    }
}
