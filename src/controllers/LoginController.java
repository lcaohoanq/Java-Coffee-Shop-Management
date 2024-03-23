package controllers;

import views.LoginView;

public class LoginController {

    LoginView loginView;
    int count = 0;
    int attempts = 3;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    public boolean authenticate() {
        if(loginView.authenticate()){
            return true;
        }
        count++;
        System.out.printf("You have %d more attempts\n", attempts - count);
        if(count == attempts){
            System.exit(0);
        }
        return false;
    }

}
