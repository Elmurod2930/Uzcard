package com.example.service;

import com.example.dto.company.CompanyResponseDTO;
import com.example.dto.profile.ProfileCreateDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.example.dto.profile.ProfileUpdateDTO;
import com.example.entity.CompanyEntity;
import com.example.entity.ProfileEntity;
import com.example.enums.GeneralStatus;
import com.example.exps.ItemNotFoundException;
import com.example.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public ProfileResponseDTO create(ProfileCreateDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setUsername(dto.getUsername());
        entity.setRole(dto.getRole());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setSurname(dto.getSurname());
        profileRepository.save(entity);
        return toResponseDTO(entity);
    }

    private ProfileResponseDTO toResponseDTO(ProfileEntity entity) {
        ProfileResponseDTO dto = new ProfileResponseDTO();
        dto.setName(entity.getName());
        dto.setPassword(entity.getPassword());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public ProfileResponseDTO update(String id, ProfileUpdateDTO dto) {
        ProfileEntity entity = get(id);
        if (dto.getName() != null && !dto.getName().isBlank()) {
            entity.setName(dto.getName());
        }
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            entity.setPassword(dto.getPassword());
        }
        if (dto.getRole() != null) {
            entity.setRole(dto.getRole());
        }
        if (dto.getUsername() != null && dto.getUsername().isBlank()) {
            entity.setUsername(dto.getUsername());
        }
        if (dto.getSurname() != null && !dto.getSurname().isBlank()) {
            entity.setSurname(entity.getSurname());
        }
        profileRepository.save(entity);
        return toResponseDTO(entity);
    }

    public ProfileEntity get(String id) {
        Optional<ProfileEntity> optional = profileRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("profile not found");
        }
        return optional.get();
    }

    public Page<ProfileResponseDTO> paging(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<ProfileEntity> profileEntityPage = profileRepository.findAll(pageable);
        Long totalCount = profileEntityPage.getTotalElements();
        List<ProfileEntity> entities = profileEntityPage.getContent();
        List<ProfileResponseDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entities) {
            dtoList.add(toResponseDTO(entity));
        }
        return new PageImpl<>(dtoList, pageable, totalCount);
    }

    public ProfileResponseDTO changeStatus(String id) {
        ProfileEntity entity = get(id);
        if (entity.getStatus() == GeneralStatus.ACTIVE) {
            entity.setStatus(GeneralStatus.BLOCK);
        }
        if (entity.getStatus() == GeneralStatus.BLOCK) {
            entity.setStatus(GeneralStatus.ACTIVE);
        }
        profileRepository.save(entity);
        return toResponseDTO(entity);
    }
}
