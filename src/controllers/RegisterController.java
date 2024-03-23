package controllers;

import java.util.ArrayList;
import java.util.List;
import models.Account;

public class RegisterController {

    private List<Account> accountList = new ArrayList<>();
    public RegisterController() {
        accountList.add(new Account("admin", "admin", "admin"));
        accountList.add(new Account("hoang", "1", "staff"));
    }
    public List<Account> getAccountList() {
        return accountList;
    }
}
