package com.example.dto.profile;

import com.example.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileUpdateDTO {
    private String name;
    private String surname;
    private ProfileRole role;
    private String username;
    private String password;
}
