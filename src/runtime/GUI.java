package runtime;

import views.*;

public class GUI {
    public static void main(String[] args) {
        initMainFrame();
        initIngredientFrame();
        initDrinkFrame();
        initOrderFrame();
        initReportFrame();

    }

    private static void initMainFrame(){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setVisible(true);
    }

    private static void initIngredientFrame(){
        IngredientFrame ingredientFrame = new IngredientFrame();
        ingredientFrame.setVisible(true);
    }

    private static void initDrinkFrame(){
        DrinkFrame drinkFrame = new DrinkFrame();
        drinkFrame.setVisible(true);
    }

    private static void initOrderFrame(){
        OrderFrame orderFrame = new OrderFrame();
        orderFrame.setVisible(true);
    }

    private static void initReportFrame(){
        ReportFrame reportFrame = new ReportFrame();
        reportFrame.setVisible(true);
    }
}
