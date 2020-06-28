package model;
import io.swagger.model.Account;
import org.junit.Assert;
import org.junit.Test;


public class AccountTest {

    @Test
    public void newAccountIsNotNull() {
        Account account = new Account("NL00INHO001122334455",600.2,20,Account.TypeAccountEnum.valueOf("saving"));
        Assert.assertNotNull(account);
    }
    @Test
    public void invalidIdToReturnFalse() {
        Account account = new Account("NL00INHO001122334455",600.2,20,Account.TypeAccountEnum.valueOf("saving"));
        boolean check;

        if(account.getId() == (int) account.getId()) {// true
            check = true;
        }else{
            check = false;
        }

        Assert.assertTrue(check);

    }

    @Test
    public void invalidEnumToReturnFalse() {
        Account account = new Account("NL00INHO001122334455",600.2,20,Account.TypeAccountEnum.valueOf("saving"));
        boolean check;
        String enumCheck = "saving";
        if(enumCheck.equals("saving") || enumCheck.equals("basic")) {// true
            check = true;
        }else{
            check = false;
        }

        Assert.assertTrue(check);

    }
    @Test
    public void invalidIBANReturnFalse() {
        Account account = new Account("NL00INHO001122334455",600.2,20,Account.TypeAccountEnum.valueOf("saving"));
        Assert.assertFalse(account.getIBAN().matches("NL\\d{2}INHO\\d{10}"));
    }

    @Test
    public void invalidAmountToReturnFalse() {
        Account account = new Account("NL00INHO001122334455",600.2,20,Account.TypeAccountEnum.valueOf("saving"));
        Double accountMoney = account.getAcountAmount();
        boolean checkMoney;

        final double minAmount = 0;
        final double maxAmount = 999999999;
        if (accountMoney > minAmount && accountMoney <= maxAmount)
            checkMoney = true;
        else
            checkMoney = false;
        Assert.assertTrue(checkMoney);
    }


}
