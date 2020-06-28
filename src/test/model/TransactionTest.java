package model;

import io.swagger.model.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class TransactionTest {

    @Test
    public void newTransactionIsNotNull() {
        Transaction transaction = new Transaction(20,"NL00INHO0011112222","NL00INHO3344445555",10,new Date(),"Test Transaction",30.0d);
        Assert.assertNotNull(transaction);
    }

    @Test
    public void invalidFromValueReturnsFalse() {
        Transaction transaction = new Transaction(20,"NL00INHO0011112222","NL00Bank3344445555",10,new Date(),"Test Transaction",30.0d);
        Assert.assertFalse(transaction.getFrom().matches("NL\\d{2}INHO\\d{10}"));
    }

    @Test
    public void invalidToValueReturnsFalse() {
        Transaction transaction = new Transaction(20,"NL00Bank0011112222","NL00INHO3344445555",10,new Date(),"Test Transaction",30.0d);
        Assert.assertFalse(transaction.getTo().matches("NL\\d{2}INHO\\d{10}"));
    }

    @Test
    public void invalidAmountToReturnFalse() {
        Transaction transaction = new Transaction(20,"NL00INHO0011112222","NL00INHO3344445555",10,new Date(),"Test Transaction",30.0d);
        Double transactionMoney = transaction.getMoney();
        boolean checkMoney;

        final double minAmount = 0;
        final double maxAmount = 99999;
        if (transactionMoney > minAmount && transactionMoney <= maxAmount)
            checkMoney = true;
        else
            checkMoney = false;
        Assert.assertTrue(checkMoney);
    }

    @Test
    public void invalidPerformingUserIDToReturnFalse() {
        Transaction transaction = new Transaction(20,"NL00INHO0011112222","NL00NINHO3344445555",10,new Date(),"Test Transaction",30.0d);
        Assert.assertTrue(transaction.getUserPerforming() > 0);
    }
}
