package controllers;

import constants.Regex;
import models.Ingredient;
import models.MenuDrink;
import models.Order;
import utils.ConsoleColors;
import utils.Utils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class OrderManagement {
    private List<Order> orderList = new ArrayList<>();
    private List<Order> orderHistory = new ArrayList<>();
    private MenuManagement menuManagement;
    private IngredientManagement ingredientManagement;
    public OrderManagement(MenuManagement menuManagement, IngredientManagement ingredientManagement){
        this.menuManagement = menuManagement;
        this.ingredientManagement = ingredientManagement;
    }

    //4.1 Dispensing the drink
    //Chuc nang chon mon,
    //show danh sach mon an ra, nguoi dung lua mon,
    //check neu khong co/ (in ra va hoi continue?)
    //neu co thi we can start checking ingredients of the drink and update the
    //ingredient’s status.
    //tuc la sao? tuc la
    //lua mon coffee (check xem nguyen lieu trong kho co du khong? va update the ingredient status???
    //ingredient status la sao ta???
//    public void dispensingDrink(){
//        //in ra cho nguoi dung lua mon
//        menuManagement.showAllDrinks();
//        //tim code do uong
//        do{
//            String code = Utils.getString("Input code dispensing drink");
//            MenuDrink menuDrink = menuManagement.searchObject(code);
//            if(menuDrink == null){
//                System.out.println("the drink does not exist");
//            }else{
//                //chọn món, lập một mảng option, phân cách nhau bằng dấu cách (dấu cách cuối sẽ trim đi)
//                //khi nhập một món order(sẽ check ở đây, check có hay không)
//                String userOrder = Utils.getString("Input your drink want to order", Regex.O_PATTERN,"The drink code is required", "Order information is the drink code and seperate by one space");
//                String[] tmp = userOrder.split("\\s");
//                System.out.println("Your order: " + tmp);
//
//                //neu tim thay thi se add vao mot cai gi do
//
//
//                //add xong se show ra currenList
//                //tao them mot ham display
//
//
//                break;
//            }
//        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE));
//    }

    public void dispensingDrink(){
        if(!orderList.isEmpty()){
            orderList.clear();
        }
//        in ra cho nguoi dung lua mon
        menuManagement.showAllDrinks();

        //chọn món, lập một mảng option, phân cách nhau bằng dấu cách (dấu cách cuối sẽ trim đi)
        //khi nhập một món order(sẽ check ở đây, check có hay không)
        String userOrder = Utils.getString("Input your drink want to order: ", Regex.O_PATTERN,"The drink code is required", "Order information is the drink code and seperate by one space");
        String[] tmp = userOrder.trim().split("\\s"); //prevent the last space
        for(String code: tmp){
            orderList.add(new Order(code));
            orderHistory.add(new Order(code,menuManagement.searchObjectByCode(code).getName()));
        }

        //find the drink (find by code, contain which ingredient) and update the quantity of ingredient in ingredientManagement
        //trường hợp lí tưởng, khi mà người dùng nhập đúng hết tất cả thông tin
        for(Order order: orderList){
            //tìm món uống với code đó
            MenuDrink drinkItem = menuManagement.searchObjectByCode(order.getCode());
            Map<Ingredient, Integer> ingredient = drinkItem.getRecipe();
            for(Map.Entry<Ingredient, Integer> entry: ingredient.entrySet()){
                Ingredient i = entry.getKey();
                //trong kho se bi giam quantity
                int newQuantity = ingredientManagement.getStorageQuantity(i.getCode())-entry.getValue();
                if(newQuantity < 0){
                    System.out.printf(ConsoleColors.RED + "Out of ingredient for drink code: %s, name: %s\n" + ConsoleColors.RESET, drinkItem.getCode(),drinkItem.getName());
                    return;
                }
                i.setQuantity(newQuantity);
            }
            System.out.printf(ConsoleColors.GREEN + "Order successfully, code: %s, name: %s\n" + ConsoleColors.RESET, drinkItem.getCode(),drinkItem.getName());
        }
        showOrderHistory();
    }

    //4.2 Update the dispensing drink
    //tang them so luong cua drink
    // update quantity, start checking ingredients for
    //dispensing the drink and update the ingredient’s status.
    public void updateDispensingDrink(){
        //trong nay moi cap nhat
    }

    private void showOrderList(){
        if(orderList.isEmpty()){
            System.out.println("No one order");
            return;
        }
        for(Order code: orderList){
            System.out.println(code.toString());
        }
    }

    private void showOrderHistory(){
        if(orderHistory.isEmpty()){
            System.out.println("Nothing to show");
            return;
        }
        System.out.printf(ConsoleColors.RED_UNDERLINED + "| %5s | %15s | %20s    |\n"  + ConsoleColors.RESET,"Code", "Name", "Time");
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
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
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

    public static void main(String[] args) {

    }
}

