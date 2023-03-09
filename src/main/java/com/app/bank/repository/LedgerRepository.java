package com.app.bank.repository;

import com.app.bank.constant.LedgerStatus;
import com.app.bank.entity.Account;
import com.app.bank.entity.Ledger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger,Long> {
    Optional<List<Ledger>> findByAccountAndLedgerStatus(Account account, LedgerStatus ledgerStatus);
}
