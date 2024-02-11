package models;

import utils.ConsoleColors;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MenuDrink implements Serializable {
    private String code;
    private String name;
    private Map<Ingredient,Integer> recipe;

    public MenuDrink(String code, String name, Map<Ingredient, Integer> recipe) {
        this.code = code.toUpperCase();
        this.name = name;
        this.recipe = recipe;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showInfo(){
        double sum = 0;
        System.out.printf(ConsoleColors.GREEN + "Drink code: %-5s\nDrink name: %-20s\n",code,name + ConsoleColors.RESET);
        Map<Ingredient, Integer> recipe = this.getRecipe();
        System.out.printf("| %5s | %-15s | %10s | %10s | %15s   |\n", "Code", "Ingredient", "Quantity", "Price", "Amount");
        for(Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
            String code = entry.getKey().getCode();
            String name = entry.getKey().getName();
            double price = entry.getKey().getPrice();
            int quantity = entry.getValue();
            double amount = quantity * price;
            sum += amount;
            System.out.printf("| %5s | %-15s | %10d | %10.1f |  %15.1f$ |\n", code, name, quantity, price, amount);
        }
        System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET + "%10.1f$\n", sum);
    }
}
