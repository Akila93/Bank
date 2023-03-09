package com.app.bank.service;

import com.app.bank.entity.Account;
import com.app.bank.repository.AccountRepository;
import com.app.bank.util.IOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StatementService {

    @Autowired
    AccountRepository accountRepository;

    public void printStatement(String accountNo){
        Optional<Account> accountOptional = accountRepository.findByAccountNo(accountNo);
        Account account = accountOptional.get();
        IOUtil.printBanner("print statement");
    }
}
