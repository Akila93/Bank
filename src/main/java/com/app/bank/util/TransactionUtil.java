package com.app.bank.util;

import com.app.bank.constant.TransactionType;
import com.app.bank.entity.Account;

public class TransactionUtil {
    public static void validateTransactionAmount(Account account, TransactionType transactionType, double amountInDouble) {
        if (TransactionType.DEPOSIT.equals(transactionType)) {
            if (Double.MAX_VALUE - account.getBalance() < amountInDouble) {
                throw new ModuleException("Cannot do the transaction. Account value could exceed the maximum possible amount.");
            }
            if (amountInDouble < 1) {
                throw new ModuleException("Cannot do the transaction. Minimum deposit is $1.");
            }
        } else if (TransactionType.WITHDRAW.equals(transactionType)) {
            if (account.getBalance() < amountInDouble) {
                throw new ModuleException("Cannot do the transaction. Insufficient account balance.");
            }
            if (amountInDouble < 1) {
                throw new ModuleException("Cannot do the transaction. Minimum withdraw is $1.");
            }
        } else {
            throw new ModuleException("Invalid transaction type.");
        }
    }
}
