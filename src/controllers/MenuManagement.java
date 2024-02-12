package controllers;

import constants.Message;
import constants.Regex;
import models.*;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.util.*;
import java.util.List;

public class MenuManagement implements Searchable<MenuDrink>, Sortable<MenuDrink>, FileService {
    private List<MenuDrink> drinkList = new ArrayList<>();
    private Map<Ingredient,Integer> recipe;
    private IngredientManagement idm;
//    private static Scanner sc = new Scanner(System.in);
    public MenuManagement(IngredientManagement idm){
        this.idm = idm;
    }
    //func 2.1: Add the drink to the menu
    public void addDrink(){
        boolean isExist = false;
        String code;
        do{
            isExist = false;
            // Add a new drink to the list
            code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            for (MenuDrink drink : drinkList) {
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
            System.out.println("-----Select the ingredient at the list below-----");
            idm.showIngredientList();
            recipe.putAll(inputIngredientCodeAndQuantity());
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_ADD_INGREDIENT));
        //add the drink to the list
        drinkList.add(new MenuDrink(code, name, recipe));
    }

    private Map<Ingredient,Integer> inputIngredientCodeAndQuantity(){
        Map<Ingredient,Integer> ingredientMap = new HashMap<>();
        //input each ingredient code and quantity
        String iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE,Message.INGREDIENT_CODE_IS_REQUIRED, Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
        //find the ingredient by code
        //if the ingredient is not found, ask the user to input again
        if(idm.checkToExist(iCode)){
            int quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, 0);
            //add the ingredient and quantity to the list
            recipe.put(idm.searchObjectByCode(iCode), quantity);
        }else{
            System.out.println("The ingredient is not found");
        }
        return ingredientMap;
    }

    //func 2.2: Update the drink informationb
    //There 3 case to update: add, delete, adjust
    //Why i need to clarify? Because in real life, the drink recipe can be changed by adding, deleting or adjusting each ingredient
    public void updateDrink(){
        String code;
        String iCode;
        int iQuantity;
        Ingredient ingredient;
        MenuDrink menuDrinkItem;
        Map<Ingredient,Integer> recipe;
        do{
            this.showMenu();
            code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            menuDrinkItem = this.searchObjectByCode(code);
            if(menuDrinkItem == null) {
                System.out.println(Message.DRINK_DOES_NOT_EXIST);
            }else{
                Map<Ingredient, Integer> drinkRecipe = menuDrinkItem.getRecipe();
                //check if the ingredient code is existed

                System.out.println("----------------Update drink option----------------");
                System.out.printf("1.Add new ingredient\n2.Remove ingredient\n3.Adjust ingredient\n");
                int choice = Utils.getInt("Input your choice: ", "Choice is required", 1, 3);
                switch(choice){
                    case 1:
                        System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
                        menuDrinkItem.showInfo();
                        System.out.println("-------------Select the ingredient at the list below-------------");
                        idm.showIngredientList();
                        System.out.print("Input the ingredient code: ");
                        iCode = new Scanner(System.in).nextLine();
                        ingredient = idm.searchObjectByCode(iCode);
                        if(ingredient == null){
                            System.out.println("Ingredient is not exist");
                        }else{
                            System.out.printf("Input the %s quantity: ", ingredient.getName());
                            iQuantity = new Scanner(System.in).nextInt();
                            drinkRecipe.put(ingredient,iQuantity);
                        }
                        System.out.println("After adding");
                        menuDrinkItem.showInfo();
                        System.out.println("Add drink ingredient successfully");
                        break;
                    case 2:
                        System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
                        menuDrinkItem.showInfo();
                        System.out.print("Input the ingredient code: ");
                        iCode = new Scanner(System.in).nextLine();
                        ingredient = idm.searchObjectByCode(iCode);
                        if(ingredient == null){
                            System.out.println("Ingredient is not exist");
                        }else{
                            if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_DELETE)){
                                return;
                            }

                            recipe = menuDrinkItem.getRecipe();
                            recipe.remove(ingredient);
                            menuDrinkItem.showInfo();
                            System.out.println("Remove ingredient of drink successfully");
                        }
                        break;
                    case 3:
                        do{
                            System.out.println(ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET);
                            menuDrinkItem.showInfo();
                            String newDrinkName = Utils.getString("Input new drink name or blank: ", Regex.I_NAME, "Drink name must a letter");
                            //can receive null or != null
                            //if null then do not change the old information
                            if(!newDrinkName.isEmpty()){
                                menuDrinkItem.setName(newDrinkName);
                            }
                            do{
                                System.out.print("Input the ingredient code to update: ");
                                iCode = new Scanner(System.in).nextLine();
                                ingredient = new Ingredient();
                                Map<Ingredient, Integer> currentIngredient = menuDrinkItem.getRecipe();
                                for(Map.Entry<Ingredient,Integer> entry: currentIngredient.entrySet()){
                                    if(!entry.getKey().getCode().equalsIgnoreCase(iCode)){
                                        ingredient = null;
                                        break;
                                    }
                                }
                                if(ingredient == null){
                                    System.out.println("Ingredient is not exist");
                                }else{
                                    System.out.printf("Before update ingredient %s, %s\n", ingredient.getCode(), ingredient.getName());
                                    ingredient.showIngredient();

                                    recipe = menuDrinkItem.getRecipe();
                                    for(Map.Entry<Ingredient, Integer> entry: recipe.entrySet()){
                                        System.out.printf("Before update of ingredient code: %s, name: %s\n", entry.getKey().getCode(), entry.getKey().getName());
                                        entry.getKey().showIngredient();

                                        //the empty name, unit receive ""
                                        //the empty quantity, price receive -1
                                        String newName = Utils.getString(Message.INPUT_NEW_INGREDIENT_NAME + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NAME, Message.INGREDIENT_NAME_REQUIRED_A_LETTER_OR_BLANK);
                                        int newQuantity = Utils.getInt(Message.INPUT_NEW_INGREDIENT_QUANTITY + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NUMBER,Message.INGREDIENT_QUANTITY_REQUIRED_A_NUMBER_OR_BLANK);
                                        String newUnit = Utils.getString(Message.INPUT_NEW_INGREDIENT_UNIT + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_UNIT, Message.INGREDIENT_UNIT_REQUIRED_A_LETTER_OR_BLANK);
                                        Double newPrice = Utils.getDouble(Message.INPUT_NEW_INGREDIENT_PRICE + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NUMBER, Message.INGREDIENT_PRICE_REQUIRED_A_NUMBER_OR_BLANK);

                                        //neu no khac rong thi update gia tri moi
                                        if(!newName.isEmpty()){
                                            entry.getKey().setName(newName);
                                        }
                                        if(!(newQuantity == -1)){
                                            entry.getKey().setQuantity(newQuantity);
                                        }
                                        if(!newUnit.isEmpty()){
                                            entry.getKey().setUnit(newUnit);
                                        }
                                        if(!(newPrice == - 1)){
                                            entry.getKey().setPrice(newPrice);
                                        }
                                        System.out.println("After update: ");
                                        entry.getKey().showIngredient();
                                        System.out.printf("Update ingredient successfully code: %s, name: %s\n", entry.getKey().getCode(), entry.getKey().getName());
                                    }
                                }
                            }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
                        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
                        break;
                }
                break;
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_UPDATE_DRINK));


    }

    //func 2.3: Delete the drink
    public void deleteDrink(){
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        MenuDrink menuDrinkItem = this.searchObjectByCode(code);
        if(menuDrinkItem == null) {
            System.out.println(Message.DRINK_DOES_NOT_EXIST);
            return;
        }
        System.out.println("Before deleting: ");
        menuDrinkItem.showInfo();
        if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_DELETE_DRINK)){
            return;
        }
        drinkList.remove(menuDrinkItem);
        System.out.println(Message.DELETE_DRINK_SUCCESSFULLY);
    }

    //func 2.4: Show all drinks in the menu
    public void showMenu(){
        if(drinkList.isEmpty()){
            System.out.println(Message.MENU_DRINK_IS_EMPTY);
            return;
        }
        double sum = 0;
        this.sortAscending(drinkList);
//        System.out.println(ConsoleColors.GREEN + "List of drinks: " + ConsoleColors.RESET);
        for(MenuDrink menuDrink : drinkList){
//            System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "  %-5s : %-20s" + ConsoleColors.RESET +"\n", menuDrink.getCode(), menuDrink.getName());
            System.out.printf(ConsoleColors.GREEN + "Drink code: %-5s\nDrink name: %-20s\n",menuDrink.getCode(),menuDrink.getName() + ConsoleColors.RESET);

            Map<Ingredient, Integer> recipe = menuDrink.getRecipe();
            System.out.printf("| %-5s | %-15s | %10s | %10s |  %15s |\n", "Code", "Name", "Quantity", "Price", "Amount");
            for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
                String code = entry.getKey().getCode();
                String name = entry.getKey().getName();
                double price = entry.getKey().getPrice();
                int quantity = entry.getValue();
                double amount = quantity * price;
                sum += amount;
                System.out.printf("| %-5s | %-15s | %10d | %10.1f |  %15.1f$ |\n", code, name, quantity, price, amount);
            }
            System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET + "%10.1f$\n", sum);
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
                Map<Ingredient, Integer> recipe = new HashMap<>();
                String[] ingredients = stk.nextToken().split("\\s+");
                for (int i = 0; i < ingredients.length; i += 2) {
                    String iName = ingredients[i].trim();
                    int quantity = Integer.parseInt(ingredients[i + 1].trim());
                    Ingredient ingredient = this.idm.searchObjectByName(iName); //transfer because the Recipe receive Map<Ingredient, Integer> only
                    recipe.put(ingredient, quantity);
                }
                drinkList.add(new MenuDrink(code, name, recipe));
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
            for(MenuDrink menuDrink : drinkList){
                bufferedWriter.write(menuDrink.getCode() + "|" + menuDrink.getName() + "|");
                Map<Ingredient, Integer> recipe = menuDrink.getRecipe();
                for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
                    String iName = entry.getKey().getName();
                    int quantity = entry.getValue();
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
        MenuDrink menuDrink = this.searchObjectByCode(code.trim());
        return menuDrink != null;
    }

    @Override
    public int searchIndexByCode(String code) {
        for(int i = 0; i < drinkList.size(); i++){
            if(drinkList.get(i).getCode().equalsIgnoreCase(code.trim())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public MenuDrink searchObjectByCode(String code) {
        int pos = this.searchIndexByCode(code);
        return pos == -1 ? null : drinkList.get(pos);
    }

    @Override
    public int searchIndexByName(String name) {
        for(int i = 0; i < drinkList.size(); i++){
            if(drinkList.get(i).getName().equals(name.trim())){
                return i;
            }
        }
        return -1;
    }

    @Override
    public MenuDrink searchObjectByName(String name) {
        int pos = this.searchIndexByName(name);
        return pos == -1 ? null : drinkList.get(pos);
    }

    @Override
    public void sortAscending(List<MenuDrink> list) {
        list.sort(new Comparator<MenuDrink>() {
            @Override
            public int compare(MenuDrink o1, MenuDrink o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    @Override
    public void sortDescending(List<MenuDrink> list) {

    }
}
