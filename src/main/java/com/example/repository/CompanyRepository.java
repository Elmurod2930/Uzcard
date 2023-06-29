package com.example.repository;

import com.example.entity.CompanyEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CompanyRepository extends CrudRepository<CompanyEntity, String>,
        PagingAndSortingRepository<CompanyEntity, String> {
    @Transactional
    @Modifying
    @Query("update CompanyEntity set visible = false where id=:id")
    void deleteById(@Param("id") String id);
}
