package com.example.dto.client;

import com.example.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ClientFilterRequestDTO {
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String passwordSeria;
    private String passportNumber;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private GeneralStatus status;
}
