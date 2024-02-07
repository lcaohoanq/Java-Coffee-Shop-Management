//package controllers;
//
//import models.Ingredient;
//import models.Menu;
//import models.Order;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class OrderManagement {
//    private List<Order> orderList;
//
//    public OrderManagement() {
//        this.orderList = new ArrayList<>();
//    }
//
//    public void store(Order order) {
//        // Store the order in the list
//        orderList.add(order);
//
//        // Update ingredients based on the number of orders
//        updateIngredients();
//    }
//
//    public void updateIngredients() {
//        // Assume there is a specific logic to update ingredients based on the number of orders
//        // You can customize this logic according to your business rules
//        int numberOfOrders = orderList.size();
//
//        System.out.println("Updating ingredients based on the number of orders: " + numberOfOrders);
//
//        for (Order order : orderList) {
//            // Update ingredients for each order
//            order.getMenuItem().forEach(Menu::updateIngredientQuantities);
//        }
//
//        // Display updated ingredient quantities after processing all orders
//        System.out.println("\nUpdated Ingredient Quantities:");
//        displayIngredientQuantities(orderList.get(0).getMenuItem().get(0).getIngredients());
//    }
//
//    private void displayIngredientQuantities(List<Ingredient> ingredients) {
//        for (Ingredient ingredient : ingredients) {
//            System.out.println(ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit());
//        }
//    }
//}
//
