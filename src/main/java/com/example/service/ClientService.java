package com.example.service;

import com.example.dto.client.ClientCreateDTO;
import com.example.dto.client.ClientResponseDTO;
import com.example.dto.client.ClientUpdateDTO;
import com.example.entity.ClientEntity;
import com.example.enums.ProfileRole;
import com.example.exps.ItemNotFoundException;
import com.example.repository.ClientRepository;
import com.example.util.SpringSecurityUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientResponseDTO create(ClientCreateDTO dto) {
        ClientEntity entity = new ClientEntity();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setMiddleName(dto.getMiddleName());
        entity.setPassportNumber(dto.getPassportNumber());
        entity.setSurname(dto.getSurname());
        entity.setPasswordSeria(dto.getPasswordSeria());
        clientRepository.save(entity);
        return toResponseDTO(entity);
    }

    public ClientResponseDTO toResponseDTO(ClientEntity entity) {
        ClientResponseDTO dto = new ClientResponseDTO();
        dto.setMiddleName(entity.getMiddleName());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setPhone(entity.getPhone());
        dto.setPasswordSeria(entity.getPasswordSeria());
        dto.setPassportNumber(entity.getPassportNumber());
        dto.setStatus(entity.getStatus());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public ClientResponseDTO update(String id, ClientUpdateDTO dto) {
        ClientEntity entity = get(id);
        if (dto.getMiddleName() != null && !dto.getMiddleName().isBlank()) {
            entity.setMiddleName(dto.getMiddleName());
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getSurname() != null && !dto.getSurname().isBlank()) {
            entity.setSurname(dto.getSurname());
        }
        if (dto.getPhone() != null && !dto.getPhone().isBlank()) {
            entity.setPhone(dto.getPhone());
        }
        if (dto.getPasswordSeria() != null && !dto.getPasswordSeria().isBlank()) {
            entity.setPasswordSeria(dto.getPasswordSeria());
        }
        if (dto.getPassportNumber() != null && !dto.getPassportNumber().isBlank()) {
            entity.setPassportNumber(dto.getPassportNumber());
        }
        clientRepository.save(entity);
        return toResponseDTO(entity);
    }

    public ClientEntity get(String id) {
        Optional<ClientEntity> optional = clientRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("not fount client");
        }
        return optional.get();
    }

    public ClientResponseDTO getById(String id) {
        if (SpringSecurityUtil.getProfileRole().equals(ProfileRole.ROLE_ADMIN)) {
            ClientEntity entity = get(id);
            return toResponseDTO(entity);
        } else {
            // TODO: 6/29/2023
        }
        return null;
    }


}
