package views;

import models.LoginModel;
import utils.Utils;

public class LoginView {
    private LoginModel loginModel;
    private String username;
    private String password;

    public LoginView(){
        this.loginModel = new LoginModel();
    }

    public boolean authenticate(){
        username = Utils.getString("Enter username: ", "Username is required!");
        password = Utils.getString("Enter password: ", "Password is required!");
        return this.loginModel.authenticate(username, password);
    }
    public void readFromFile(){
        this.loginModel.readFromFile();
    }
}
