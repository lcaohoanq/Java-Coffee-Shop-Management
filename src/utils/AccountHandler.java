package utils;

import java.util.ArrayList;
import java.util.List;
import models.Account;

public class AccountHandler {

    private final List<Account> accountList = new ArrayList<>();

    public AccountHandler() {
        accountList.add(new Account("admin", "admin", "admin"));
        accountList.add(new Account("hoang", "1", "staff"));
    }

    public List<Account> getAccountList() {
        return accountList;
    }


}
