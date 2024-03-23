package runtime;

import constants.Path;
import controllers.IngredientManagement;
import controllers.LoginController;
import controllers.MenuManagement;
import controllers.OrderManagement;
import controllers.RegisterController;
import models.MenuBuilder;

public class Main {
    public static void main(String[] args) {

        //init menu
        MenuBuilder menuAuthentication = new MenuBuilder("Authentication");
        MenuBuilder menu = new MenuBuilder("Coffee Shop Management");
        MenuBuilder menuManageIngredients = new MenuBuilder("Manage Ingredients");
        MenuBuilder menuManageDrinks = new MenuBuilder("Manage Beverage Recipes");
        MenuBuilder menuManageDispensingBeverages = new MenuBuilder("Manage dispensing beverages");
        MenuBuilder menuManageReport = new MenuBuilder("Manage Report");
        MenuBuilder menuUpdateDrink = new MenuBuilder("Update Drink");

        initMenu(menuAuthentication, menu, menuManageIngredients, menuManageDrinks, menuManageDispensingBeverages, menuManageReport, menuUpdateDrink);

        RegisterController registerController = new RegisterController();
        LoginController loginController = null;
        boolean isLogin;
        int choice_tmp;
        do{
            menuAuthentication.print();
            choice_tmp = menuAuthentication.getChoice();
            switch (choice_tmp){
                case 1:
                    loginController = new LoginController(registerController);
                    isLogin = loginController.authenticate();
                    if (isLogin) {
                        System.out.println("Login successfully!");
                        break;
                    }else{
                        System.out.println("Login failed!");
                    }
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(0);
            }
        }while(choice_tmp != menuAuthentication.optionList.size());

        IngredientManagement im = new IngredientManagement();
        MenuManagement mm = new MenuManagement(im);
        OrderManagement om = new OrderManagement(im, mm);

        //Reload data when the program starts
        im.loadDataObject(Path.URL_INGREDIENT_DAT);
        mm.loadDataObject(Path.URL_MENU_DAT);
        om.loadDataObject(Path.URL_ORDER_DAT);
//        om2.

        int choice;
        do{
            menu.print();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
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
                    int choiceManageDrinks;
                    int choiceUpdateDrink;
                    do {
                        menuManageDrinks.print();
                        choiceManageDrinks = menuManageDrinks.getChoice();
                        switch (choiceManageDrinks) {
                            case 1:
                                mm.addDrink();
                                break;
                            case 2:
                                do{
                                    menuUpdateDrink.print();
                                    choiceUpdateDrink = menuUpdateDrink.getChoice();
                                    switch (choiceUpdateDrink){
                                        case 1:
                                            mm.addIngredientToDrink();
                                            break;
                                        case 2:
                                            mm.removeIngredientFromDrink();
                                            break;
                                        case 3:
                                            mm.adjustIngredientInDrink();
                                            break;
                                        case 4:
                                    }
                                }while (choiceUpdateDrink != menuUpdateDrink.optionList.size());
                                break;
                            case 3:
                                mm.deleteDrink();
                                break;
                            case 4:
                                mm.showMenu();
                                break;
                            case 5:
                                break;
                        }
                    }while (choiceManageDrinks != menuManageDrinks.optionList.size()) ;
                    break;
                case 3:
                    int choiceManageDispensingBeverages;
                    do{
                        menuManageDispensingBeverages.print();
                        choiceManageDispensingBeverages = menuManageDispensingBeverages.getChoice();
                        switch (choiceManageDispensingBeverages){
                            case 1:
                                om.dispensingDrink();
                                break;
                            case 2:
                                om.updateDispensingDrink();
                                break;
                            case 3:
                                break;
                        }
                    }while(choiceManageDispensingBeverages != menuManageDispensingBeverages.optionList.size());
                    break;
                case 4:
                    int choiceManageReport;
                    do{
                        menuManageReport.print();
                        choiceManageReport =  menuManageReport.getChoice();
                        switch (choiceManageReport){
                            case 1:
                                im.showIngredientList("available");
                                break;
                            case 2:
                                im.showIngredientList("out");
                                break;
                            case 3:
                                om.showCurrentOrder();
                                break;
                            case 4:
                                break;
                        }

                    }while(choiceManageReport != menuManageReport.optionList.size());
                    break;
                case 5:
                    im.saveDataObject(Path.URL_INGREDIENT_DAT);
                    mm.saveDataObject(Path.URL_MENU_DAT);
                    om.saveDataObject(Path.URL_ORDER_DAT);
                    break;
                case 6:
                    System.out.println("Thanks for using our service!");
            }
        }while(choice != menu.optionList.size());

    }

    //mm = main menu, im = ingredient menu, dm = drink menu, dbm = dispensing beverage menu, rm = report menu, udm = update drink menu
    private static void initMenu(MenuBuilder ma ,MenuBuilder mm, MenuBuilder im, MenuBuilder dm, MenuBuilder dbm, MenuBuilder rm, MenuBuilder udm){
        ma.addOption("Login");
        ma.addOption("Register");
        ma.addOption("Exit");

        mm.addOption("Manage ingredients");
        mm.addOption("Manage beverage recipes");
        mm.addOption("Dispensing beverages");
        mm.addOption("Report");
        mm.addOption("Store data to file");
        mm.addOption("Exit");

        im.addOption("Add an ingredient");
        im.addOption("Update ingredient information");
        im.addOption("Delete ingredient");
        im.addOption("Show all ingredients");
        im.addOption("Exit to main menu");

        dm.addOption("Add the drink to menu");
        dm.addOption("Update the drink information");
        dm.addOption("Delete the drink from menu");
        dm.addOption("Show menu");
        dm.addOption("Exit to main menu");

        udm.addOption("Add new ingredient to the drink");
        udm.addOption("Remove ingredient from the drink");
        udm.addOption("Adjust the ingredient in the drink");
        udm.addOption("Exit to manage drinks menu");

        dbm.addOption("Dispensing a drink");
        dbm.addOption("Update the dispensing information");
        dbm.addOption("Exit to main menu");

        rm.addOption("The ingredients are available");
        rm.addOption("The drinks for which the store is out of ingredients");
        rm.addOption("Show all the dispensing drink");
        rm.addOption("Exit to main menu");
    }
}
