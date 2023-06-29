package com.example.dto.profile;

import com.example.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileCreateDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotNull
    private ProfileRole role;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
