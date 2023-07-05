package com.example.dto.card;

import com.example.entity.ClientEntity;
import com.example.entity.CompanyEntity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardUpdateDTO {
    private String id;
    private String number;
    private String expiredDate;
    private String phone;
    private Long balance;
    private ClientEntity client;
    private CompanyEntity company;
}
