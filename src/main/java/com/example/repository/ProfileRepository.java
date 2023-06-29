package com.example.repository;

import com.example.entity.ProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, String>,
        PagingAndSortingRepository<ProfileEntity, String> {

    Optional<ProfileEntity> findByUsername(String email);
    Optional<ProfileEntity> findByUsernameAndPasswordAndVisible(String username, String password, Boolean visible);

    @Modifying
    @Transactional
    @Query("update ProfileEntity set status = 'ACTIVE' where id=:id")
    Boolean changeStatusById(@Param("id") String id);

}
