package controllers;

import models.FileService;
import models.Ingredient;
import models.Searchable;
import models.Sortable;
import utils.StringTools;
import utils.Utils;
import constants.*;

import java.io.*;
import java.util.*;

public class IngredientManagement implements Searchable<Ingredient>, Sortable<Ingredient>, FileService {
    private List<Ingredient> ingredientList = new ArrayList<>();
    private List<Ingredient> availableIngredientList = new ArrayList<>();
    private List<Ingredient> outOfIngredientList = new ArrayList<>();
    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public void addIngredient() {
        this.showIngredientList();
        boolean isExist;
        String code;
       do{
           do {
               isExist = false; // reset isExisted
               code = Utils
                       .getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                               Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
               for (Ingredient ingredient : ingredientList) {
                   if (ingredient.getCode().equalsIgnoreCase(code)) {
                       isExist = true;
                       System.out.println(Message.INGREDIENT_CODE_IS_EXISTED);
                       break;
                   }
               }
           } while (isExist);

           String name = Utils.getString(Message.INPUT_INGREDIENT_NAME, Regex.I_NAME, Message.INGREDIENT_NAME_IS_REQUIRED, Message.INGREDIENT_NAME_MUST_START_WITH_LETTER);
           //khi them nguyen lieu moi, so luong phai lon hon 0
           double quantity = Utils.getDouble(Message.INPUT_INGREDIENT_QUANTITY, 0.0);
           String unit = Utils.getString(Message.INPUT_INGREDIENT_UNIT, Regex.I_UNIT, Message.INGREDIENT_UNIT_IS_REQUIRED, Message.INGREDIENT_UNIT_MUST_A_LETTER);
           double price = Utils.getDouble(Message.INPUT_INGREDIENT_PRICE, 0.0);

           // add to userActionList
           ingredientList.add(new Ingredient(code,name,quantity,unit,price));
           System.out.println(Message.ADD_INGREDIENT_SUCCESSFULLY);
       }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void updateIngredient(){
        if(ingredientList.isEmpty()) {
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        //show the list of ingredient to choose which one need to update
        System.out.println("Select the ingredient you want to update below:");
        this.showIngredientList();
        do{
            String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                    Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS).toUpperCase();
            Ingredient ingredient = searchObjectByCode(code);
//            int index = searchIndexByCode(code);
            if (ingredient == null) {
                System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
            } else {
                System.out.println("Before update: ");
//                ingredientList.get(index).showIngredient();
                StringTools.printTitle("i");
                StringTools.printLine("i");
                ingredient.showIngredient();
                StringTools.printLine("i");

                //the empty name, unit receive ""
                //the empty quantity, price receive -1
                String newName = Utils.getString(Message.INPUT_NEW_INGREDIENT_NAME + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NAME, Message.INGREDIENT_NAME_REQUIRED_A_LETTER_OR_BLANK);
                double newQuantity = Utils.getDouble(Message.INPUT_NEW_INGREDIENT_QUANTITY + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.QUANTITY,Message.QUANTITY_REQUIRED_A_POSITIVE_INTEGER_OR_DOUBLE);
                String newUnit = Utils.getString(Message.INPUT_NEW_INGREDIENT_UNIT + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_UNIT, Message.INGREDIENT_UNIT_REQUIRED_A_LETTER_OR_BLANK);
                double newPrice = Utils.getDouble(Message.INPUT_NEW_INGREDIENT_PRICE + " or" + Message.ENTER_TO_KEEP_THE_OLD_INFORMATION, Regex.I_NUMBER, Message.INGREDIENT_PRICE_REQUIRED_A_NUMBER_OR_BLANK);

                //neu no khac rong thi update gia tri moi
                if(!newName.isEmpty()){
                    ingredient.setName(newName);
                }
                if(!(newQuantity == -1)){
                    ingredient.setQuantity(newQuantity);
                }
                if(!newUnit.isEmpty()){
                    ingredient.setUnit(newUnit);
                }
                if(!(newPrice == - 1)){
                    ingredient.setPrice(newPrice);
                }
                System.out.println("After update: ");
                StringTools.printTitle("i");
                StringTools.printLine("i");
                ingredient.showIngredient();
                StringTools.printLine("i");
                System.out.println(Message.UPDATE_INGREDIENT_SUCCESSFULLY);
                break;
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
    }

    public void deleteIngredient(){
        if(ingredientList.isEmpty()) {
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        //show the list of ingredient to choose which one need to delete
        System.out.println("Select the ingredient you want to delete below:");
        this.showIngredientList();
        String code = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED,
                Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS).toUpperCase();
        Ingredient ingredient = searchObjectByCode(code);
        int index = searchIndexByCode(code);
        if (ingredient == null) {
            System.out.println(Message.INGREDIENT_IS_NOT_EXIST);
        }else {
            System.out.println("Before delete: ");
            StringTools.printTitle("i");
            StringTools.printLine("i");
            ingredient.showIngredient();
            StringTools.printLine("i");
            if(!Utils.getUserConfirmation(Message.DO_YOU_READY_WANT_TO_DELETE)){
                System.out.println(Message.DELETE_INGREDIENT_FAILED);
                return;
            }
            ingredientList.remove(index);
            System.out.println(Message.DELETE_INGREDIENT_SUCCESSFULLY);
        }
    }

    public void showIngredientList(){
        if(ingredientList.isEmpty()) {
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        this.sortAscending(ingredientList);
        StringTools.printTitle("i");
        StringTools.printLine("i");
        for (Ingredient ingredient : ingredientList) {
            ingredient.showIngredient();
            StringTools.printLine("i");
        }
    }

    private void filterIngredientList(List<Ingredient> availableIngredientList, List<Ingredient> outOfIngredientList){
        if(!availableIngredientList.isEmpty() || !outOfIngredientList.isEmpty()){
            availableIngredientList.clear();
            outOfIngredientList.clear();
        }
        for(Ingredient ingredient: ingredientList){
            if(ingredient.getQuantity() > 0){
                availableIngredientList.add(ingredient);
            }else{
                outOfIngredientList.add(ingredient);
            }
        }
    }

    private void checkingStatus(String status){
        if(status.equals("available")) {
            if (availableIngredientList.isEmpty()) {
                System.out.println(Message.NOTHING_TO_SHOW);
                return;
            }
            StringTools.printTitle("i");
            StringTools.printLine("i");
            for (Ingredient ingredient : availableIngredientList) {
                ingredient.showIngredient();
                StringTools.printLine("i");
            }
        }else if(status.equals("out")){
            if(outOfIngredientList.isEmpty()){
                System.out.println(Message.NOTHING_TO_SHOW);
                return;
            }
            StringTools.printTitle("i");
            StringTools.printLine("i");
            for (Ingredient ingredient : outOfIngredientList) {
                ingredient.showIngredient();
                StringTools.printLine("i");
            }
        }
    }

    //function 5.1: The ingredients are available
    public void showIngredientList(String status){
        if(ingredientList.isEmpty()){
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }

        //filter ingredient list each time before showing
        filterIngredientList(availableIngredientList, outOfIngredientList);
        checkingStatus(status);
    }

    @Override
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
//                String type = stk.nextToken();
                double quantity = Double.parseDouble(stk.nextToken());
                String unit = stk.nextToken();
                double price = Double.parseDouble(stk.nextToken());
                ingredientList.add(new Ingredient(code, name, quantity, unit, price));
            }
            System.out.println("Load data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(String path){
        if(ingredientList.isEmpty()){
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        try{
            File f = new File(path);
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Ingredient ingredient : ingredientList){
                bw.write(ingredient.getCode() + "|" + ingredient.getName() + "|" + ingredient.getQuantity() + "|" + ingredient.getUnit() + "|" + ingredient.getPrice());
                bw.newLine();
            }
            bw.close();
            fw.close();
            System.out.println("Save data successfully at " + path);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDataObject(String path) {
        if(!ingredientList.isEmpty()){
            ingredientList.clear();
        }
        try{
            File f = new File(path);
            if(!f.exists()){
                throw new Exception(Message.FILE_NOT_FOUND);
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Ingredient ingredient;
            try{
                while((ingredient = (Ingredient) fo.readObject()) != null){
                    ingredientList.add(ingredient);
                }
            }catch(EOFException e){
                //do nothing
            }
            fo.close();
            fi.close();
            System.out.println(Message.READ_FILE_SUCCESSFULLY + path);
        }catch(Exception e){
            System.out.println(Message.READ_FILE_FAILED + e.getMessage());
        }
    }

    @Override
    public void saveDataObject(String path) {
        if(ingredientList.isEmpty()){
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        try{
            File f = new File(path);
            FileOutputStream fOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(Ingredient ingredient : ingredientList){
                out.writeObject(ingredient);
            }
            out.close();
            fOut.close();
            System.out.println(Message.SAVE_FILE_SUCCESSFULLY + path);
        }catch(Exception e) {
            System.out.println(Message.SAVE_FILE_FAILED + e.getMessage());
        }
    }

    //return the full quantity of ingredient code
    public double getStorageQuantity(String code){
        return this.searchObjectByCode(code).getQuantity();
    }

    @Override
    public boolean checkToExist(String code) {
        Ingredient ingredient = this.searchObjectByCode(code);
        return ingredient == null? false : true;
    }

    @Override
    public int searchIndexByCode(String code) {
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getCode().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Ingredient searchObjectByCode(String code) {
        int pos = this.searchIndexByCode(code);
        return pos == -1 ? null : ingredientList.get(pos);
    }

    @Override
    public int searchIndexByName(String name) {
        for (int i = 0; i < ingredientList.size(); i++) {
            if (ingredientList.get(i).getName().equalsIgnoreCase(name.trim())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Ingredient searchObjectByName(String name) {
        int pos = this.searchIndexByName(name);
        return pos == -1 ? null : ingredientList.get(pos);
    }

    @Override
    public void sortAscending(List<Ingredient> list) {
        list.sort(new Comparator<Ingredient>() {
            @Override
            public int compare(Ingredient o1, Ingredient o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }


}
