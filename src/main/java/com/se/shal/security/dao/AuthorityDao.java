package com.se.shal.security.dao;

import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;

public interface AuthorityDao {
    Authority findByName(AuthorityName authorityName);
}
