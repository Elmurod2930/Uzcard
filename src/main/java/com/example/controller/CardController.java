package com.example.controller;

import com.example.dto.card.CardCreateDTO;
import com.example.dto.card.CardInfo;
import com.example.dto.card.CardResponseDTO;
import com.example.dto.card.CardUpdateDTO;
import com.example.service.CardService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/card")
@AllArgsConstructor
public class CardController {
    private final CardService cardService;

    @PostMapping("/create")
    public ResponseEntity<CardResponseDTO> create(@RequestBody CardCreateDTO dto) {
        CardResponseDTO response = cardService.create(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/change-status/{id}")
    public ResponseEntity<CardResponseDTO> changeStatus(@PathVariable String id) {
        CardResponseDTO response = cardService.changeStatus(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/assign-phone")
    public ResponseEntity<CardResponseDTO> assignPhone(@RequestBody CardUpdateDTO dto) {
        CardResponseDTO response = cardService.assignPhone(dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{card-id}")
    public ResponseEntity<CardInfo> getCardById(@PathVariable("card-id") String id) {
        CardInfo response = cardService.getCardById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/card-list-by-phone/{phone}")
    public ResponseEntity<List<CardInfo>> getCardListByPhone(@PathVariable String phone) {
        List<CardInfo> response = cardService.getCardListByPhone(phone);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/card-list-by-client-id/{clientId}")
    public ResponseEntity<List<CardInfo>> getCardListByClientId(@PathVariable String clientId) {
        List<CardInfo> response = cardService.getCardListByClientId(clientId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/card-by-number/{number}")
    public ResponseEntity<CardInfo> getCardListByNumber(@PathVariable String number) {
        CardInfo response = cardService.getCardByNumber(number);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance-by-number/{number}")
    public ResponseEntity<Long> getCardBalanceByNumber(@PathVariable String number) {
        Long response = cardService.getCardBalanceByNumber(number);
        return ResponseEntity.ok(response);
    }
}

