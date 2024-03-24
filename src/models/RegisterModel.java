package models;

import constants.Path;
import utils.AccountHandler;

public class RegisterModel{
    private AccountHandler accountHandler;
    public RegisterModel(){
        this.accountHandler = new AccountHandler();
    }
    public boolean authenticate(String username, String password, String confirmPassword){
        return isMatching(password,confirmPassword) && !isDuplicate(username);
    }

    public boolean isMatching(String password, String confirmPassword){
        return password.equals(confirmPassword);
    }
    public boolean isDuplicate(String username){
        for(Account account: accountHandler){
            if(account.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }
    public void insertUser(String username, String password){
        accountHandler.add(new Account(username, password));
    }
    public void updateToFile(){
        accountHandler.saveToFile(Path.URL_ACCOUNT_DAT);
    }
}
