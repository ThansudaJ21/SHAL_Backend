package com.se.shal.product.dao;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("db")
public class ProductDaoDbImpl implements ProductDao{
}
