package com.se.shal.product.dao;

import com.se.shal.product.dto.SalesInformationDto;
import com.se.shal.product.entity.SalesInformation;

public interface SalesInformationDao {
    SalesInformation save(SalesInformation salesInformation);
    SalesInformation getSalesInformation(Long id);
}
