package controllers;

import models.Ingredient;
import utils.ConsoleColors;
import utils.Utils;
import constants.*;
import java.util.ArrayList;

public class IngredientManagement {
    private ArrayList<Ingredient> ingredientList = new ArrayList<>();
    public void addIngredient() {
        boolean isExist = false;
        String code;
       do{
           do {
               isExist = false; // reset isExisted
               code = Utils
                       .getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                               Message.INGREDIENT_CODE_MUST_BE_H_AND_2_DIGITS)
                       .toUpperCase();
               for (Ingredient ingredient : ingredientList) {
                   if (ingredient.getCode().equals(code)) {
                       isExist = true;
                       System.out.println(Message.INGREDIENT_CODE_IS_EXISTED + "\n" + Message.ADD_INGREDIENT_FAILED);
                       break;
                   }
               }
           } while (isExist);

           String name = Utils.getString(Message.INPUT_INGREDIENT_NAME, Regex.I_NAME, Message.INGREDIENT_NAME_IS_REQUIRED, Message.INGREDIENT_NAME_MUST_START_WITH_LETTER);
           String type = Utils.getString(Message.INPUT_INGREDIENT_TYPE, Regex.I_TYPE, Message.INGREDIENT_TYPE_IS_REQUIRED, Message.INGREDIENT_TYPE_MUST_A_LETTER);
           Integer quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, Regex.I_QUANTITY, 0,100);
           String unit = Utils.getString(Message.INPUT_INGREDIENT_UNIT, Regex.I_UNIT, Message.INGREDIENT_QUANTITY_IS_REQUIRED, Message.INGREDIENT_QUANTITY_MUST_BE_A_POSITIVE_NUMBER);
           Double price = Utils.getDouble(Message.INPUT_INGREDIENT_PRICE, 0);

           // add to userActionList
           ingredientList.add(new Ingredient(code,name,type,quantity,unit,price));
           System.out.println(Message.ADD_INGREDIENT_SUCCESSFULLY);
       }while(Utils.getUserConfirmation());
    }

    public void updateIngredient(){
        if(ingredientList.isEmpty()) {
            System.out.println("Ingredient list is empty");
        }
        String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                Message.INGREDIENT_CODE_MUST_BE_H_AND_2_DIGITS).toUpperCase();
        Ingredient ingredient = searchIngredientByCode(code);
        int index = searchIngredientIndex(code);
        if (ingredient == null) {
            System.out.println(Message.INGREDIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("Before update: ");
            ingredient.showIngredient();

            String name = Utils.getString(Message.INPUT_INGREDIENT_NAME, Regex.I_NAME, Message.INGREDIENT_NAME_IS_REQUIRED, Message.INGREDIENT_NAME_MUST_START_WITH_LETTER);
            String type = Utils.getString(Message.INPUT_INGREDIENT_TYPE, Regex.I_TYPE, Message.INGREDIENT_TYPE_IS_REQUIRED, Message.INGREDIENT_TYPE_MUST_A_LETTER);
            Integer quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, Regex.I_QUANTITY, 0,100);
            String unit = Utils.getString(Message.INPUT_INGREDIENT_UNIT, Regex.I_UNIT, Message.INGREDIENT_QUANTITY_IS_REQUIRED, Message.INGREDIENT_QUANTITY_MUST_BE_A_POSITIVE_NUMBER);
            Double price = Utils.getDouble(Message.INPUT_INGREDIENT_PRICE, 0);

            ingredientList.set(index, new Ingredient(code,name,type,quantity,unit,price));
            System.out.println("After update: ");
            ingredientList.get(index).showIngredient();
            System.out.println(Message.UPDATE_INGREDIENT_SUCCESSFULLY);
        }
    }

    public void deleteIngredient(){
        if(ingredientList.isEmpty()) {
            System.out.println("Ingredient list is empty");
        }
        String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                Message.INGREDIENT_CODE_MUST_BE_H_AND_2_DIGITS).toUpperCase();
        Ingredient ingredient = searchIngredientByCode(code);
        int index = searchIngredientIndex(code);
        if (ingredient == null) {
            System.out.println(Message.INGREDIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("Before delete: ");
            ingredient.showIngredient();
            ingredientList.remove(index);
            System.out.println(Message.DELETE_INGREDIENT_SUCCESSFULLY);
        }
    }
    public void exitProgram(){
        System.out.println("Exit program");
        System.exit(0);
    }

    public void showIngredientList(){
        if(ingredientList.isEmpty()) {
            System.out.println("Ingredient list is empty");
        }
        String str = String.format(ConsoleColors.PURPLE_BACKGROUND + "%-5s%-20s%-10s%10s%5s%-6s", "Code", "Name", "Type", "Quantity", "Unit", "Price" + ConsoleColors.RESET);
        for (Ingredient ingredient : ingredientList) {
            ingredient.showIngredient();
        }
    }

    private int searchIngredientIndex(String code) {
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getCode().equals(code)) {
                return i;
            }
        }
        return -1;
    }

    private Ingredient searchIngredientByCode(String code) {
        int pos = this.searchIngredientIndex(code);
        return pos == -1 ? null : ingredientList.get(pos);
    }

    public static void main(String[] args) {

    }

}
