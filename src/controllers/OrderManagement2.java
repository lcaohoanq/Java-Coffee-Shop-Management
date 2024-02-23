package controllers;

import constants.Message;
import constants.Regex;
import models.Ingredient;
import models.Menu;
import models.Order;
import models.Order2;
import utils.Utils;

import java.io.*;
import java.util.*;

public class OrderManagement2 {

    private List<Order2> currentOrderList = new ArrayList<>();
    private List<Order2> tmpList = new ArrayList<>();
    private IngredientManagement im;
    private MenuManagement mm;

    public OrderManagement2(IngredientManagement im, MenuManagement mm){
        this.im = im;
        this.mm = mm;
    }

    public void dispensingDrink(){
        if(!currentOrderList.isEmpty()){
            currentOrderList.clear();
        }
        boolean isExist;
        String code;
        int quantity;
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
                quantity = Utils.getInt("Enter the quantity you want order: ", 1);
                //find the drink above
                Menu drinkItem = mm.searchObjectByCode(code);
                if(handleIngredientQuantity(drinkItem, drinkItem.getRecipe(), quantity)){
                    order.put(code, quantity);
                    currentOrderList.add(new Order2(order));
                    tmpList.addAll(currentOrderList);
                    System.out.println(Message.ORDER_SUCCESSFULLY);
                }else{
                    System.out.println(Message.ORDER_FAILED + " out of ingredient for your order " + drinkItem.getCode());
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_ORDER_MORE_DRINK));
    }

    public void updateDispensingDrink(){
        //
        if(currentOrderList.isEmpty()){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        int quantity;
        boolean isExist;
        System.out.println("--------User ordered--------");
        showCurrentOrder();

        do {
            isExist = true;
            String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS).toUpperCase();
            if (searchIndexByCode(code) == -1) {
                System.out.println(Message.DRINK_CODE_IS_NOT_EXIST_IN_CURRENT_ORDER);
                isExist = false;
            }
            if(isExist){
                quantity = Utils.getInt(Message.INPUT_NEW_QUANTITY_OF_ORDER, 1);
                Menu drinkItem = mm.searchObjectByCode(code);
                if(handleIngredientQuantity(drinkItem, drinkItem.getRecipe(), quantity)){
                    System.out.println(Message.ORDER_SUCCESSFULLY);
                }else{
                    System.out.println(Message.ORDER_FAILED + " out of ingredient for your order " + drinkItem.getCode());
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
        //update thanh cong, thi ta se xoa du lieu trong currentOrderList
        currentOrderList.clear();
    }

    private boolean handleIngredientQuantity(Menu drinkItem, Map<Ingredient, Double> recipe, int quantity){
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

    private void showCurrentOrder(){
        if(currentOrderList.isEmpty()){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        for(Order2 order: currentOrderList){
            order.showInfo();
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
