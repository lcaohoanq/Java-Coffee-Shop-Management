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
        menu.addOption("Add new drink");
        menu.addOption("Update drink");
        menu.addOption("Delete drink");
        menu.addOption("Show all drinks");

        while(true){
            menu.print();
            int choice = menu.getChoice();
            switch(choice){
                case 1:
                    mm.addDrink();
                    break;
                case 2:
                    mm.updateDrink();
                    break;
                case 3:
                    mm.deleteDrink();
                    break;
                case 4:
                    mm.showAllDrinks();
                    break;
                default:
                    System.out.println("Goodbye");
                    return;
            }
        }

    }
}
