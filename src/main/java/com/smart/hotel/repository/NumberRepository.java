package com.smart.hotel.repository;

import com.smart.hotel.domain.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NumberRepository extends JpaRepository<NumberEntity, String> {
    Optional<NumberEntity> findByNumber(String number);

    void deleteByNumber(String number);
}
