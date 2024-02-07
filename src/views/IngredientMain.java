package views;

import constants.Path;
import controllers.IngredientManagement;
import views.Menu;

public class IngredientMain {

    public static void main(String[] args) {
        IngredientManagement idm = new IngredientManagement();

        Menu menu = new Menu("Coffee Shop Management System");
        menu.addOption("Load data from file");
        menu.addOption("Add an ingredient");
        menu.addOption("Update ingredient information");
        menu.addOption("Delete ingredient");
        menu.addOption("Show all ingredients");
        menu.addOption("Save data to file");

        int choice;
        do {
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    idm.loadData(Path.URL_INGREDIENT_TXT);
                    break;
                case 2:
                    idm.addIngredient();
                    break;
                case 3:
                    idm.updateIngredient();
                    break;
                case 4:
                    idm.deleteIngredient();
                    break;
                case 5:
                    idm.showIngredientList();
                    break;
                case 6:
                    idm.saveData(Path.URL_INGREDIENT_TXT);
                    break;
            }
        } while (choice != 0);


    }

    private static void manageSubmenu(){

    }

}
