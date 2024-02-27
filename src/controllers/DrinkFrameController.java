package controllers;

import views.DrinkFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrinkFrameController implements ActionListener {

    private DrinkFrame drinkFrame;

    public DrinkFrameController(DrinkFrame drinkFrame) {
        this.drinkFrame = drinkFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                //Add drink
                break;
            case "2":
                //Delete drink
                break;
            case "3":
                //Update drink
                break;
            case "4":
                //View drinks
                break;
            case "5":
                //Back to main frame
                break;
            default:
                System.out.println("Unknown button action: " + buttonText);
        }
    }
}
