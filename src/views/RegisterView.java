package views;

import models.Account;
import models.RegisterModel;
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
        this.registerModel.insertUser(username, password);
    }
    public void updateToFile(){
        this.registerModel.updateToFile();
    }

}
