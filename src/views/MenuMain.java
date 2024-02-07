package views;

import constants.Path;
import controllers.IngredientManagement;
import controllers.MenuManagement;
import models.Ingredient;

public class MenuMain {
    public static void main(String[] args) {
        MenuManagement mm = new MenuManagement();
        IngredientManagement im = new IngredientManagement();

        im.loadData(Path.URL_INGREDIENT_TXT);

        Menu menu = new Menu("Test Drink Menu ");
        menu.addOption("Load data");
        menu.addOption("Add new drink");
        menu.addOption("Update drink");
        menu.addOption("Delete drink");
        menu.addOption("Show all drinks");
        menu.addOption("Save data");

        while (true) {
            menu.print();
            int choice = menu.getChoice();
            switch (choice) {
                case 1:
                    mm.loadData(Path.URL_MENU_TXT);
                    break;
                case 2:
                    mm.addDrink();
                    break;
                case 3:
                    mm.updateDrink();
                    break;
                case 4:
                    mm.deleteDrink();
                    break;
                case 5:
                    mm.showAllDrinks();
                    break;
                case 6:
                    mm.saveData(Path.URL_MENU_TXT);
                    break;
                case 0:
                    return;
            }
        }


    }
}
