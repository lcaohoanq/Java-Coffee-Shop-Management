package controllers;

import views.RegisterView;

public class RegisterController {

    private RegisterView registerView;
    public RegisterController(RegisterView registerView) {
        this.registerView = registerView;
    }
    public boolean authenticate(){
        if(registerView.authenticate()){
            registerView.insertUser();
            registerView.updateToFile();
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        RegisterController registerController = new RegisterController(new RegisterView());
        registerController.authenticate();
    }
    

}
