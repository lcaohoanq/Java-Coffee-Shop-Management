//package controllers;
//
//import constants.Message;
//import constants.Path;
//import models.MenuBuilder;
//import viewer.MainFrame;
//
//public class Controller {
//
//    int choice = 0;
//
//    MenuBuilder menu = new MenuBuilder("Coffee Shop Management");
//    MenuBuilder menuManageIngredients = new MenuBuilder("Manage Ingredients");
//    MenuBuilder menuManageDrinks = new MenuBuilder("Manage Beverage Recipes");
//    MenuBuilder menuManageDispensingBeverages = new MenuBuilder("Manage dispensing beverages");
//    MenuBuilder menuManageReport = new MenuBuilder("Manage Report");
//    MenuBuilder menuUpdateDrink = new MenuBuilder("Update Drink");
//    IngredientManagement im = new IngredientManagement();
//    MenuManagement mm = new MenuManagement(im);
//    OrderManagement om = new OrderManagement(im, mm);
//
//    CompanyManagement cm = new CompanyManagement();
//
//    public Controller() {
//        initMenu(menu, menuManageIngredients, menuManageDrinks, menuManageDispensingBeverages, menuManageReport, menuUpdateDrink);
//        doManagement(MyFrame.getBtnValue()); // nhận vào tham số từ GUI và chạy chức năng
//        // tương ứng
//    }
//
//    public void doManagement(int btnValue) {
//        try {
//            do {
//                menu.print();
//                // choice = menu.getChoice();
//                switch (btnValue) {
//                    case 1:
//                        // App.consoleArea.setText(null);
//                        cm.getEmployeeFromFile(Path.fileEmp, Path.filePL);
//
//                        MainFrame.jTextArea_Viewer.setText(cm.empList.toString());
//                        break;
//                    case 2:
//                        cm.getDeveloperByProgrammingLanguage(Path.filePL);
//                        MainFrame.jTextArea_Viewer.setText(cm.devSearchedList.toString());
//                        break;
//                    case 3:
//                        cm.getTestersHaveSalaryGreaterThan();
//                        MainFrame.jTextArea_Viewer.setText(cm.testerSearchedList.toString());
//                        break;
//                    case 4:
//                        cm.getEmployeeWithHighestSalary();
//                        break;
//                    case 5:
//                        cm.getLeaderWithMostEmployees();
//                        break;
//                    case 6:
//                        cm.sorted();
//                        Message.createSuccessMsg("Sorted");
//                        break;
//                }
//            } while (choice != menu.optionList.size());
//        } catch (Exception e) {
//            System.out.println("Loi Controller");
//        }
//    }
//
//    private static void initMenu(MenuBuilder mm, MenuBuilder im, MenuBuilder dm, MenuBuilder dbm, MenuBuilder rm, MenuBuilder udm){
//        mm.addOption("Manage ingredients");
//        mm.addOption("Manage beverage recipes");
//        mm.addOption("Dispensing beverages");
//        mm.addOption("Report");
//        mm.addOption("Store data to file");
//        mm.addOption("Exit");
//
//        im.addOption("Add an ingredient");
//        im.addOption("Update ingredient information");
//        im.addOption("Delete ingredient");
//        im.addOption("Show all ingredients");
//        im.addOption("Exit to main menu");
//
//        dm.addOption("Add the drink to menu");
//        dm.addOption("Update the drink information");
//        dm.addOption("Delete the drink from menu");
//        dm.addOption("Show menu");
//        dm.addOption("Exit to main menu");
//
//        udm.addOption("Add new ingredient to the drink");
//        udm.addOption("Remove ingredient from the drink");
//        udm.addOption("Adjust the ingredient in the drink");
//        udm.addOption("Exit to manage drinks menu");
//
//        dbm.addOption("Dispensing a drink");
//        dbm.addOption("Update the dispensing information");
//        dbm.addOption("Exit to main menu");
//
//        rm.addOption("The ingredients are available");
//        rm.addOption("The drinks for which the store is out of ingredients");
//        rm.addOption("Show all the dispensing drink");
//        rm.addOption("Exit to main menu");
//    }
//}
