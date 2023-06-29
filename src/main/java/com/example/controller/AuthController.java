package com.example.controller;

import com.example.dto.auth.AuthDTO;
import com.example.dto.auth.AuthResponseDTO;
import com.example.dto.auth.RegistrationDTO;
import com.example.dto.auth.RegistrationResponseDTO;
import com.example.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<RegistrationResponseDTO> registration(@RequestBody @Valid RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }


    @PostMapping("")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @GetMapping("/email/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable String jwt){
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}