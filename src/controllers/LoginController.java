package controllers;

import models.Account;
import runtime.Main;
import utils.Utils;

public class LoginController {

    RegisterController registerController;

    public LoginController(RegisterController registerController) {
        this.registerController = registerController;
    }

    public boolean authenticate() {
        String username = Utils.getString("Enter username: ", "Username is required!");
        String password = Utils.getString("Enter password: ", "Password is required!");
        for (Account account : registerController.getAccountList()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

}
