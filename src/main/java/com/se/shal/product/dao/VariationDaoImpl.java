package com.se.shal.product.dao;

import com.se.shal.product.entity.Variations;
import com.se.shal.product.repository.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VariationDaoImpl implements VariationDao{

    @Autowired
    VariationRepository variationRepository;
    @Override
    public List<Variations> save(List<Variations> variations) {
        return variationRepository.saveAll(variations);
    }

    @Override
    public List<Variations> findAll() {
        return variationRepository.findAll();
    }

    @Override
    public List<Variations> getVariationsByIds(List<Long> ids) {
        return variationRepository.findAllById(ids);
    }

    @Override
    public Variations getVariationsById(Long id) {
        return variationRepository.getById(id);
    }

    @Override
    public List<Variations> updateVariations(List<Variations> variations) {
        return variationRepository.saveAllAndFlush(variations);
    }
}
