package model;
import io.swagger.model.Account;
import org.junit.Assert;
import org.junit.Test;


public class AccountTest {

    @Test
    public void newAccountIsNotNull() {
        Account account = new Account(69,600.2,"saving","NL00INHO001122334455");
        Assert.assertNotNull(account);
    }

    @Test
    public void invalidIBANReturnFalse() {
        Account account = new Account(69,600.2,"saving","NL00INHO001122334455");
        Assert.assertFalse(account.getFrom().matches("NL\\d{2}INHO\\d{10}"));
    }

    @Test
    public void invalidAmountToReturnFalse() {
        Account account = new Account(69,600.2,"saving","NL00INHO001122334455");
        Double accountMoney = account.getAcountAmount();
        boolean checkMoney;

        final double minAmount = 0;
        final double maxAmount = 999999999999;
        if (accountMoney > minAmount && accountMoneyMoney <= maxAmount)
            checkMoney = true;
        else
            checkMoney = false;
        Assert.assertTrue(checkMoney);
    }

    @Test
    public void invalidPerformingUserIDToReturnFalse() {
        Account account = new Account(69,600.2,"saving","NL00INHO001122334455");
        Assert.assertTrue(transaction.getUserPerforming() > 0);
    }
}
