package views;

import models.Account;
import models.RegisterModel;
import utils.AccountHandler;
import utils.Utils;

public class RegisterView {

    private RegisterModel registerModel;
    private String username;
    private String password;
    private String confirmPassword;
    public RegisterView(){
        this.registerModel = new RegisterModel();
    }
    public boolean authenticate(){
        username = Utils.getString("Enter username: ", "Username is required!");
        password = Utils.getString("Enter password: ", "Password is required!");
        confirmPassword = Utils.getString("Enter confirm-password: ", "Confirm-password is required!");
        return this.registerModel.authenticate(username, password, confirmPassword);
    }
    public void insertUser(){
        System.out.println("Before insert");
        for(Account account : registerModel.accountHandler.getAccountList()){
            System.out.println(account);
        }
        this.registerModel.insertUser(username, password);
        System.out.println("After insert");
        for(Account account : registerModel.accountHandler.getAccountList()){
            System.out.println(account);
        }
    }
    public void updateToFile(){
        this.registerModel.updateToFile();
    }

}
