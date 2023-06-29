package com.example.entity;

import com.example.enums.TransactionStatus;
import com.example.enums.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table
@Entity
@Getter
@Setter
public class TransactionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", insertable = false, updatable = false)
    private CardEntity card;
    @Column(name = "card_id")
    private String cardId;
    @Column(name = "amount")
    private Long amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;
    @Column(name = "transfer_id")
    private String transferId;
    @ManyToOne
    @JoinColumn(name = "transfer_id", insertable = false, updatable = false)
    private TransferEntity transfer;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

}
//id(uuid), card_id,amount,transaction_type (CREDIT,DEBIT), transfer_id, created_date,
//        status(CREATED,SUCCESS,FAILED,CANCELED,)
