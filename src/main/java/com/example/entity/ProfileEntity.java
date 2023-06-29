package com.example.entity;

import com.example.enums.GeneralStatus;
import com.example.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
}
//id(uuid),name,surname,created_date,status,role(ADMIN,MODERATOR), username,password