package com.se.shal.product.dao;

import com.se.shal.product.entity.Variations;
import com.se.shal.product.repository.VariationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VariationDaoImpl implements VariationDao {

    @Autowired
    VariationRepository variationRepository;

    @Override
    public List<Variations> save(List<Variations> variations) {
        return variationRepository.saveAll(variations);
    }

    @Override
    public void deleteVariationsById(Long id) {
        variationRepository.deleteById(id);
    }

    @Override
    public Variations findById(Long id) {
        return variationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Variations> findByIds(List<Long> ids) {
        return variationRepository.findByIdIn(ids);
    }
}
