package controllers;

import constants.Path;
import views.IngredientFrame;
import views.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IngredientFrameController implements ActionListener {
    private IngredientFrame ingredientFrame;
    private IngredientManagement im;
    private MainFrame mainFrame;
    private boolean isDataLoaded = true;
    public IngredientFrameController(IngredientFrame ingredientFrame) {
        this.mainFrame = mainFrame;
        this.ingredientFrame = ingredientFrame;
        this.im = new IngredientManagement();
        if (isDataLoaded) {
            im.loadDataObject(Path.URL_INGREDIENT_DAT);
            isDataLoaded = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String buttonText = e.getActionCommand();
        switch (buttonText) {
            case "1":
                im.addIngredient();
                break;
            case "2":
                im.deleteIngredient();
                break;
            case "3":
                im.updateIngredient();
                break;
            case "4":
                IngredientFrame.jTextArea_Viewer.setText(im.ingredientList.toString());
                break;
            case "5":
                mainFrame.setVisible(true);
                ingredientFrame.dispose();
                break;
            default:
                System.out.println("Unknown button action: " + buttonText);
        }
    }
}
