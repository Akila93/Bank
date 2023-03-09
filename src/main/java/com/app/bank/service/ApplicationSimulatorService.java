package com.app.bank.service;

import com.app.bank.constant.OperationType;
import com.app.bank.entity.Account;
import com.app.bank.util.IOUtil;
import com.app.bank.util.SimulatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationSimulatorService {

    private static String requestedOperation = null;
    private static Account simulatingAccount;

    @Autowired
    TransactionService transactionService;

    @Autowired
    StatementService statementService;

    public void simulateOperations(){
        simulatingAccount = new Account();
        simulatingAccount.setAccountNo("ACN00001");
        displayInfoBanner();
        do {
            try {
                performOperation();
                displayInfoBanner();
            } catch (Exception e) {
                //illegal operation
            }
        } while (!"Q".equalsIgnoreCase(requestedOperation));
    }

    private void performOperation() throws Exception {
        Optional<OperationType> operation = SimulatorUtil.getOperationType(IOUtil.readInput());
        if(operation.isPresent()){
            requestedOperation = operation.get().toString();
            switch (operation.get()){
                case DEPOSIT:
                    transactionService.deposit(simulatingAccount.getAccountNo());
                    return;
                case WITHDRAW:
                    transactionService.withdraw(simulatingAccount.getAccountNo());
                    return;
                case PRINT_STATEMENT:
                    statementService.printStatement(simulatingAccount.getAccountNo());
                    return;
                case QUIT:
                    System.out.println("quit");
            }
        } else {
            throw new Exception();
        }
    }

    private void displayInfoBanner() {

        Optional<OperationType> operation = SimulatorUtil.getOperationType(requestedOperation);

        if(!operation.isPresent()) {
            IOUtil.printBanner("Welcome to AwesomeGIC Bank! What would you like to do?");
            IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
            return;
        }
        switch (operation.orElse(null)){
            case DEPOSIT:
                IOUtil.printBanner("Thank you. $500 has been deposited to your account.\nIs there anything else you'd like to do?");
                IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
                return;
            case WITHDRAW:
                IOUtil.printBanner("Thank you. $100 has been withdrawn.\nIs there anything else you'd like to do?");
                IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
                return;
            case PRINT_STATEMENT:
                IOUtil.printBanner("Is there anything else you'd like to do?");
                IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
                return;
            case QUIT:
                IOUtil.printBanner("Thank you for banking with AwesomeGIC Bank.\nHave a nice day!");
                return;
        }
    }
}
