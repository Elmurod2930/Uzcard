package com.example.dto.card;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CardCreateDTO {
    private String number;
    private String expiredDate;
    private Long balance;
    private String clientId;
    private String companyId;
}
