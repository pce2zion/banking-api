package com.example.bankingappdemo.utils;

import org.springframework.stereotype.Component;

import java.time.Year;
import java.util.Random;
@Component
public class AccountUtils {
    public final String accountExistsCode = "001";

    public final String accountCreationCode = "002";

    public final String accountNotExistsCode = "003";

    public final String accountFoundCode = "004";

    public final String accountCreditCode = "005";

    public final String accountDebitCode = "006";

    public final String fundsTransferCode = "007";
    public final String insufficientFundsCode = "008";
    public final String accountExistsMessage = "This account already exists";

    public final String accountNotExistMessage = "The account does not  exists";

    public final String accountFoundSuccess = "The account has been found";
    public final String FundsTransferMessage = "Funds transferred to recipient";
    public final String accountDebitMessage = "Account debited";
    public final String insufficientFundsMessage = "insufficient funds";
    public final String accountCreditMessage = "Account credited";
    public final String accountCreationMessage = "A new user has been created";
    Random random = new Random();

    Year currentYear = Year.now();
    String year = String.valueOf(currentYear);

    StringBuilder newStr = new StringBuilder();

    public String createAccountNo(){
        for(int i = 0; i < 7; i++){
            newStr.append(random.nextInt(10));
        }
       String main = String.valueOf(newStr);
        String accountNumber = year.concat(main);
        return accountNumber;
    }
}
