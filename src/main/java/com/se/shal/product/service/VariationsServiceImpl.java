package com.se.shal.product.service;

import com.se.shal.product.dao.OptionsDao;
import com.se.shal.product.dao.ProductDao;
import com.se.shal.product.dao.VariationDao;
import com.se.shal.product.entity.Options;
import com.se.shal.product.entity.Product;
import com.se.shal.product.entity.ProductAttribute;
import com.se.shal.product.entity.Variations;
import com.se.shal.product.repository.VariationRepository;
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
    ProductDao productDao;

    @Autowired
    VariationRepository variationRepository;

    @Transactional
    @Override
    public void deleteVariations(Long product,Long id) {
        Product p = productDao.getProduct(product);
    }
}
