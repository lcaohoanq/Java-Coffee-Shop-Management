package controllers;

import models.Ingredient;
import models.Searcher;
import utils.ConsoleColors;
import utils.StringTools;
import utils.Utils;
import constants.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class IngredientManagement implements Searcher<Ingredient> {
    private List<Ingredient> ingredientList = new ArrayList<>();
    public void addIngredient() {
        boolean isExist = false;
        String code;
       do{
           do {
               isExist = false; // reset isExisted
               code = Utils
                       .getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                               Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS)
                       .toUpperCase();
               for (Ingredient ingredient : ingredientList) {
                   if (ingredient.getCode().equals(code)) {
                       isExist = true;
                       System.out.println(Message.INGREDIENT_CODE_IS_EXISTED + ", " + Message.ADD_INGREDIENT_FAILED);
                       break;
                   }
               }
           } while (isExist);

           String name = Utils.getString(Message.INPUT_INGREDIENT_NAME, Regex.I_NAME, Message.INGREDIENT_NAME_IS_REQUIRED, Message.INGREDIENT_NAME_MUST_START_WITH_LETTER);
           String type = Utils.getString(Message.INPUT_INGREDIENT_TYPE, Regex.I_TYPE, Message.INGREDIENT_TYPE_IS_REQUIRED, Message.INGREDIENT_TYPE_MUST_A_LETTER);
           int quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, 0);
           String unit = Utils.getString(Message.INPUT_INGREDIENT_UNIT, Regex.I_UNIT, Message.INGREDIENT_UNIT_IS_REQUIRED, Message.INGREDIENT_UNIT_MUST_A_LETTER);
           Double price = Utils.getDouble(Message.INPUT_INGREDIENT_PRICE, 0);

           // add to userActionList
           ingredientList.add(new Ingredient(code,name,type,quantity,unit,price));
           System.out.println(Message.ADD_INGREDIENT_SUCCESSFULLY);
       }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void updateIngredient(){
        if(ingredientList.isEmpty()) {
            System.out.println("Ingredient list is empty");
            return;
        }
        String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS).toUpperCase();
        Ingredient ingredient = searchObject(code);
        int index = searchIndex(code);
        if (ingredient == null) {
            System.out.println(Message.INGREDIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("Before update: ");
            ingredient.showIngredient();

            String name = Utils.getString(Message.INPUT_INGREDIENT_NAME, Regex.I_NAME, Message.INGREDIENT_NAME_IS_REQUIRED, Message.INGREDIENT_NAME_MUST_START_WITH_LETTER);
            String type = Utils.getString(Message.INPUT_INGREDIENT_TYPE, Regex.I_TYPE, Message.INGREDIENT_TYPE_IS_REQUIRED, Message.INGREDIENT_TYPE_MUST_A_LETTER);
            int quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, 0);
            String unit = Utils.getString(Message.INPUT_INGREDIENT_UNIT, Regex.I_UNIT, Message.INGREDIENT_UNIT_IS_REQUIRED, Message.INGREDIENT_UNIT_MUST_A_LETTER);
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
            return;
        }
        String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS).toUpperCase();
        Ingredient ingredient = searchObject(code);
        int index = searchIndex(code);
        if (ingredient == null) {
            System.out.println(Message.INGREDIENT_DOES_NOT_EXIST);
        } else {
            System.out.println("Before delete: ");
            ingredient.showIngredient();
            if(!Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE)){
                return;
            }
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
            return;
        }
        sortIngredientListByName(ingredientList);
        String str = String.format(ConsoleColors.PURPLE_BACKGROUND + "%-5s%-20s%-10s%10s%5s%-6s", "Code", "Name", "Type", "Quantity", "Unit", "Price" + ConsoleColors.RESET);
        StringTools.printTitle("i");
        StringTools.printLine("i");
        for (Ingredient ingredient : ingredientList) {
            ingredient.showIngredient();
            StringTools.printLine("i");
        }
    }

    public void sortIngredientListByName(List<Ingredient> ingredientList){
        ingredientList.sort(new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    public void loadData(String path){
        if(!ingredientList.isEmpty()){
            ingredientList.clear();
        }
        try{
            File f = new File(path);
            if(!f.exists()){
                throw new Exception("File not found");
            }
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String data;
            while((data = br.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(data, "|");
                String code = stk.nextToken();
                String name = stk.nextToken();
                String type = stk.nextToken();
                int quantity = Integer.parseInt(stk.nextToken());
                String unit = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                ingredientList.add(new Ingredient(code, name, type, quantity, unit, price));
            }
            System.out.println("Load data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveData(String path){
        if(ingredientList.isEmpty()){
            System.out.println("Ingredient list is empty");
            return;
        }
        try{
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Ingredient ingredient : ingredientList){
                bw.write(ingredient.getCode() + "|" + ingredient.getName() + "|" + ingredient.getType() + "|" + ingredient.getQuantity() + "|" + ingredient.getUnit() + "|" + ingredient.getPrice());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Save data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    @Override
    public boolean checkToExist(String code) {
        Ingredient ingredient = this.searchObject(code);
        return ingredient == null? false : true;
    }

    @Override
    public int searchIndex(String code) {
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getCode().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Ingredient searchObject(String code) {
        int pos = this.searchIndex(code);
        return pos == -1 ? null : ingredientList.get(pos);
    }
}
