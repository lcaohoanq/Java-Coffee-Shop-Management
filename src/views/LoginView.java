package views;

import models.Account;
import models.LoginModel;
import models.MenuBuilder;
import utils.Utils;

public class LoginView {
    private LoginModel loginModel;
    private String username;
    private String password;

    public LoginView(){
        this.loginModel = new LoginModel();
    }
    public  LoginModel getLoginModel() {
        return loginModel;
    }
    public boolean authenticate(){
        username = Utils.getString("Enter username: ", "Username is required!");
        password = Utils.getString("Enter password: ", "Password is required!");
        return this.loginModel.authenticate(username, password);
    }
}
