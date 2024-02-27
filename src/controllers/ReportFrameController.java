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

    }
}
