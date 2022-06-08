package com.se.shal.shop.dao;

import com.se.shal.shop.entity.Shop;
import com.se.shal.shop.entity.Shop_;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopName;
import com.se.shal.shop.graphql.entity.ShopQueryFilterByShopStatus;
import com.se.shal.shop.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ShopDaoImpl implements ShopDao{
    @Autowired
    ShopRepository shopRepository;

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }


    @Override
    public List<Shop> getAllShop() {
        return shopRepository.findAll();
    }

    @Override
    public Page<Shop> getShopByFilterByShopNameOrShopStatus(ShopQueryFilterByShopName filter, PageRequest pageRequest) {
        Specification<Shop> specification = getShopPredicate(filter);
        return shopRepository.findAll(specification, pageRequest);
    }

    Specification<Shop> getShopPredicate(ShopQueryFilterByShopName filter){
        return (Root<Shop> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->{
            List<Predicate> predicates = new ArrayList<>();
            if (filter.getQueryText() != null){
                predicates.add(cb.like(root.get(Shop_.SHOP_NAME),"%"+ filter.getQueryText() + "%"));
            }
            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }

//    @Override
//    public Page<Shop> getShopFilterByShopStatus(ShopQueryFilterByShopStatus filter, PageRequest pageRequest) {
//        Specification<Shop> specification = getShopByShopStatusPredicate(filter);
//        return shopRepository.findAll(specification, pageRequest);
//    }
//
//    Specification<Shop> getShopByShopStatusPredicate(ShopQueryFilterByShopStatus filter){
//        return (Root<Shop> root, CriteriaQuery<?> cq, CriteriaBuilder cb) ->{
//            List<Predicate> predicates = new ArrayList<>();
//            if (filter.getShopStatus() != null){
//                predicates.add(cb.like(root.get(Shop_.SHOP_STATUS),"%"+ filter.getShopStatus() + "%"));
//            }
//
//            return cb.or(predicates.toArray(new Predicate[0]));
//        };
//    }

    @Override
    public Shop findById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }
}
