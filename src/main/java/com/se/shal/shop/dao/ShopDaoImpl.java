package com.se.shal.shop.dao;

import com.google.common.base.Strings;
import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.ShopStatusName;
import com.se.shal.shop.entity.Shop_;
import com.se.shal.shop.graphql.entity.ShopQueryFilter;
import com.se.shal.shop.repository.FailureReasonListRepository;
import com.se.shal.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopDaoImpl implements ShopDao {
    @Autowired
    ShopRepository shopRepository;

    @Autowired
    FailureReasonListRepository failureReasonListRepository;

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }


    @Override
    public Page<Shop> getShopByFilter(ShopQueryFilter filter, PageRequest pageRequest) {
        Specification<Shop> specification = getShopPredicate(filter);
        try {
            return shopRepository.findAll(specification, pageRequest);
        } catch (IllegalArgumentException  | InvalidDataAccessApiUsageException ex) {
            return null;
        }
    }

    Specification<Shop> getShopPredicate(ShopQueryFilter filter) {
        return (Root<Shop> root, CriteriaQuery<?> cq, CriteriaBuilder cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getShopName() != null) {
                predicates.add(cb.like(root.get(Shop_.SHOP_NAME), "%" + filter.getShopName() + "%"));
            }

            if (!Strings.isNullOrEmpty(filter.getShopStatus())) {

                ShopStatusName enumResult = ShopStatusName.valueOf(filter.getShopStatus());
                predicates.add(cb.equal(root.get(Shop_.SHOP_STATUS), enumResult));

            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }


    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }
}
