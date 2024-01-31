package models;

public class Order {
    private Menu menuItem;
    private Customer customer;

    public Order(Menu menuItem, Customer customer) {
        this.menuItem = menuItem;
        this.customer = customer;
    }

    public void displayOrderDetails() {
        System.out.println("Order for Customer ID " + customer.getCusId() + ", Name: " + customer.getName() +
                ", Item: " + menuItem.get_name());
    }
}
