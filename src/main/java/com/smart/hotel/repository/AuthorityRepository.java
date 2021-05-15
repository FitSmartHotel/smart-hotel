package com.smart.hotel.repository;

import com.smart.hotel.domain.AuthorityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, String> {}
