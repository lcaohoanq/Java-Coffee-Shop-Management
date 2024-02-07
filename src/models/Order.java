package models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<MenuDrink> menuDrinks;
    private Customer customer;
    private LocalDateTime orderTime;

    public Order(List<MenuDrink> menuDrinks, Customer customer) {
        this.menuDrinks = menuDrinks;
        this.customer = customer;
        this.orderTime = LocalDateTime.now(); // Set the order time to the current time
    }

    public List<MenuDrink> getMenuItem() {
        return menuDrinks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void displayOrderDetails() {
        System.out.println("Order for Customer ID " + customer.getCusId() +
                ", Name: " + customer.getName() +
                ", Order Time: " + orderTime);
        for (MenuDrink MenuDrink : menuDrinks) {
            System.out.println("  Item: " + MenuDrink.getName());
        }
    }
}
