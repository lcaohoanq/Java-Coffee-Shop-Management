package controllers;

import views.ReportFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportFrameController implements ActionListener {
    private ReportFrame reportFrame;
    public  ReportFrameController(ReportFrame reportFrame){
        this.reportFrame = reportFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                //Show available ingredients
                break;
            case "2":
                //Show out of stock ingredients
                break;
            case "3":
                //View current order
                break;
            case "4":
                //Back to main frame
                break;
        }
    }
}
