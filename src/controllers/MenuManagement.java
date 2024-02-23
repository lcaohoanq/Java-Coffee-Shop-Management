package controllers;

import constants.Message;
import constants.Regex;
import models.*;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.util.*;
import java.util.List;

public class MenuManagement {
    private List<Menu> menuList = new ArrayList<>();
    private Map<Ingredient,Double> recipe;
    private IngredientManagement im;

    public MenuManagement(IngredientManagement im){
        this.im = im;
    }

    //func 2.1: Add the drink to the menu
    public void addDrink(){
        boolean isExist;
        String code;
        Menu menu;
        do{
            isExist = false;
            // Add a new drink to the list
            code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            for (Menu drink : menuList) {
                if (drink.getCode().equalsIgnoreCase(code)) {
                    isExist = true;
                    System.out.println(Message.DRINK_CODE_IS_EXISTED + ", " + Message.ADD_DRINK_FAILED);
                    break;
                }
            }
        }while(isExist);
        String name = Utils.getString(Message.INPUT_DRINK_NAME, Regex.D_NAME, Message.DRINK_NAME_IS_REQUIRED, Message.DRINK_NAME_MUST_START_WITH_LETTER);
        recipe = new HashMap<>();
        do{
            System.out.println(Message.SELECT_INGREDIENT_AT_LIST_BELOW);
            im.showIngredientList();
            recipe.putAll(inputIngredientCodeAndQuantity());
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_PICK_INGREDIENT));
        //add the drink to the list
        menu = new Menu(code, name, recipe);
        menuList.add(menu);
        menu.showAllInfo();
        System.out.println(Message.ADD_DRINK_SUCCESSFULLY);
    }

    private Map<Ingredient,Double> inputIngredientCodeAndQuantity(){
        Map<Ingredient,Double> ingredientMap = new HashMap<>();
        //input each ingredient code and quantity
        String iCode = Utils.getString(Message.INPUT_INGREDIENT_CODE, Regex.I_CODE,Message.INGREDIENT_CODE_IS_REQUIRED, Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
        //find the ingredient by code
        //if the ingredient is not found, ask the user to input again
        if(im.checkToExist(iCode)){
            Double quantity = Utils.getDouble(Message.INPUT_INGREDIENT_QUANTITY, 0.0);
            //add the ingredient and quantity to the list
            recipe.put(im.searchObjectByCode(iCode), quantity);
        }else{
            System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
        }
        return ingredientMap;
    }

    //func 2.2: Update the drink information
    //There 3 case to update: add, delete, adjust
    //Why i need to clarify? Because in real life, the drink recipe can be changed by adding, deleting or adjusting each ingredient
    public void addIngredientToDrink(){
        String dCode;
        do{
            System.out.println(Message.SELECT_DRINK_AT_LIST_BELOW);
            dCode = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            Menu menuItem = this.searchObjectByCode(dCode);
            if(menuItem == null) {
                System.out.println(Message.DRINK_IS_NOT_EXIST);
                return;
            }
            Map<Ingredient, Double> drinkRecipe = menuItem.getRecipe();

            System.out.println(Message.BEFORE_ADDING);
            menuItem.showAllInfo();
            String iCode = Utils.getString(Message.INPUT_INGREDIENT_CODE, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
            Ingredient ingredient = im.searchObjectByCode(iCode);
            if(ingredient == null){
                System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
            }else{
                System.out.printf("Input the %s quantity: ", ingredient.getName());
                double iQuantity = new Scanner(System.in).nextInt();
                drinkRecipe.put(ingredient, iQuantity);
            }
            System.out.println(Message.AFTER_UPDATING);
            menuItem.showAllInfo();
            System.out.println(Message.ADD_INGREDIENT_TO_DRINK_SUCCESSFULLY);
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void removeIngredientFromDrink(){
        String iCode,dCode;
        Ingredient ingredient;
        do{
            dCode = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            Menu menuItem = this.searchObjectByCode(dCode);
            if(menuItem == null) {
                System.out.println(Message.DRINK_IS_NOT_EXIST);
                return;
            }
            System.out.println(Message.BEFORE_DELETING);
            menuItem.showAllInfo();
            System.out.print(Message.INPUT_INGREDIENT_CODE);
            iCode = Utils.getString(Message.INPUT_INGREDIENT_CODE, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
            ingredient = im.searchObjectByCode(iCode);
            if(ingredient == null){
                System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
            }else{
                if(!Utils.getUserConfirmation(Message.DO_YOU_READY_WANT_TO_DELETE)){
                    return;
                }

                recipe = menuItem.getRecipe();
                recipe.remove(ingredient);
                menuItem.showAllInfo();
                System.out.println(Message.DELETE_INGREDIENT_FROM_DRINK_SUCCESSFULLY);
            }
        }while (Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void adjustIngredientInDrink(){
        String iCode;
        double iQuantity;
        boolean isExist = false;
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObjectByCode(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_IS_NOT_EXIST);
            return;
        }
        Map<Ingredient, Double> drinkRecipe = menuItem.getRecipe();

        System.out.println(Message.BEFORE_UPDATING);
        menuItem.showAllInfo();
        String newDrinkName = Utils.getString(Message.INPUT_NEW_DRINK_NAME + " or" + Message.BLANK_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NAME, Message.DRINK_NAME_MUST_START_WITH_LETTER);
        //can receive null or != null
        //if null then do not change the old information
        if(!newDrinkName.isEmpty()){
            menuItem.setName(newDrinkName);
        }
        do{
            iCode = Utils.getString(Message.INPUT_INGREDIENT_CODE, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS).toUpperCase();
            for(Map.Entry<Ingredient,Double> entry: drinkRecipe.entrySet()){
                if(entry.getKey().getCode().equalsIgnoreCase(iCode)){
                    isExist = true;
                    break;
                }
            }
            if(isExist){
                for (Map.Entry<Ingredient, Double> entry : drinkRecipe.entrySet()) {
                    if (entry.getKey().getCode().equalsIgnoreCase(iCode)) {
                        System.out.printf("| %-5s | %-15s | %10s |\n", "Code", "Name", "Quantity");
                        System.out.printf("| %-5s | %-15s | %10.2f |\n", entry.getKey().getCode(), entry.getKey().getName(), entry.getValue());
                        iQuantity = Utils.getDouble(Message.INPUT_NEW_INGREDIENT_QUANTITY + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.QUANTITY,Message.QUANTITY_REQUIRED_A_POSITIVE_INTEGER_OR_DOUBLE);
                        if(iQuantity != -1){
                            entry.setValue(iQuantity);
                        }
                        System.out.printf("After update ingredient %s, %s\n", entry.getKey().getCode(), entry.getKey().getName());
                        menuItem.showAllInfo();
                    }
                }
            }else{
                System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    //func 2.3: Delete the drink
    public void deleteDrink(){
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObjectByCode(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_IS_NOT_EXIST);
            return;
        }
        System.out.println(Message.BEFORE_UPDATING);
        menuItem.showAllInfo();
        if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_DELETE_DRINK)){
            return;
        }
        menuList.remove(menuItem);
        System.out.println(Message.DELETE_DRINK_SUCCESSFULLY);
    }

    //func 2.4: Show all drinks in the menu
    public void showMenu(){
        if(menuList.isEmpty()){
            System.out.println(Message.MENU_DRINK_IS_EMPTY);
            return;
        }
        double sum;
        this.sortAscending(menuList);
        for(Menu menu : menuList){
            sum = 0;
            System.out.printf(ConsoleColors.GREEN + "Drink code: %-5s\nDrink name: %-20s\n", menu.getCode(), menu.getName() + ConsoleColors.RESET);
            Map<Ingredient, Double> recipe = menu.getRecipe();
            System.out.printf("| %-5s | %-15s | %10s | %10s |  %19s |\n", "Code", "Name", "Quantity", "Price", "Amount");
            for (Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
                String code = entry.getKey().getCode();
                String name = entry.getKey().getName();
                double price = entry.getKey().getPrice();
                double quantity = entry.getValue();
                double amount = quantity * price;
                sum += amount;
                System.out.printf("| %-5s | %-15s | %10.1f | %10.0f |  %15.0f VND |\n", code, name, quantity, price, amount);
            }
            System.out.printf(Message.TOTAL + "%10.0f VND\n", sum);
        }
    }

    public void loadDataObject(String path) {
        if(!menuList.isEmpty()){
            menuList.clear();
        }
        try {
            File file = new File(path);
            if(!file.exists()){
                throw new IOException(Message.FILE_NOT_FOUND + path);
            }
            FileInputStream fi = new FileInputStream(file);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Menu menuItem;
            try{
                while((menuItem = (Menu) fo.readObject()) != null){
                    menuList.add(menuItem);
                }
            }catch (EOFException e){
                //do nothing
            }
            fo.close();
            fi.close();
            System.out.println(Message.READ_FILE_SUCCESSFULLY + path);
        } catch (Exception e) {
            System.out.println(Message.READ_FILE_FAILED + e.getMessage());
        }
    }

    public void saveDataObject(String path) {
        if(menuList.isEmpty()){
            System.out.println(Message.MENU_DRINK_IS_EMPTY);
            return;
        }
        try {
            File file = new File(path);
            FileOutputStream fOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(Menu menu : menuList){
                out.writeObject(menu);
            }
            out.close();
            fOut.close();
            System.out.println(Message.SAVE_FILE_SUCCESSFULLY + path);
        } catch (Exception e) {
            System.out.println(Message.SAVE_FILE_FAILED + e.getMessage());
        }
    }

    public boolean checkToExist(String code) {
        Menu menu = this.searchObjectByCode(code.trim());
        return menu != null;
    }

    public int searchIndexByCode(String code) {
        for(int i = 0; i < menuList.size(); i++){
            if(menuList.get(i).getCode().equalsIgnoreCase(code.trim())){
                return i;
            }
        }
        return -1;
    }

    public Menu searchObjectByCode(String code) {
        int pos = this.searchIndexByCode(code);
        return pos == -1 ? null : menuList.get(pos);
    }

    public void sortAscending(List<Menu> list) {
        list.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

}
