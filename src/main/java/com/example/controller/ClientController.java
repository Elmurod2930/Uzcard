package com.example.controller;

import com.example.dto.client.ClientCreateDTO;
import com.example.dto.client.ClientResponseDTO;
import com.example.dto.client.ClientUpdateDTO;
import com.example.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/client")
@AllArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientResponseDTO> create(@RequestBody ClientCreateDTO dto) {
        ClientResponseDTO response = clientService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ClientResponseDTO> update(@PathVariable String id, @RequestBody ClientUpdateDTO dto) {
        ClientResponseDTO response = clientService.update(id, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getById(@PathVariable String id) {
        ClientResponseDTO response = clientService.getById(id);
        return ResponseEntity.ok(response);
    }
}
