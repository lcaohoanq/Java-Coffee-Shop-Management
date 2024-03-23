package views;

import models.Account;
import models.LoginModel;
import models.MenuBuilder;
import utils.Utils;

public class LoginView {
    private final MenuBuilder menuAuthentication = new MenuBuilder("Authentication");
    private LoginModel loginModel;
    private String username;
    private String password;

    public LoginView(){
        this.loginModel = new LoginModel();
        init();
    }

    public  LoginModel getLoginModel() {
        return loginModel;
    }

    public MenuBuilder getMenuAuthentication() {
        return menuAuthentication;
    }

    private void init(){
        menuAuthentication.addOption("Login");
        menuAuthentication.addOption("Register");
        menuAuthentication.addOption("Exit");
    }
    public boolean authenticate(){
        username = Utils.getString("Enter username: ", "Username is required!");
        password = Utils.getString("Enter password: ", "Password is required!");
        return this.loginModel.authenticate(username, password);
    }
}
