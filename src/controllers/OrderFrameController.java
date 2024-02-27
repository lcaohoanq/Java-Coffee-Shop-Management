package controllers;

import views.OrderFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrderFrameController implements ActionListener {
    private OrderFrame orderFrame;
    public OrderFrameController(OrderFrame orderFrame){
        this.orderFrame = orderFrame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                //Dispensing order
                break;
            case "2":
                //Update dispensed order
                break;
            case "3":
                //Back to main frame
                break;
        }
    }
}
