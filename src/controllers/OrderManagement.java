package controllers;

import constants.Message;
import constants.Regex;
import models.Ingredient;
import models.Menu;
import models.Order;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.util.*;

public class OrderManagement {

    private List<Order> currentOrderList = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();
    private IngredientManagement im;
    private MenuManagement mm;
    private int orderQuantity = 0;
    public OrderManagement(IngredientManagement im, MenuManagement mm){
        this.im = im;
        this.mm = mm;
    }

    public void dispensingDrink(){
        if(!currentOrderList.isEmpty()){
            currentOrderList.clear();
        }
        boolean isExist;
        String code;
        Map<String, Integer> order;
        do{
            isExist = true;
            code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS).toUpperCase();
            if(!mm.checkToExist(code)){
                System.out.println(Message.DRINK_IS_NOT_EXIST);
                isExist = false;
            }
            if(isExist){
                order = new HashMap<>();
                orderQuantity = Utils.getInt("Enter the quantity you want order: ", 1);
                //find the drink above
                Menu drinkItem = mm.searchObjectByCode(code);
                if(handleIngredientQuantity(drinkItem.getRecipe(), orderQuantity)){
                    order.put(code, orderQuantity);
                    currentOrderList.add(new Order(order));
                    orderHistory.add(new Order(order));
                    System.out.println(Message.ORDER_SUCCESSFULLY);
                }else{
                    System.out.println(Message.ORDER_FAILED_OUT_OF_INGREDIENT + drinkItem.getCode());
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_ORDER_MORE_DRINK));
    }

    public void updateDispensingDrink(){
        if(currentOrderList.isEmpty()){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        int quantity;
        boolean isExist;
        System.out.println("----------------------------User ordered----------------------------");
        showCurrentOrder();

        do {
            isExist = true;
            String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS).toUpperCase();
            if (searchIndexByCode(code) == -1) {
                System.out.println(Message.DRINK_CODE_IS_NOT_EXIST_IN_CURRENT_ORDER);
                isExist = false;
            }
            if(isExist){
                Menu drinkItem = mm.searchObjectByCode(code);
                quantity = Utils.getInt(Message.INPUT_NEW_QUANTITY_OF_ORDER, 1);
                //compare the currentQuantity with the newQuantity
                int difference = compareQuantity(this.orderQuantity, quantity);
                if(handleIngredientQuantity(drinkItem.getRecipe(), difference)){
                    System.out.println(Message.ORDER_SUCCESSFULLY);
                }else{
                    System.out.println(Message.ORDER_FAILED_OUT_OF_INGREDIENT + drinkItem.getCode());
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
        //update thanh cong, thi ta se xoa du lieu trong currentOrderList
        currentOrderList.clear();
    }

    private boolean handleIngredientQuantity(Map<Ingredient, Double> recipe, int quantity){
        double newQuantity = 0;
        for(Map.Entry<Ingredient, Double> entry: recipe.entrySet()){
            Ingredient ingredient = entry.getKey();
            double requiredQuantity = entry.getValue();
            newQuantity = im.getStorageQuantity(ingredient.getCode()) - requiredQuantity * quantity;
            if(newQuantity < 0){
                return false;
            }
            else{
                IngredientManagement.ingredientList.get(im.searchIndexByCode(ingredient.getCode())).setQuantity(newQuantity);
            }
        }
        return true;
    }

    private int compareQuantity(int currentQuantity, int newQuantity){
        return newQuantity - currentQuantity;
    }

    private boolean handleIsEmpty(List<Order> list){
        return list.isEmpty();
    }
    private void handlePrintList(List<Order> list){
        System.out.printf(ConsoleColors.GREEN_UNDERLINED + "| %5s | %19s | %20s |\n"  + ConsoleColors.RESET,"Code", "Quantity", "Time");
        for(Order order: list){
            order.showInfo();
        }
    }

    public void showCurrentOrder(){
        if(handleIsEmpty(currentOrderList)){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        handlePrintList(currentOrderList);
    }

    private void showOrderHistory(){
        if(handleIsEmpty(orderHistory)){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        handlePrintList(orderHistory);
    }

    public void loadDataObject(String url){
        if(!orderHistory.isEmpty()){
            orderHistory.clear();
        }
        try{
            File f = new File(url);
            if(!f.exists()){
                throw new IOException(Message.FILE_NOT_FOUND + url);
            }
            FileInputStream fi = new FileInputStream(f);
            ObjectInputStream fo = new ObjectInputStream(fi);
            Order order;
            try{
                while((order = (Order) fo.readObject()) != null){
                    orderHistory.add(order);
                }
            }catch(EOFException e){
                //do nothing
            }
            fo.close();
            fi.close();
            System.out.println(Message.READ_FILE_SUCCESSFULLY + url);
        }catch(Exception e){
            System.out.println(Message.READ_FILE_FAILED + e.getMessage());
        }
    }

    public void saveDataObject(String url){
        if(orderHistory.isEmpty()){
            System.out.println(Message.INGREDIENT_LIST_IS_EMPTY);
            return;
        }
        try{
            File f = new File(url);
            FileOutputStream fOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(Order order : orderHistory){
                out.writeObject(order);
            }
            out.close();
            fOut.close();
            System.out.println(Message.SAVE_FILE_SUCCESSFULLY + url);
        }catch(Exception e) {
            System.out.println(Message.SAVE_FILE_FAILED + e.getMessage());
        }
    }

    public int searchIndexByCode(String code) {
        for(int i = 0; i < currentOrderList.size(); i++){
            if(currentOrderList.get(i).getOrder().containsKey(code)){
                return i;
            }
        }
        return -1;
    }


}
