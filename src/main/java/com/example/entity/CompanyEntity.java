package com.example.entity;

import com.example.enums.CompanyRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "company")
@Entity
@Getter
@Setter
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "contact")
    private String contact;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "role")
    private CompanyRole role;
    @Column(name = "code")
    private String code;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
}

//name, address,contact,created_date,visible, role(BANK,PAYMENT), code (if bank)
//        username (unique),password
