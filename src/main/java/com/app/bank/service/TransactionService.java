package com.app.bank.service;

import com.app.bank.constant.LedgerStatus;
import com.app.bank.constant.TransactionType;
import com.app.bank.entity.Account;
import com.app.bank.entity.Ledger;
import com.app.bank.entity.Transaction;
import com.app.bank.repository.AccountRepository;
import com.app.bank.repository.LedgerRepository;
import com.app.bank.repository.TransactionRepository;
import com.app.bank.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    public String deposit(String accountNo){
        Optional<Account> accountOptional = accountRepository.findByAccountNo(accountNo);
        Account account = accountOptional.get();
        IOUtil.printBanner("Please enter the amount to deposit:");
        String amount = IOUtil.readInput();
        double amountInDouble=Double.parseDouble(amount);

        Ledger ledgerRecord = new Ledger();
        ledgerRecord.setAccount(account);
        ledgerRecord.setOpeningBalance(account.getBalance());
        ledgerRecord.setClosingBalance(account.getBalance()+amountInDouble);
        ledgerRecord.setLedgerStatus(LedgerStatus.PENDING);
        Ledger savedLedgerRecord = ledgerRepository.save(ledgerRecord);


        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amountInDouble);
        transaction.setTransactionType(TransactionType.DEPOSIT);
        transaction.setRemarks(accountNo+"_"+TransactionType.DEPOSIT+"_"+new Date().toString());
        Transaction savedTransaction = transactionRepository.save(transaction);

        account.setBalance(account.getBalance()+amountInDouble);
        accountRepository.save(account);

        savedLedgerRecord.setLedgerStatus(LedgerStatus.SUCCESS);
        savedLedgerRecord.setTransaction(savedTransaction);
        ledgerRepository.save(savedLedgerRecord);
        return amount;
    }

    public String withdraw(String accountNo){
        Optional<Account> accountOptional = accountRepository.findByAccountNo(accountNo);
        Account account = accountOptional.get();
        IOUtil.printBanner("Please enter the amount to withdraw:");
        String amount = IOUtil.readInput();
        double amountInDouble=Double.parseDouble(amount);

        Ledger ledgerRecord = new Ledger();
        ledgerRecord.setAccount(account);
        ledgerRecord.setOpeningBalance(account.getBalance());
        ledgerRecord.setClosingBalance(account.getBalance()-amountInDouble);
        ledgerRecord.setLedgerStatus(LedgerStatus.PENDING);
        Ledger savedLedgerRecord = ledgerRepository.save(ledgerRecord);


        Transaction transaction = new Transaction();
        transaction.setTransactionAmount(amountInDouble);
        transaction.setTransactionType(TransactionType.WITHDRAW);
        transaction.setRemarks(accountNo+"_"+TransactionType.WITHDRAW+"_"+new Date().toString());
        Transaction savedTransaction = transactionRepository.save(transaction);

        account.setBalance(account.getBalance()-amountInDouble);
        accountRepository.save(account);

        savedLedgerRecord.setLedgerStatus(LedgerStatus.SUCCESS);
        savedLedgerRecord.setTransaction(savedTransaction);
        ledgerRepository.save(savedLedgerRecord);
        return amount;
    }

}
