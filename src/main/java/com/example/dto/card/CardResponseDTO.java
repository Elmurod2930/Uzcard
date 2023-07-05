package com.example.dto.card;

import com.example.entity.ClientEntity;
import com.example.entity.CompanyEntity;
import com.example.enums.CardStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CardResponseDTO {
    private String number;
    private String expiredDate;
    private String phone;
    private CardStatus status;
    private LocalDateTime createdDate;
    private Long balance;
    private ClientEntity client;
    private CompanyEntity company;
}
