package com.example.dto.client;

import com.example.enums.GeneralStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientResponseDTO {
    private String name;
    private String surname;
    private String middleName;
    private String phone;
    private String passwordSeria;
    private String passportNumber;
    private LocalDateTime createdDate;
    private GeneralStatus status;
}
