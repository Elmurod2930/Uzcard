package com.example.service;

import com.example.dto.company.CompanyCreateDTO;
import com.example.dto.company.CompanyResponseDTO;
import com.example.dto.company.CompanyUpdateDTO;
import com.example.entity.CompanyEntity;
import com.example.exps.AppBadRequestException;
import com.example.exps.ItemNotFoundException;
import com.example.repository.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyResponseDTO create(CompanyCreateDTO dto) {
        CompanyEntity entity = new CompanyEntity();
        entity.setAddress(dto.getAddress());
        entity.setName(dto.getName());
        entity.setContact(dto.getContact());
        entity.setPassword(dto.getPassword());
        entity.setRole(dto.getRole());
        entity.setUsername(dto.getUsername());

        companyRepository.save(entity);
        return toResponseDTO(entity);
    }

    public CompanyResponseDTO toResponseDTO(CompanyEntity entity) {
        CompanyResponseDTO dto = new CompanyResponseDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setContact(entity.getContact());
        dto.setAddress(entity.getAddress());
        dto.setPassword(entity.getPassword());
        dto.setUsername(entity.getUsername());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public CompanyResponseDTO update(String id, CompanyUpdateDTO dto) {
        CompanyEntity entity = get(id);
        if (dto.getAddress() != null && !dto.getAddress().isBlank()) {
            entity.setAddress(dto.getAddress());
        }
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        }
        if (dto.getUsername() != null && !dto.getUsername().isBlank()) {
            entity.setUsername(dto.getUsername());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }

        if (dto.getContact() != null && !dto.getContact().isBlank()) {
            entity.setContact(dto.getContact());
        }
        companyRepository.save(entity);
        return toResponseDTO(entity);
    }

    public CompanyEntity get(String id) {
        Optional<CompanyEntity> optional = companyRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("company not found");
        }
        return optional.get();
    }

    public Page<CompanyResponseDTO> paging(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<CompanyEntity> companyEntityPage = companyRepository.findAll(pageable);
        Long totalCount = companyEntityPage.getTotalElements();
        List<CompanyEntity> entities = companyEntityPage.getContent();
        List<CompanyResponseDTO> dtoList = new LinkedList<>();
        for (CompanyEntity entity : entities) {
            dtoList.add(toResponseDTO(entity));
        }
        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    public Boolean delete(String id) {
        if (id == null || id.isBlank()) {
            throw new AppBadRequestException("id null");
        }
        CompanyEntity entity = get(id);
        companyRepository.deleteById(id);
        return true;
    }
}
