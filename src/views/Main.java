package views;

import constants.Path;
import controllers.IngredientManagement;
import controllers.MenuManagement;
import utils.ConsoleColors;
import models.Menu;

public class Main {
    public static void main(String[] args) {

        Menu menuManageIngredients;
        IngredientManagement im = new IngredientManagement();;
        im.loadData(Path.URL_INGREDIENT_TXT);

        Menu menuManageDrinks;
        MenuManagement mm = new MenuManagement();
        mm.loadData(Path.URL_MENU_TXT);

        Menu menuManageDispensingBeverages;
//        DispensingBeveragesManagement dbm = new DispensingBeveragesManagement();
//        dbm.loadData(Path.URL_ORDER_TXT);

        Menu menuManageReport;
//        ReportManagement rm = new ReportManagement();

        Menu menu = new Menu(ConsoleColors.GREEN + "Coffee Shop Management" + ConsoleColors.RESET);
        menu.addOption("Manage ingredients");
        menu.addOption("Manage beverage recipes");
        menu.addOption("Dispensing beverages");
        menu.addOption("Report");
        menu.addOption("Store data to file");
        menu.addOption("Exit");

        int choice;
        do{
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    menuManageIngredients = new Menu("Manage Ingredients");

                    menuManageIngredients.addOption("Add an ingredient");
                    menuManageIngredients.addOption("Update ingredient information");
                    menuManageIngredients.addOption("Delete ingredient");
                    menuManageIngredients.addOption("Show all ingredients");
                    menuManageIngredients.addOption("Exit to main menu");
                    int choiceManageIngredients;
                    do{
                        menuManageIngredients.print();
                        choiceManageIngredients = menuManageIngredients.getChoice();
                        switch (choiceManageIngredients) {
                            case 1:
                                im.addIngredient();
                                break;
                            case 2:
                                im.updateIngredient();
                                break;
                            case 3:
                                im.deleteIngredient();
                                break;
                            case 4:
                                im.showIngredientList();
                                break;
                            case 5:
                                break;
                        }
                    }while (choiceManageIngredients != menuManageIngredients.optionList.size());

                    break;
                case 2:
                    menuManageDrinks = new Menu("Manage Beverage Recipes");
                    menuManageDrinks.addOption("Add the drink to menu");
                    menuManageDrinks.addOption("Update the drink information");
                    menuManageDrinks.addOption("Delete the drink from menu");
                    menuManageDrinks.addOption("Show menu");
                    menuManageDrinks.addOption("Exit to main menu");

                    int choiceManageDrinks;
                    do {
                        menuManageDrinks.print();
                        choiceManageDrinks = menuManageDrinks.getChoice();
                        switch (choiceManageDrinks) {
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
                            case 5:
                                break;
                        }
                    }while (choiceManageDrinks != menuManageDrinks.optionList.size()) ;
                case 3:
                    menuManageDispensingBeverages = new Menu("Manage dispensing beverages");
                    menuManageDispensingBeverages.addOption("Dispensing the drink");
                    menuManageDispensingBeverages.addOption("Update the dispensing drink");
                    menuManageDispensingBeverages.addOption("Exit to main menu");

                    int choiceManageDispensingBeverages;
                    do{
                        menuManageDispensingBeverages.print();
                        choiceManageDispensingBeverages = menuManageDispensingBeverages.getChoice();
                        switch (choiceManageDispensingBeverages){
                            case 1:
//                                dbm.dispensingDrink();
                                break;

                            case 2:
//                                dbm.updateDispensingDrink();
                                break;

                            case 3:
                                break;

                        }
                    }while(choiceManageDispensingBeverages != menuManageDispensingBeverages.optionList.size());

                    break;
                case 4:
                    menuManageReport = new Menu("Manage Report");
                    menuManageReport.addOption("The ingredients are available");
                    menuManageReport.addOption("The drinks for which the store is out of ingredients");
                    menuManageReport.addOption("Show all the dispensing drink");
                    menuManageReport.addOption("Exit to main menu");
                    int choiceManageReport;
                    do{
                        menuManageReport.print();
                        choiceManageReport =  menuManageReport.getChoice();
                        switch (choiceManageReport){
                            case 1:
//                                rm.
                                break;
                            case 2:
//                                rm.
                                break;
                            case 3:
//                                rm.
                                break;
                            case 4:
                                break;
                        }

                    }while(choiceManageReport != menuManageReport.optionList.size());
                    break;
                case 5:
                    im.saveData(Path.URL_INGREDIENT_TXT);
                    mm.saveData(Path.URL_MENU_TXT);
//                    dbm.saveData(Path.URL_ORDER_TXT);
                    break;
                case 6:
                    System.out.println("Exit");
                    break;
            }
        }while(choice != menu.optionList.size());

    }
}
