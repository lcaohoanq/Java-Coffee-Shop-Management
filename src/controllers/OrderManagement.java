package controllers;

import constants.Message;
import constants.Regex;
import models.*;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManagement implements Sortable<Order>, Searchable<Order>, FileService{
    private List<Order> currentOrderList = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();
    private MenuManagement menuManagement;
    private IngredientManagement ingredientManagement;

    public OrderManagement(MenuManagement menuManagement, IngredientManagement ingredientManagement){
        this.menuManagement = menuManagement;
        this.ingredientManagement = ingredientManagement;
    }

    public void dispensingDrink(){
        if(!currentOrderList.isEmpty()){
            currentOrderList.clear();
        }

        //in ra cho nguoi dung lua mon
        menuManagement.showMenu();

        boolean isOutOfIngredient = false;
        double total = 0.0;

        orderDrink();

        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        for(Order order: currentOrderList){
            //tìm món uống với code đó
            Menu menuItem = menuManagement.searchObjectByCode(order.getCode());
            Map<Ingredient,Double> recipe = menuItem.getRecipe();
            double newQuantity = 0;

            for(Map.Entry<Ingredient, Double> entry: recipe.entrySet()){
                Ingredient i = entry.getKey();
                //trong kho se bi giam quantity
                double requiredQuantity = entry.getValue();
                newQuantity = ingredientManagement.getStorageQuantity(i.getCode())- requiredQuantity; //so luong nguyen lieu hien co trong kho tru cho so luong nguyen lieu can co trong mon uong

                if(newQuantity < 0){
                    isOutOfIngredient = true;
                    System.out.printf(Message.ORDER_FAILED + " out of ingredient for the drink code: %5s\n", menuItem.getCode());
                    break;
                }else{
                    i.setQuantity(newQuantity);
                }
            }
            if(!isOutOfIngredient){
                System.out.printf(Message.ORDER_SUCCESSFULLY + " for code: %5s, name: %-15s, price: %15.0f VND\n", menuItem.getCode(),menuItem.getName(), menuItem.getPrice());
                total += menuItem.getPrice();
            }
        }
        System.out.printf(Message.TOTAL + "%.0f VND\n", total);
    }

    //4.2 Update the dispensing drink
    //tang them so luong cua drink
    // update quantity, start checking ingredients for
    //dispensing the drink and update the ingredient’s status.
    public void updateDispensingDrink(){
        //trong nay moi cap nhat
        if(currentOrderList.isEmpty()){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        //in ra cho nguoi dung lua mon
        System.out.println("--------User ordered--------");
        this.showCurrentOrder();

        boolean isExist;

        do {
            isExist = true;
            //find the drink with input code
            String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, "The drink code is required", Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS).toUpperCase();

            if (searchIndexByCode(code) == -1) {
                System.out.println(Message.DRINK_CODE_DOES_NOT_EXIST_IN_CURRENT_ORDER);
                isExist = false;
            }

            if(isExist) {
                Menu menuItem = menuManagement.searchObjectByCode(code);
                menuItem.showSortInfo();
                double newQuantity = 0.0;
                int newQuantityOrder = Utils.getInt("Input new quantity of order or blank to keep quantity is 1: ", Regex.QUANTITY, Message.QUANTITY_REQUIRED_A_POSITIVE_INTEGER_OR_DOUBLE);

                //if newQuantityOrder = -1 tuc la nguoi dung bam enter, giu nguyen nhu cu
                if (newQuantityOrder != -1) {
                    Map<Ingredient,Double> recipe = menuItem.getRecipe();
                    for (Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
                        Ingredient i = entry.getKey();
                        double requiredQuantity = entry.getValue();
                        newQuantity = ingredientManagement.getStorageQuantity(i.getCode()) - (newQuantityOrder * requiredQuantity);
                        if (newQuantity < 0) {
                            System.out.print(Message.OUT_OF_INGREDIENT + " for your quantity order\n");
                            break;
                        } else {
                            i.setQuantity(newQuantity);
                        }
                    }
                }
                if(newQuantityOrder == -1){
                    System.out.printf("Update quantity successfully for drink code: %s, name: %s, quantity: %d\n", menuItem.getCode(), menuItem.getName(), 1);
                }else if(newQuantity >= 0){
                    System.out.printf("Update successfully for drink code: %s, name: %s, quantity: %d\n", menuItem.getCode(), menuItem.getName(), newQuantityOrder);
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
        //update thanh cong, thi ta se xoa du lieu trong currentOrderList
        currentOrderList.clear();
    }

    private void orderDrink(){
        //chọn món, lập một mảng option, phân cách nhau bằng dấu cách (dấu cách cuối sẽ trim đi)
        //khi nhập một món order(sẽ check ở đây, check có hay không)
        do{
            String userOrder = Utils.getString(Message.INPUT_DRINK_CODE, Regex.O_PATTERN,Message.DRINK_CODE_IS_REQUIRED, Message.ORDER_PATTERN).toUpperCase();
            String[] userOrderList = userOrder.trim().split("\\s"); //prevent the last space
            for(String code: userOrderList){
                if(menuManagement.searchObjectByCode(code) == null){
                    System.out.println(Message.DRINK_DOES_NOT_EXIST);
                    break;
                }else{
                    currentOrderList.add(new Order(code, menuManagement.searchObjectByCode(code).getName()));
                    //orderHistory de luu lai toan bo lich su order de ghi ra file
                    orderHistory.add(new Order(code,menuManagement.searchObjectByCode(code).getName()));
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_ORDER_MORE_DRINK));
    }

    public void showCurrentOrder(){
        if(currentOrderList.isEmpty()){
            System.out.println(Message.NO_ONE_ORDERED);
            return;
        }
        this.sortAscending(currentOrderList);
        System.out.printf(ConsoleColors.GREEN_UNDERLINED + "| %5s | %-15s | %-20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
        for(Order order: currentOrderList){
            order.showInfo();
        }
    }

    public void showAllOrder(){
        if(orderHistory.isEmpty()){
            System.out.println("Nothing to show");
            return;
        }
        System.out.printf(ConsoleColors.RED_UNDERLINED + "| %5s | %-15s | %-20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
        for(Order order: orderHistory){
            order.showInfo();
        }
    }

    @Override
    public void loadData(String path){
        try{
            File f = new File(path);
            if(!f.exists()){
                return;
            }
            FileReader fileReader = new FileReader(f);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferedReader.readLine()) != null){
                StringTokenizer stk = new StringTokenizer(line, "|");
                String code = stk.nextToken().trim();
                String name = stk.nextToken().trim();
                String time = stk.nextToken().trim();
                // Parse the timeString into a LocalDateTime object
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
                LocalDateTime timeFormatted = LocalDateTime.parse(time, formatter);
                orderHistory.add(new Order(code, name, timeFormatted));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void saveData(String path){
        try {
            File f = new File(path);
            FileWriter fileWriter = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for(Order order: orderHistory){
                bw.write(order.getCode() + "|" + order.getName() + "|" + order.getTime());
                bw.newLine();
            }
            bw.close();
            System.out.println("Save data successfully at " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDataObject(String path) {
        if(!orderHistory.isEmpty()){
            orderHistory.clear();
        }
        try{
            File f = new File(path);
            if(!f.exists()){
                return;
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
            System.out.println(Message.READ_FILE_SUCCESSFULLY + path);
        }catch (Exception e){
            System.out.println(Message.READ_FILE_FAILED + e.getMessage());
        }
    }

    @Override
    public void saveDataObject(String path) {
        if(orderHistory.isEmpty()){
            System.out.println(Message.ORDER_LIST_IS_EMPTY);
            return;
        }
        try{
            File f = new File(path);
            FileOutputStream fOut = new FileOutputStream(f);
            ObjectOutputStream out = new ObjectOutputStream(fOut);
            for(Order order: orderHistory){
                out.writeObject(order);
            }
            out.close();
            fOut.close();
            System.out.println(Message.SAVE_FILE_SUCCESSFULLY + path);
        }catch (Exception e){
            System.out.println(Message.SAVE_FILE_FAILED + e.getMessage());
        }
    }

    @Override
    public void sortAscending(List<Order> list) {
        list.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getName().compareTo(o2.getName()); //sort ascending order by name
            }
        });
    }

    @Override
    public boolean checkToExist(String code) {
        return false;
    }

    @Override
    public int searchIndexByCode(String code) {
        for(int i = 0; i < currentOrderList.size(); i++){
            if(currentOrderList.get(i).getCode().equalsIgnoreCase(code)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Order searchObjectByCode(String code) {
        return null;
    }

    @Override
    public int searchIndexByName(String name) {
        return 0;
    }

    @Override
    public Order searchObjectByName(String name) {
        return null;
    }
}

