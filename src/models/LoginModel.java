package models;

import constants.Path;
import utils.AccountHandler;

public class LoginModel {

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
    public void readFromFile(){
        this.accountHandler.loadFromFile(Path.URL_ACCOUNT_DAT);
    }
}
