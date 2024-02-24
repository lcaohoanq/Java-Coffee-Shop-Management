package models;

import utils.ConsoleColors;

import java.io.Serializable;
import java.util.Map;

public class Menu implements Serializable {
    private String code;
    private String name;
    private Map<Ingredient,Double> recipe;

    public Menu(String code, String name, Map<Ingredient, Double> recipe) {
        this.code = code.toUpperCase();
        this.name = name.toLowerCase();
        this.recipe = recipe;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Double> getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showSortInfo(){
        double total = 0;
        System.out.printf(ConsoleColors.GREEN + "Drink code: %-5s\nDrink name: %-20s\n",code,name + ConsoleColors.RESET);
        Map<Ingredient, Double> recipe = this.getRecipe();
        for(Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
            double price = entry.getKey().getPrice();
            double quantity = entry.getValue();
            double amount = quantity * price;
            total += amount;
        }
        System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET + "%10.0f VND\n", total);
    }

    public void showAllInfo(){
        double total = 0;
        System.out.printf(ConsoleColors.GREEN + "Drink code: %-5s\nDrink name: %-20s\n",code,name + ConsoleColors.RESET);
        Map<Ingredient, Double> recipe = this.getRecipe();
        System.out.printf("| %-5s | %-15s | %10s | %10s |  %19s |\n", "Code", "Name", "Quantity", "Price", "Amount");
        for(Map.Entry<Ingredient, Double> entry : recipe.entrySet()) {
            String code = entry.getKey().getCode();
            String name = entry.getKey().getName();
            double price = entry.getKey().getPrice();
            double quantity = entry.getValue();
            double amount = quantity * price;
            total += amount;
            System.out.printf("| %5s | %-15s | %10.1f| %10.0f |  %15.0f VND |\n", code, name, quantity, price, amount);
        }
        System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET + "%10.0f VND\n", total);
    }
}
