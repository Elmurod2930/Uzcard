package com.example.dto.company;

import com.example.enums.CompanyRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CompanyCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @NotBlank
    private String contact;
    @NotNull
    private CompanyRole role;
    @NotBlank
    private String password;
    @NotBlank
    private String username;
}

