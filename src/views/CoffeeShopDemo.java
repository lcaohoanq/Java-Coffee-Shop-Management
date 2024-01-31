package views;

import models.Customer;
import models.Ingredient;
import models.Menu;
import models.Order;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class CoffeeShopDemo {
    public static void main(String[] args) {
        // Create Ingredients
        Ingredient coffee = new Ingredient("I00", "Coffee", "coffee", 250, "ml", 2.5);
        Ingredient milk = new Ingredient("I01", "Milk", "milk", 200, "ml", 1.5);
        Ingredient sugar = new Ingredient("I02", "Sugar", "sugar", 10, "g", 0.5);

        // Create a Menu Item
        List<Ingredient> coffeeIngredients = Arrays.asList(coffee, milk, sugar);
        List<Integer> coffeeQuantities = Arrays.asList(1, 1, 2);
        Menu coffeeMenu = new Menu("M00", "Coffee", coffeeIngredients, coffeeQuantities);

        // Create a Customer
        Customer customer1 = new Customer("C01", "Hao", 1990, "Male");
        Customer customer2 = new Customer("C02", "Hoang", 2004, "Male");
        Customer customer3 = new Customer("C03", "Duong", 2004, "Male");

        // Create an Order
        Order order1 = new Order(coffeeMenu, customer1);
        Order order2 = new Order(coffeeMenu, customer2);
        Order order3 = new Order(coffeeMenu, customer3);

        displayOrderList(order1);
        displayOrderList(order2);
        displayOrderList(order3);

        // Update ingredient quantities based on the order
        coffeeMenu.updateIngredientQuantities();

        // Display updated ingredient quantities
        System.out.println("\nUpdated Ingredient Quantities:");
        displayIngredientQuantities(coffeeMenu.getIngredients());
    }
    private static void displayOrderList(Order order) {
        // Display initial order details
        System.out.println("Initial Order Details:");
        order.displayOrderDetails();
    }
    private static void displayIngredientQuantities(List<Ingredient> ingredients) {
        for (Ingredient ingredient : ingredients) {
            System.out.println(ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit());
        }
    }
}
