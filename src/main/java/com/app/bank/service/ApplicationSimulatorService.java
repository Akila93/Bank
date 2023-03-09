package com.app.bank.service;

import com.app.bank.constant.OperationType;
import com.app.bank.entity.Account;
import com.app.bank.util.IOUtil;
import com.app.bank.util.ModuleException;
import com.app.bank.util.SimulatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.app.bank.constant.OperationType.PRINT_STATEMENT;

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
        IOUtil.printBanner("");
        displayInfoBanner();
        do {
            try {
                performOperation();
                displayInfoBanner();
            } catch (ModuleException e) {
                IOUtil.printBanner(e.getMessage());
                requestedOperation = null;
                displayInfoBanner(Optional.of(PRINT_STATEMENT));
            }
        } while (!"Q".equalsIgnoreCase(requestedOperation));
    }

    private void performOperation() throws ModuleException {
        String userInput = IOUtil.readInput();
        if(!IOUtil.validateOperationCodeInput(userInput)){
            throw new ModuleException("Invalid Operation Requested");
        }
        Optional<OperationType> operation = SimulatorUtil.getOperationType(userInput);
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
        }
    }

    private void displayInfoBanner() {
        Optional<OperationType> operation = SimulatorUtil.getOperationType(requestedOperation);
        displayInfoBanner(operation);
    }
    private void displayInfoBanner(Optional<OperationType> operation) {

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
