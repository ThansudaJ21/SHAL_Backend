package com.se.shal.product.dao;

import com.se.shal.product.entity.SalesInformation;
import com.se.shal.product.repository.SalesInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SalesInformationDaoImpl implements  SalesInformationDao{
    @Autowired
    SalesInformationRepository salesInformationRepository;

    @Override
    public SalesInformation save(SalesInformation salesInformation) {
        return salesInformationRepository.save(salesInformation);
    }
}
