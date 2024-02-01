package models;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<Menu> Menus;
    private Customer customer;
    private LocalDateTime orderTime;

    public Order(List<Menu> Menus, Customer customer) {
        this.Menus = Menus;
        this.customer = customer;
        this.orderTime = LocalDateTime.now(); // Set the order time to the current time
    }

    public List<Menu> getMenuItem() {
        return Menus;
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
        for (Menu Menu : Menus) {
            System.out.println("  Item: " + Menu.getName());
        }
    }
}
