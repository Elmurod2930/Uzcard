package com.example.repository;

import com.example.entity.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ClientRepository extends CrudRepository<ClientEntity, String>,
        PagingAndSortingRepository<ClientEntity, String> {
}
