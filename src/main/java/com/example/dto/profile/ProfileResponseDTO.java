package com.example.dto.profile;

import com.example.enums.GeneralStatus;
import com.example.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO {
    private String name;
    private String surname;
    private LocalDateTime createdDate;
    private GeneralStatus status;
    private ProfileRole role;
    private String username;
    private String password;
}
