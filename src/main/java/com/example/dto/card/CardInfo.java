package com.example.dto.card;

import com.example.enums.CardStatus;
import com.example.mapper.ClientMapper;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CardInfo {
    private String id;
    private String number;
    private String expiredDate;
    private String phone;
    private CardStatus status;
    private LocalDateTime createdDate;
    private Long balance;
    private ClientMapper profile;
}
