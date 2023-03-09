package com.app.bank.service;

import com.app.bank.constant.LedgerStatus;
import com.app.bank.entity.Account;
import com.app.bank.entity.Ledger;
import com.app.bank.entity.Transaction;
import com.app.bank.repository.AccountRepository;
import com.app.bank.repository.LedgerRepository;
import com.app.bank.repository.TransactionRepository;
import com.app.bank.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    LedgerRepository ledgerRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private static final SimpleDateFormat formator = new SimpleDateFormat("dd MMM yyyy hh:mm:ss aa");

    public void printStatement(String accountNo){
        Optional<Account> accountOptional = accountRepository.findByAccountNo(accountNo);
        Account account = accountOptional.get();

        Optional<List<Ledger>> ledgerRecordsOptional=ledgerRepository.findByAccountAndLedgerStatus(account, LedgerStatus.SUCCESS);

        if(ledgerRecordsOptional.isPresent() && !ledgerRecordsOptional.get().isEmpty()){
            List<Ledger> ledgerRecordList = ledgerRecordsOptional.get();
            IOUtil.printBanner("Date                    | Amount | Balance");
            for(Ledger ledgerRecord: ledgerRecordList){
                String record = formator.format(ledgerRecord.getLastUpdatedDate())+" | "+ledgerRecord.getTransaction().getTransactionAmount()+"  | "+ledgerRecord.getClosingBalance();
                IOUtil.printBanner(record);
            }
        }

    }
}
