package com.se.shal.security.dao;

import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import com.se.shal.security.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{

    @Autowired
    AuthorityRepository authorityRepository;
    @Override
    public Authority findByName(AuthorityName authorityName) {
        return authorityRepository.findByName(authorityName);
    }
}
