package controllers;

import views.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrameController implements ActionListener {

    private MainFrame mainFrame;
    private IngredientFrame ingredientFrame;
    private DrinkFrame drinkFrame;
    private OrderFrame orderFrame;
    private ReportFrame reportFrame;
    public MainFrameController(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        this.ingredientFrame = new IngredientFrame();
        this.drinkFrame = new DrinkFrame();
        this.orderFrame = new OrderFrame();
        this.reportFrame = new ReportFrame();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                ingredientFrame.setVisible(true);
                mainFrame.setVisible(false);
                break;
            case "2":
                drinkFrame.setVisible(true);
                mainFrame.setVisible(false);
                break;
            case "3":
                orderFrame.setVisible(true);
                mainFrame.setVisible(false);
                break;
            case "4":
                reportFrame.setVisible(true);
                mainFrame.setVisible(false);
                break;
            case "5":
                //Save to file at 3 managers
            case "6":
                System.exit(0);
        }
    }
}
