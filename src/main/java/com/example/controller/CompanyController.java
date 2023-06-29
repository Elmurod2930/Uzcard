package com.example.controller;

import com.example.dto.company.CompanyCreateDTO;
import com.example.dto.company.CompanyResponseDTO;
import com.example.dto.company.CompanyUpdateDTO;
import com.example.service.CompanyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<CompanyResponseDTO> create(@RequestBody @Valid CompanyCreateDTO dto) {
        CompanyResponseDTO response = companyService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CompanyResponseDTO> update(@RequestBody CompanyUpdateDTO dto, @PathVariable String id) {
        CompanyResponseDTO response = companyService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/paging")
    public ResponseEntity<Page<CompanyResponseDTO>> paging(@RequestParam("page") int page,
                                                           @RequestParam("size") int size) {
        Page<CompanyResponseDTO> entityPage = companyService.paging(page, size);
        return ResponseEntity.ok(entityPage);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Boolean del = companyService.delete(id);
        return ResponseEntity.ok(del);
    }
}
