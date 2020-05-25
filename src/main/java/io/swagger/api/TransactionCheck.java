package io.swagger.api;

import io.swagger.model.Transaction;
import io.swagger.model.Users;

public class TransactionCheck
{
    //Performs check on min/max amount and transaction limit
    private boolean transactionCheck(Transaction currentTransaction,Users userPerforming)
    {
        boolean transactionAllowed = false;

        int dayLimit = 5;
        double minimumAmount = 100.00f;
        double maxAmount = 1000.00f;

        if(currentTransaction.getAmount() > maxAmount)
        {
            transactionAllowed = false;
        }

        return transactionAllowed;
    }


}
