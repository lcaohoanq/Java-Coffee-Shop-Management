package controllers;

import constants.Message;
import constants.Regex;
import models.*;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.util.*;
import java.util.List;

public class MenuManagement implements Searchable<Menu>, Sortable<Menu>, FileService {
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
                    System.out.println(ConsoleColors.RED + Message.DRINK_CODE_IS_EXISTED + ", " + Message.ADD_DRINK_FAILED + ConsoleColors.RESET);
                    break;
                }
            }
        }while(isExist);
        String name = Utils.getString(Message.INPUT_DRINK_NAME, Regex.D_NAME, Message.DRINK_NAME_IS_REQUIRED, Message.DRINK_NAME_MUST_START_WITH_LETTER);
        recipe = new HashMap<>();
        do{
            System.out.println("-------------Select the ingredient at the list below-------------");
            im.showIngredientList();
            recipe.putAll(inputIngredientCodeAndQuantity());
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_ADD_INGREDIENT));
        //add the drink to the list
        menu = new Menu(code, name, recipe);
        menuList.add(menu);
        menu.showInfo();
        System.out.println(ConsoleColors.GREEN + Message.ADD_DRINK_SUCCESSFULLY + ConsoleColors.RESET);
    }

    private Map<Ingredient,Double> inputIngredientCodeAndQuantity(){
        Map<Ingredient,Double> ingredientMap = new HashMap<>();
        //input each ingredient code and quantity
        String iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE,Message.INGREDIENT_CODE_IS_REQUIRED, Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
        //find the ingredient by code
        //if the ingredient is not found, ask the user to input again
        if(im.checkToExist(iCode)){
            Double quantity = Utils.getDouble(Message.INPUT_INGREDIENT_QUANTITY, 0.0);
            //add the ingredient and quantity to the list
            recipe.put(im.searchObjectByCode(iCode), quantity);
        }else{
            System.out.println("The ingredient is not found");
        }
        return ingredientMap;
    }

    //func 2.2: Update the drink information
    //There 3 case to update: add, delete, adjust
    //Why i need to clarify? Because in real life, the drink recipe can be changed by adding, deleting or adjusting each ingredient
    public void addIngredientToDrink(){
        String dCode;
        do{
            this.showMenu();
            dCode = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            Menu menuItem = this.searchObjectByCode(dCode);
            if(menuItem == null) {
                System.out.println(Message.DRINK_DOES_NOT_EXIST);
                return;
            }
            Map<Ingredient, Double> drinkRecipe = menuItem.getRecipe();

            System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
            menuItem.showInfo();
            System.out.println("--------------Select the ingredient at the list below-------------");
            im.showIngredientList();
            String iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
            Ingredient ingredient = im.searchObjectByCode(iCode);
            if(ingredient == null){
                System.out.println("Ingredient is not exist");
            }else{
                System.out.printf("Input the %s quantity: ", ingredient.getName());
                double iQuantity = new Scanner(System.in).nextInt();
                drinkRecipe.put(ingredient, iQuantity);
            }
            System.out.println("After adding");
            menuItem.showInfo();
            System.out.println("Add drink ingredient successfully");
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void removeIngredientFromDrink(){
        String iCode,dCode;
        Ingredient ingredient;
        do{
            this.showMenu();

            dCode = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            Menu menuItem = this.searchObjectByCode(dCode);
            if(menuItem == null) {
                System.out.println(Message.DRINK_DOES_NOT_EXIST);
                return;
            }
            System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
            menuItem.showInfo();
            System.out.print("Input the ingredient code: ");
            iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
            ingredient = im.searchObjectByCode(iCode);
            if(ingredient == null){
                System.out.println("Ingredient is not exist");
            }else{
                if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_DELETE)){
                    return;
                }

                recipe = menuItem.getRecipe();
                recipe.remove(ingredient);
                menuItem.showInfo();
                System.out.println("Remove ingredient of drink successfully");
            }
        }while (Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void adjustIngredientInDrink(){
        String iCode;
        double iQuantity;
        boolean isExist = false;
        this.showMenu();
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObjectByCode(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_DOES_NOT_EXIST);
            return;
        }
        Map<Ingredient, Double> drinkRecipe = menuItem.getRecipe();

        System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
        menuItem.showInfo();
        String newDrinkName = Utils.getString("Input new drink name or blank: ", Regex.I_NAME, "Drink name must a letter");
        //can receive null or != null
        //if null then do not change the old information
        if(!newDrinkName.isEmpty()){
            menuItem.setName(newDrinkName);
        }
        do{
            iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
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
                        System.out.printf("Before update ingredient %s, %s\n", entry.getKey().getCode(), entry.getKey().getName());
                        System.out.printf("| %-5s | %10.2f |\n", entry.getKey().getCode(), entry.getValue());
                        iQuantity = Utils.getDouble(Message.INPUT_NEW_INGREDIENT_QUANTITY + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NUMBER,Message.INGREDIENT_QUANTITY_REQUIRED_A_NUMBER_OR_BLANK);
                        if(iQuantity != -1){
                            entry.setValue(iQuantity);
                        }
                        System.out.printf("After update ingredient %s, %s\n", entry.getKey().getCode(), entry.getKey().getName());
                        menuItem.showInfo();
                    }
                }
            }else{
                System.out.println("Ingredient is not exist");
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    //func 2.3: Delete the drink
    public void deleteDrink(){
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObjectByCode(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_DOES_NOT_EXIST);
            return;
        }
        System.out.println("---------------------Before deleting---------------------");
        menuItem.showInfo();
        if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_DELETE_DRINK)){
            return;
        }
        menuList.remove(menuItem);
        System.out.println(ConsoleColors.GREEN + Message.DELETE_DRINK_SUCCESSFULLY + ConsoleColors.RESET);
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
            System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET + "%10.0f VND\n", sum);
        }
    }

    @Override
    public void loadData(String path){
        try {
            File file = new File(path);
            if(!file.exists()){
                return;
            }
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            StringTokenizer stk;
            while((line = bufferedReader.readLine()) != null){
                stk = new StringTokenizer(line, "|");
                String code = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                Map<Ingredient, Double> recipe = new HashMap<>();
                String[] ingredients = stk.nextToken().split("\\s+");
                for (int i = 0; i < ingredients.length; i += 2) {
                    String iName = ingredients[i].trim();
                    double quantity = Double.parseDouble(ingredients[i + 1].trim());
                    Ingredient ingredient = this.im.searchObjectByName(iName); //transfer because the Recipe receive Map<Ingredient, Double> only
                    recipe.put(ingredient, quantity);
                }
                menuList.add(new Menu(code, name, recipe));
            }
            bufferedReader.close();
            System.out.println("Load data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(String path){
        try {
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            for(Menu menu : menuList){
                bufferedWriter.write(menu.getCode() + "|" + menu.getName() + "|");
                Map<Ingredient, Double> recipe = menu.getRecipe();
                for (Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
                    String iName = entry.getKey().getName();
                    double quantity = entry.getValue();
                    bufferedWriter.write(iName + " " + quantity + " ");
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Save data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean checkToExist(String code) {
        Menu menu = this.searchObjectByCode(code.trim());
        return menu != null;
    }

    @Override
    public int searchIndexByCode(String code) {
        for(int i = 0; i < menuList.size(); i++){
            if(menuList.get(i).getCode().equalsIgnoreCase(code.trim())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Menu searchObjectByCode(String code) {
        int pos = this.searchIndexByCode(code);
        return pos == -1 ? null : menuList.get(pos);
    }

    @Override
    public int searchIndexByName(String name) {
        for(int i = 0; i < menuList.size(); i++){
            if(menuList.get(i).getName().equals(name.trim())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Menu searchObjectByName(String name) {
        int pos = this.searchIndexByName(name);
        return pos == -1 ? null : menuList.get(pos);
    }

    @Override
    public void sortAscending(List<Menu> list) {
        list.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

}
