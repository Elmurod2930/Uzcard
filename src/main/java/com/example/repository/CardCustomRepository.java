package com.example.repository;

import com.example.dto.client.ClientFilterRequestDTO;
import com.example.entity.ClientEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CardCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ClientEntity> filter(ClientFilterRequestDTO filterRequestDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select c From ProfileEntity as c where visible = true");
        if (filterRequestDTO.getName() != null) {
            params.put("name", filterRequestDTO.getName());
            builder.append(" and c.name =:name");
        }
        if (filterRequestDTO.getSurname() != null) {
            params.put("surname", filterRequestDTO.getSurname());
            builder.append(" and c.surname =:surname");
        }
        if (filterRequestDTO.getMiddleName() != null) {
            params.put("middle_name", filterRequestDTO.getMiddleName());
            builder.append(" and c.middle_name=:middle_name");
        }
        if (filterRequestDTO.getCreatedDateFrom() != null && filterRequestDTO.getCreatedDateTo() != null) {
            builder.append(" and c.createdDate between :dateFrom and :dateTo ");
            params.put("dateFrom", LocalDateTime.of(filterRequestDTO.getCreatedDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(filterRequestDTO.getCreatedDateTo(), LocalTime.MAX));
        } else if (filterRequestDTO.getCreatedDateFrom() != null) {
            builder.append(" and c.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(filterRequestDTO.getCreatedDateFrom(), LocalTime.MIN));
        } else if (filterRequestDTO.getCreatedDateTo() != null) {
            builder.append(" and c.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(filterRequestDTO.getCreatedDateTo(), LocalTime.MIN));
        }
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List clientList = query.getResultList();
        return clientList;
    }
}
