package com.app.bank.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long accountId;

    @Column(nullable=false, length=45, unique = true, updatable = false)
    String accountNo;

    double balance;

    @Temporal(TemporalType.DATE)
    Date lastUpdatedDate;

    @OneToMany(targetEntity = Ledger.class, mappedBy = "account")
    List<Ledger> ledgers;

    @PreUpdate
    public void lastUpdatedDate(){
        this.lastUpdatedDate = new Date();
    }
}
