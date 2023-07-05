package com.example.repository;

import com.example.entity.CardEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface CardRepository extends CrudRepository<CardEntity, String> {
    @Query("from CardEntity where phone=:phone")
    List<CardEntity> findByPhone(@Param("phone") String phone);

    @Query("from CardEntity where clientId=:clientId")
    List<CardEntity> findByClientId(@Param("clientId") String clientId);

    Optional<CardEntity> findByNumber(String number);
}
