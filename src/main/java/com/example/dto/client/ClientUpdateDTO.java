package com.example.dto.client;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientUpdateDTO {
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String passwordSeria;
    private String passportNumber;
}
