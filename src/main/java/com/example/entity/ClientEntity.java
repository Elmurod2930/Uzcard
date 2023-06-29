package com.example.entity;

import com.example.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "client")
@Entity
@Getter
@Setter
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "middleName")
    private String middle_name;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "phone")
    private String phone;
    @Enumerated(EnumType.STRING)
    private GeneralStatus status;
    @Column(name = "password_seria")
    private String passwordSeria;
    @Column(name = "passport_number")
    private String passportNumber;
}
//id(uuid),name,surname,middle_name,created_date, phone(not unique), status, password_seria,passport_number
//     -> ( passport_seria + passport_number  unique)