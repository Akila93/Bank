package com.app.bank.service;

import com.app.bank.constant.OperationType;
import com.app.bank.entity.Account;
import com.app.bank.util.IOUtil;
import com.app.bank.util.ModuleException;
import com.app.bank.util.SimulatorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.app.bank.constant.OperationType.*;

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
        displayInfoBanner(Optional.empty());
        do {
            try {
                HashMap<String,Object> parameters = new HashMap<String,Object>();
                performOperation(parameters);
                displayInfoBanner(Optional.of(parameters));
            } catch (ModuleException e) {
                IOUtil.printBanner(e.getMessage());
                requestedOperation = null;
                displayInfoBanner(Optional.of(PRINT_STATEMENT),Optional.empty());
            }
        } while (!"Q".equalsIgnoreCase(requestedOperation));
    }

    private void performOperation(HashMap<String, Object> parameters) throws ModuleException {
        String userInput = IOUtil.readInput();
        if(!IOUtil.validateOperationCodeInput(userInput)){
            throw new ModuleException("Invalid Operation Requested");
        }
        Optional<OperationType> operation = SimulatorUtil.getOperationType(userInput);
        requestedOperation = operation.get().toString();
        switch (operation.get()){
            case DEPOSIT:
                String depositedAmount = transactionService.deposit(simulatingAccount.getAccountNo());
                parameters.put("amount",depositedAmount);
                return;
            case WITHDRAW:
                String withdrawnAmount = transactionService.withdraw(simulatingAccount.getAccountNo());
                parameters.put("amount",withdrawnAmount);
                return;
            case PRINT_STATEMENT:
                statementService.printStatement(simulatingAccount.getAccountNo());
                return;
        }
    }

    private void displayInfoBanner(Optional<Map<String,Object>> parametersOptional) {
        Optional<OperationType> operation = SimulatorUtil.getOperationType(requestedOperation);
        displayInfoBanner(operation,parametersOptional);
    }
    private void displayInfoBanner(Optional<OperationType> operation, Optional<Map<String,Object>> parametersOptional) {

        if(!operation.isPresent()) {
            IOUtil.printBanner("Welcome to AwesomeGIC Bank! What would you like to do?");
            IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
            return;
        }
        Map<String,Object> params = null;
        String msgBanner;
        if(parametersOptional.isPresent()){
            params = parametersOptional.get();
        } else if(DEPOSIT.equals(operation.get()) || WITHDRAW.equals(operation.get())){
            throw new ModuleException("Fail to render the banner after transaction.");
        }
        switch (operation.orElse(null)){
            case DEPOSIT:
                msgBanner = String.format("Thank you. $%s has been deposited to your account.\nIs there anything else you'd like to do?",params.get("amount").toString());
                IOUtil.printBanner(msgBanner);
                IOUtil.printBanner("[D] eposit\n[W] ithdraw\n[P] rint statement\n[Q] uit");
                return;
            case WITHDRAW:
                msgBanner = String.format("Thank you. $%s has been withdrawn.\nIs there anything else you'd like to do?",params.get("amount").toString());
                IOUtil.printBanner(msgBanner);
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
