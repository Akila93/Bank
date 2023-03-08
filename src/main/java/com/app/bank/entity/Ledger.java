package com.app.bank.entity;

import com.app.bank.constant.LedgerStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ledger")
@Getter
@Setter
public class Ledger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long ledgerId;

    @ManyToOne(targetEntity = Transaction.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    Transaction transaction;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    Account account;

    double openingBalance;

    double closingBalance;

    @Enumerated(EnumType.STRING)
    LedgerStatus ledgerStatus;

    @Temporal(TemporalType.DATE)
    Date createdDate;

    @Temporal(TemporalType.DATE)
    Date lastUpdatedDate;

    @PrePersist
    public void createdDate(){
        this.createdDate = new Date();
    }

    @PreUpdate
    public void lastUpdatedDate(){
        this.lastUpdatedDate = new Date();
    }
}
