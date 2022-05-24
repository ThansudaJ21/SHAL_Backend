package com.se.shal.product.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.Variations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class VariationsServiceImpl implements VariationsService {
    @Autowired
    VariationDao variationDao;
    @Autowired
    OptionsDao optionsDao;
    @Autowired
    ProductDao productDao;

    @Transactional
    @Override
    public List<Variations> save(Long productId, List<Variations> variations) {
        List<Variations> newVariations = variationDao.save(variations);

        Product product = productDao.findById(productId);

        for (Variations variations1 : newVariations
        ) {
            List<Options> options = optionsDao.save(variations1.getOptions());
            variations1.setProduct(product);
            variations1.setName(variations1.getName());
            variations1.setOptions(options);
        }

            return variationDao.save(newVariations);
        }
    }
