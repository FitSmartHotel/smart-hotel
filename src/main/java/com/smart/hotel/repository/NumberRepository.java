package com.smart.hotel.repository;

import com.smart.hotel.domain.NumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface NumberRepository extends JpaRepository<NumberEntity, String> {
    Optional<NumberEntity> findByNumber(String number);

    void deleteByNumber(String number);

    @Modifying
    @Query("update NumberEntity n set n.usersAmount = n.usersAmount + 1 where n.number = :number")
    void incrementUsersAmount(@Param("number") String number);

    @Modifying
    @Query("update NumberEntity n set n.usersAmount = n.usersAmount - 1 where n.number = :number")
    void decrementUsersAmount(@Param("number") String number);
}
