package runtime;

import constants.Message;
import constants.Path;
import controllers.IngredientManagement;
import controllers.LoginController;
import controllers.MenuManagement;
import controllers.OrderManagement;
import controllers.RegisterController;
import enums.AuthenticateMenu;
import enums.DrinkMenu;
import enums.IngredientMenu;
import enums.MainMenu;
import enums.OrderMenu;
import enums.ReportMenu;
import enums.UpdateDrinkMenu;
import models.MenuBuilder;
import views.LoginView;
import views.RegisterView;

public class Main {

    public static void main(String[] args) {
        doAuthentication();
    }

    private static void doAuthentication(){
        MenuBuilder ma = new MenuBuilder("Authentication");
        initMenuAuthentication(ma);
        LoginController loginController = new LoginController(new LoginView());
        RegisterController registerController = new RegisterController(new RegisterView());
        int choice;
        do{
            ma.print();
            choice = ma.getChoice();
            switch (choice){
                case 1:
                    if (loginController.authenticate()) {
                        doManagement();
                    }else{
                        System.out.println(Message.LOGIN_FAILED);
                    }
                    break;
                case 2:
                    if(registerController.authenticate()){
                        System.out.println(Message.REGISTER_SUCCESS);
                        System.out.println("Please login to continue");
                        if (loginController.authenticate()) {
                            doManagement();
                        }else{
                            System.out.println(Message.LOGIN_FAILED);
                        }
                        break;
                    }
                    System.out.println(Message.REGISTER_FAILED);
                    break;
                case 3:
                    System.exit(0);
            }
        }while(choice != ma.optionList.size());
    }

    private static void doManagement(){
        MenuBuilder menu = new MenuBuilder("Coffee Shop Management");
        MenuBuilder menuManageIngredients = new MenuBuilder("Manage Ingredients");
        MenuBuilder menuManageDrinks = new MenuBuilder("Manage Beverage Recipes");
        MenuBuilder menuManageDispensingBeverages = new MenuBuilder("Manage dispensing beverages");
        MenuBuilder menuManageReport = new MenuBuilder("Manage Report");
        MenuBuilder menuUpdateDrink = new MenuBuilder("Update Drink");

        initMenu(menu, menuManageIngredients, menuManageDrinks, menuManageDispensingBeverages, menuManageReport, menuUpdateDrink);

        IngredientManagement im = new IngredientManagement();
        MenuManagement mm = new MenuManagement(im);
        OrderManagement om = new OrderManagement(im, mm);

        //Reload data when the program starts
        im.loadDataObject(Path.URL_INGREDIENT_DAT);
        mm.loadDataObject(Path.URL_MENU_DAT);
        om.loadDataObject(Path.URL_ORDER_DAT);

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
                    System.exit(0);
            }
        }while(choice != menu.optionList.size());
    }

    private static void initMenuAuthentication(MenuBuilder ma){
        for(AuthenticateMenu option : AuthenticateMenu.values()){
            ma.addOption(option.getValue());
        }
    }

    private static void initMenu(MenuBuilder mm, MenuBuilder im, MenuBuilder dm, MenuBuilder dbm, MenuBuilder rm, MenuBuilder udm){

        for(MainMenu option : MainMenu.values()){
            mm.addOption(option.getValue());
        }

        for(IngredientMenu option : IngredientMenu.values()){
            im.addOption(option.getValue());
        }

        for(DrinkMenu option : DrinkMenu.values()){
            dm.addOption(option.getValue());
        }

        for(UpdateDrinkMenu option : UpdateDrinkMenu.values()){
            udm.addOption(option.getValue());
        }

        for(OrderMenu option : OrderMenu.values()){
            dbm.addOption(option.getValue());
        }

        for(ReportMenu option : ReportMenu.values()){
            rm.addOption(option.getValue());
        }
    }
}
