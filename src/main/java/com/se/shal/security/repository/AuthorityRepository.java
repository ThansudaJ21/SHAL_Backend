package com.se.shal.security.repository;

import com.se.shal.security.entity.Authority;
import com.se.shal.security.entity.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName input);
}
