package com.se.shal.product.service;

import com.se.shal.product.entity.SalesInformation;

public interface SalesInformationService {
    SalesInformation save(Long productId,SalesInformation salesInformation);
}
