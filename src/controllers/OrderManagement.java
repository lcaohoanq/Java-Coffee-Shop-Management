package controllers;

import constants.Message;
import constants.Regex;
import models.Ingredient;
import models.Menu;
import models.Order;
import models.Sortable;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class OrderManagement implements Sortable<Order> {
    private List<Order> orderList = new ArrayList<>();
    private List<Order> currentOrderList = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();
    private Map<Ingredient,Double> recipe = new HashMap<>();
    private MenuManagement menuManagement;
    private IngredientManagement ingredientManagement;
    public OrderManagement(MenuManagement menuManagement, IngredientManagement ingredientManagement){
        this.menuManagement = menuManagement;
        this.ingredientManagement = ingredientManagement;
    }

    public void dispensingDrink(){
        if(!orderList.isEmpty()){
            orderList.clear();
        }
//        in ra cho nguoi dung lua mon
        menuManagement.showMenu();

        //chọn món, lập một mảng option, phân cách nhau bằng dấu cách (dấu cách cuối sẽ trim đi)
        //khi nhập một món order(sẽ check ở đây, check có hay không)
        do{
            String userOrder = Utils.getString("Input your drink want to order: ", Regex.O_PATTERN,"The drink code is required", "Order information is the drink code and separate by one space if more than one").toUpperCase();
            String[] userOrderList = userOrder.trim().split("\\s"); //prevent the last space
            for(String code: userOrderList){
                if(menuManagement.searchObjectByCode(code) == null){
                    System.out.println("The drink code is not exist");
                    break;
                }else{
                    orderList.add(new Order(code));
                    currentOrderList.add(new Order(code, menuManagement.searchObjectByCode(code).getName()));
                    orderHistory.add(new Order(code,menuManagement.searchObjectByCode(code).getName()));
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_ORDER_MORE_DRINK));

        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        double total = 0.0;
        double newQuantity;
        boolean isOutOfIngredient = false;
        for(Order order: orderList){
            //tìm món uống với code đó
            Menu menuItem = menuManagement.searchObjectByCode(order.getCode());
            recipe = menuItem.getRecipe();
            for(Map.Entry<Ingredient, Double> entry: recipe.entrySet()){
                Ingredient i = entry.getKey();
                //trong kho se bi giam quantity
                newQuantity = ingredientManagement.getStorageQuantity(i.getCode())-entry.getValue(); //so luong nguyen lieu hien co trong kho tru cho so luong nguyen lieu can co trong mon uong
                if(newQuantity < 0){
                    //neu < 0 thi tuc la nguyen lieu da het, ta set ve 0 luon
//                    i.setQuantity(0);
//                    System.out.printf(ConsoleColors.RED + "Out of ingredient for drink code: %s, name: %s, price: %.0f VND\n" + ConsoleColors.RESET, menuItem.getCode(),menuItem.getName(),menuItem.getPrice());
                    System.out.println("Out of ingredient for the order\n");
                    isOutOfIngredient = true;
                    break;
                }else{
                    i.setQuantity(newQuantity);
                }
            }
            if(!isOutOfIngredient){
                System.out.printf(ConsoleColors.GREEN + "Order successfully, code: %s, name: %s, price: %.0f VND\n" + ConsoleColors.RESET, menuItem.getCode(),menuItem.getName(), menuItem.getPrice());
                total += menuItem.getPrice();
            }
        }
        System.out.printf("Total: %.0f VND\n", total);
    }

    //4.2 Update the dispensing drink
    //tang them so luong cua drink
    // update quantity, start checking ingredients for
    //dispensing the drink and update the ingredient’s status.
    public void updateDispensingDrink(){
        //trong nay moi cap nhat
        if(orderList.isEmpty()){
            System.out.println("No one order");
        }
//        in ra cho nguoi dung lua mon
//        menuManagement.showMenu();
        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        double newQuantity;
        boolean isOutOfIngredient = false;
        for(Order order: orderList){
            //tìm món uống với code đó
            Menu menuItem = menuManagement.searchObjectByCode(order.getCode());
            menuItem.showInfo();
            int newQuantityOrder = Utils.getInt("Input new quantity of order or blank to keep quantity is 1: ", Regex.I_NUMBER, "Quantity of order required a number or blank");
            //if newQuantityOrder = -1 tuc la nguoi dung bam enter, giu nguyen nhu cu
            if(newQuantityOrder != -1){
                recipe = menuItem.getRecipe();
                for(Map.Entry<Ingredient, Double> entry: recipe.entrySet()){
                    Ingredient i = entry.getKey();
                    //trong kho se bi giam quantity
                    newQuantity = ingredientManagement.getStorageQuantity(i.getCode())-(newQuantityOrder*(entry.getValue()));
                    if(newQuantity < 0){
                        //neu < 0 thi tuc la nguyen lieu da het, ta set ve 0 luon
    //                    i.setQuantity(0);
//                        System.out.printf(ConsoleColors.RED + "Out of ingredient for drink code: %s, name: %s\n" + ConsoleColors.RESET, menuItem.getCode(),menuItem.getName());
                        System.out.println("Out of ingredient for the quantity order");
                        isOutOfIngredient = true;
                        break;
                    }else{
                        i.setQuantity(newQuantity);
                    }
                }
            }
            if (!isOutOfIngredient){
                System.out.printf(ConsoleColors.GREEN + "Update order's quantity successfully, code: %s, name: %s\n\n" + ConsoleColors.RESET, menuItem.getCode(),menuItem.getName());
            }
        }
        //order thanh cong, thi ta se xoa du lieu trong orderList
        if(!isOutOfIngredient){
            orderList.clear();
        }
    }

    public void showOrderList(){
        if(currentOrderList.isEmpty()){
            System.out.println("No one order");
            return;
        }
        this.sortAscending(currentOrderList);
        System.out.printf(ConsoleColors.GREEN_UNDERLINED + "| %5s | %15s | %20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
        for(Order order: currentOrderList){
            order.showInfo();
        }
    }

    private void showOrderHistory(){
        if(orderHistory.isEmpty()){
            System.out.println("Nothing to show");
            return;
        }
        System.out.printf(ConsoleColors.RED_UNDERLINED + "| %5s | %15s | %20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
        for(Order order: orderHistory){
            order.showInfo();
        }
    }

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
    public void sortAscending(List<Order> list) {
        list.sort(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                return o1.getName().compareTo(o2.getName()); //sort ascending order by name
            }
        });
    }

}

