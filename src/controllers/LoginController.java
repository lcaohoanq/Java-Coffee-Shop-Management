package controllers;

import views.LoginView;

public class LoginController {

    private LoginView loginView;
    private int count = 0;
    private final int attempts = 3;
    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }
    public boolean authenticate() {
        loginView.readFromFile();
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

    public static void main(String[] args) {
        LoginController loginController = new LoginController(new LoginView());
       loginController.authenticate();
    }

}
