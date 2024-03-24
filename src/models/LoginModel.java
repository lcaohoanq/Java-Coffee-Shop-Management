package models;

import java.util.List;
import utils.AccountHandler;
import utils.Utils;

public class LoginModel {
    String username;
    String password;

    private AccountHandler accountHandler;

    public LoginModel(){
        this.accountHandler = new AccountHandler();
    }

    public boolean authenticate(String username, String password){
        for (Account account : accountHandler) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


}
