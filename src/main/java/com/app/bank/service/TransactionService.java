package com.app.bank.service;

import com.app.bank.repository.AccountRepository;
import com.app.bank.repository.LedgerRepository;
import com.app.bank.repository.TransactionRepository;
import com.app.bank.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    public String deposit(){
        IOUtil.printBanner("Please enter the amount to deposit:");
        String amount = IOUtil.readInput();
        return amount;
    }

    public String withdraw(){
        IOUtil.printBanner("Please enter the amount to withdraw:");
        String amount = IOUtil.readInput();
        return amount;
    }

}
