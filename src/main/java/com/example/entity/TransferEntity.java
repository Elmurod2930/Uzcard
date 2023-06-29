package com.example.entity;

import com.example.enums.TransferStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "transfer")
@Entity
@Getter
@Setter
public class TransferEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "from_card_id")
    private String fromCardId;
    @Column(name = "to_card_id")
    private String toCardId;
    @Column(name = "total_Amount")
    private Long totalAmount;
    @Column(name = "amount")
    private Long amount;
    @Column(name = "service_amount")
    private Long serviceAmount;
    @Column(name = "service_percentage")
    private Integer servicePercentage;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private TransferStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", insertable = false, updatable = false)
    private CompanyEntity company;
    @Column(name = "company_id")
    private String companyId;

}

//id (uuid), from_card_id, to_card_id, total_amount(5600),amount(5500),service_amount(100),
//     service_percentage(1%),created_date, status(SUCCESS,FAILED,CANCELED), company_id
