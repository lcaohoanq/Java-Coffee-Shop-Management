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

    }
}
