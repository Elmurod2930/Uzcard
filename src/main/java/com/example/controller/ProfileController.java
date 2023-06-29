package com.example.controller;

import com.example.dto.company.CompanyResponseDTO;
import com.example.dto.profile.ProfileCreateDTO;
import com.example.dto.profile.ProfileResponseDTO;
import com.example.dto.profile.ProfileUpdateDTO;
import com.example.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/create")
    public ResponseEntity<ProfileResponseDTO> create(@RequestBody ProfileCreateDTO dto) {
        ProfileResponseDTO response = profileService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @RequestBody ProfileUpdateDTO dto) {
        ProfileResponseDTO response = profileService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<ProfileResponseDTO>> paging(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Page<ProfileResponseDTO> entityPage = profileService.paging(page, size);
        return ResponseEntity.ok(entityPage);
    }

    @PutMapping("/change-status/{id}")
    public ResponseEntity<ProfileResponseDTO> changeStatus(@PathVariable String id) {
        ProfileResponseDTO response = profileService.changeStatus(id);
        return ResponseEntity.ok(response);
    }
}
