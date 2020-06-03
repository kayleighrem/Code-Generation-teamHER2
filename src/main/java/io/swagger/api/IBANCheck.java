package io.swagger.api;

import nl.garvelink.iban.Modulo97;

public class IBANCheck
{
    //Performs IBAN format Check
    public boolean ibanCheck(String IBANInput)
    {
        boolean valid = Modulo97.verifyCheckDigits(IBANInput);
        return valid;
    }


}
