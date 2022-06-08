package com.se.shal.product.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.ProductAttribute;
import com.se.shal.product.entity.Variations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
            variations1.setVariationName(variations1.getVariationName());
            variations1.setOptions(options);
        }

        return variationDao.save(newVariations);
    }

    @Transactional
    @Override
    public List<Variations> getVariations(Long productId) {
        List<Variations> variations = variationDao.findAll();

        List<Variations> output = new ArrayList<>();

        for (Variations variation : variations) {
            if (Objects.equals(variation.getProduct().getId(), productId)) {
                output.add(variation);
            } else if (variation.getProduct() == null) {
                return null;
            }
        }
        return output;
    }

    @Transactional
    @Override
    public List<Variations> updateVariations(Long productId, List<Variations> variations) {

        List<Variations> updatedVariations = new ArrayList<>();
        for (Variations variations1 : variations
        ) {
            Variations newVariations = variationDao.getVariationsById(variations1.getId());
            for (Options options : newVariations.getOptions()) {
                Options options1 = optionsDao.findById(options.getId());
                options1.setOptionName(options.getOptionName());
                options1.setPrice(options.getPrice());
                options1.setStock(options.getStock());
                options1.setImage(options.getImage());
            }
            newVariations.setProduct(productDao.getProduct(productId));
            newVariations.setVariationName(variations1.getVariationName());
            updatedVariations.add(newVariations);
        }

        return variationDao.save(updatedVariations);
    }

    @Override
    public void deleteVariations(Long id) {
        variationDao.deleteVariationsById(id);
    }
}
