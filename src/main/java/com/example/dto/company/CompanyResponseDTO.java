package com.example.dto.company;

import com.example.enums.CompanyRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponseDTO {
    private String id;
    private String name;
    private String address;
    private String contact;
    private LocalDateTime createdDate;
    private CompanyRole role;
    private String code;
    private String username;
    private String password;
}
