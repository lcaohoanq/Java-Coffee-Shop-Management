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

public class OrderManagement implements Sortable<Order>, Searchable<Order> {
//    private List<Order> orderList = new ArrayList<>();
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
        if(!currentOrderList.isEmpty()){
//            orderList.clear();
            currentOrderList.clear();
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
                    //cac thao tac order se thuc hien tren orderList, ta chi can co code la duoc
//                    orderList.add(new Order(code));
                    //current order list tuong tu order list nhung ta dung constructor co 2 tham so de lay ten cua drink
                    //de in ra cho nguoi dung trong
                    currentOrderList.add(new Order(code, menuManagement.searchObjectByCode(code).getName()));
                    //orderHistory de luu lai toan bo lich su order de ghi ra file
                    orderHistory.add(new Order(code,menuManagement.searchObjectByCode(code).getName()));
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_ORDER_MORE_DRINK));

        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        double total = 0.0;
        double newQuantity;
        boolean isOutOfIngredient = false;
        for(Order order: currentOrderList){
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
        if(currentOrderList.isEmpty()){
            System.out.println("No one order");
            return;
        }
//        in ra cho nguoi dung lua mon
//        menuManagement.showMenu();
        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        double newQuantity;
        boolean isExist;
        boolean isOutOfIngredient = false;
        System.out.println("--------User ordered--------");
        this.showCurrentOrder();

        do {
            isExist = true;
            //find the drink with input code
            String code = Utils.getString("Input the drink code you want to update: ", Regex.D_CODE, "The drink code is required", Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS).toUpperCase();
            if (searchIndexByCode(code) == -1) {
                System.out.println("Not exist drink code in the current order");
                isExist = false;
            }
            if(isExist) {
                Menu menuItem = menuManagement.searchObjectByCode(code);
                menuItem.showSortInfo();
                int newQuantityOrder = Utils.getInt("Input new quantity of order or blank to keep quantity is 1: ", Regex.I_NUMBER, "Quantity of order required a positive number or blank");
                //if newQuantityOrder = -1 tuc la nguoi dung bam enter, giu nguyen nhu cu
                if (newQuantityOrder != -1) {
                    recipe = menuItem.getRecipe();
                    for (Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
                        Ingredient i = entry.getKey();
                        //trong kho se bi giam quantity
                        newQuantity = ingredientManagement.getStorageQuantity(i.getCode()) - (newQuantityOrder * (entry.getValue()));
                        if (newQuantity < 0) {
                            //neu < 0 thi tuc la nguyen lieu da het, ta set ve 0 luon
                            i.setQuantity(0);
                            System.out.printf(ConsoleColors.RED + "Out of ingredient for drink code: %s, name: %s, price: %.0f VND\n" + ConsoleColors.RESET, menuItem.getCode(), menuItem.getName(), menuItem.getPrice());
                            isOutOfIngredient = true;
                            break;
                        } else {
                            i.setQuantity(newQuantity);
                        }
                    }
                }
                if(newQuantityOrder == -1){
                    System.out.printf("Update quantity successfully for drink code: %s, name: %s, quantity: %d\n", menuItem.getCode(), menuItem.getName(), 1);
                }else{
                    System.out.printf("Update successfully for drink code: %s, name: %s, quantity: %d\n", menuItem.getCode(), menuItem.getName(), newQuantityOrder);
                }
            }
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
        //order thanh cong, thi ta se xoa du lieu trong currentOrderList
        currentOrderList.clear();
    }

    public void showCurrentOrder(){
        if(currentOrderList.isEmpty()){
            System.out.println("No one order now");
            return;
        }
        this.sortAscending(currentOrderList);
        System.out.printf(ConsoleColors.GREEN_UNDERLINED + "| %5s | %15s | %20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
        for(Order order: currentOrderList){
            order.showInfo();
        }
    }

    public void showAllOrder(){
        if(orderHistory.isEmpty()){
            System.out.println("Nothing to show");
            return;
        }
        System.out.printf(ConsoleColors.RED_UNDERLINED + "| %5s | %-15s | %20s |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
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

