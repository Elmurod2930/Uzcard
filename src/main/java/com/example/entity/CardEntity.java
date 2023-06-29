package com.example.entity;

import com.example.enums.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "number")
    private String number;
    @Column(name = "expired_date")
    private String expiredDate;
    @Column(name = "phone")
    private String phone;
    @Enumerated(EnumType.STRING)
    private CardStatus status;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "balance")
    private Long balance;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", insertable = false, updatable = false)
    private ClientEntity client;
    @Column(name = "client_id")
    private String clientId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;
    @Column(name = "company_id")
    private String companyId;
}
//id(uuid),number,expired_date,phone,status(ACTIVE,BLOCK,NO_ACTIVE),created_date,balance, client_id,company_id,
