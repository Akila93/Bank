package com.app.bank.service;

import com.app.bank.util.IOUtil;
import org.springframework.stereotype.Service;

@Service
public class StatementService {

    public void printStatement(){
        IOUtil.printBanner("print statement");
    }
}
