package com.example.dto.company;

import com.example.enums.CompanyRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyUpdateDTO {
    private String name;
    private String address;
    private String contact;
    private CompanyRole role;
    private String password;
    private String username;
}
