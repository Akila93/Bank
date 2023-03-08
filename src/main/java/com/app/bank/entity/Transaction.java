package com.app.bank.entity;

import com.app.bank.constant.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "transaction")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long transactionId;

    double transactionAmount;

    @Enumerated(EnumType.STRING)
    TransactionType transactionType;

    @Temporal(TemporalType.DATE)
    Date transactionDate;

    String remarks;

    @PrePersist
    public void transactionDate(){
        this.transactionDate = new Date();
    }
}
