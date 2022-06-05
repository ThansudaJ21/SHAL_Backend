package com.se.shal.product.repository;

import com.se.shal.product.entity.SalesInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesInformationRepository extends JpaRepository<SalesInformation, Long> {
}
