package models;

import java.util.List;
import utils.AccountHandler;
import utils.Utils;

public class LoginModel {
    String username;
    String password;

    public List<Account> accountList = new AccountHandler().getAccountList();

    public List<Account> getAccountList() {
        return accountList;
    }

    public boolean authenticate(String username, String password){
        for (Account account : this.getAccountList()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
