//    package views;
//
//    import controllers.OrderManagement;
//    import models.Customer;
//    import models.Ingredient;
//    import models.Menu;
//    import models.Order;
//
//    import java.util.Arrays;
//    import java.util.List;
//
//    public class CoffeeShopDemo {
//        public static void main(String[] args) {
//            // Create Ingredients
//            Ingredient coffee = new Ingredient("I00", "Coffee", "coffee", 250, "ml", 2.5);
//            Ingredient milk = new Ingredient("I01", "Milk", "milk", 200, "ml", 1.5);
//            Ingredient sugar = new Ingredient("I02", "Sugar", "sugar", 10, "g", 0.5);
//
//            Ingredient orange = new Ingredient("I03", "Orange", "fruit", 100, "g", 1.5);
//            Ingredient ice = new Ingredient("I04", "Ice", "ice", 100, "g", 0.5);
//
//            // Create a Menu Item
//            List<Ingredient> coffeeIngredients = Arrays.asList(coffee, milk, sugar);
//            List<Integer> coffeeQuantities = Arrays.asList(1, 1, 2);
//            Menu coffeeMenu = new Menu("M00", "Coffee", coffeeIngredients, coffeeQuantities);
//
//            List<Ingredient> orangeJuiceIngredients = Arrays.asList(orange, ice);
//            List<Integer> orangeJuiceQuantities = Arrays.asList(2, 1);
//            Menu orangeJuiceMenu = new Menu("M01", "Orange Juice", orangeJuiceIngredients, orangeJuiceQuantities);
//
//            // Create Customers
//            Customer customer1 = new Customer("C01", "Hao", 1990, "Male");
//            Customer customer2 = new Customer("C02", "Hoang", 2004, "Male");
//            Customer customer3 = new Customer("C03", "Duong", 2004, "Male");
//            Customer customer4 = new Customer(); //create a customer with cusId = C04
//
//            // Create Orders for each customer with multiple menu items
//            List<Menu> customer1MenuItems = Arrays.asList(coffeeMenu, orangeJuiceMenu); // Customer 1 orders both Coffee and Orange Juice
//            Order order1 = new Order(customer1MenuItems, customer1);
//
//            List<Menu> customer2MenuItems = Arrays.asList(coffeeMenu); // Customer 2 orders only Coffee
//            Order order2 = new Order(customer2MenuItems, customer2);
//
//            List<Menu> customer3MenuItems = Arrays.asList(coffeeMenu); // Customer 3 orders only Coffee
//            Order order3 = new Order(customer3MenuItems, customer3);
//
//            List<Menu> customer4MenuItems = Arrays.asList(orangeJuiceMenu, coffeeMenu); // Customer 4 orders only Orange Juice
//            Order order4 = new Order(customer4MenuItems, customer4);
//
//            OrderManagement orderManagement = new OrderManagement();
//            orderManagement.store(order1);
//            orderManagement.store(order2);
//            orderManagement.store(order3);
//            orderManagement.store(order4);
//
//            // Display initial order details for each customer
//            System.out.println("Initial Order Details for Customer 1:");
//            displayOrderList(order1);
//
//            System.out.println("\nInitial Order Details for Customer 2:");
//            displayOrderList(order2);
//
//            System.out.println("\nInitial Order Details for Customer 3:");
//            displayOrderList(order3);
//
//            System.out.println("\nInitial Order Details for Customer 4:");
//            displayOrderList(order4);
//
//            // Update ingredient quantities based on each order
//            orderManagement.updateIngredients();
//
//            // Display updated ingredient quantities
//            System.out.println("\nUpdated Ingredient Quantities:");
//            displayIngredientQuantities(order1.getMenuItem().get(0).getIngredients());
//            displayIngredientQuantities(order4.getMenuItem().get(0).getIngredients());
//
//            // Additional customer creation
//            createCustomer();
//        }
//
//        private static void displayOrderList(Order order) {
//            // Display initial order details
//            System.out.println("Order for Customer ID " + order.getCustomer().getCusId() + ", Name: " + order.getCustomer().getName() +
//                    ", Order Time: " + order.getOrderTime());
//            for (Menu menuItem : order.getMenuItem()) {
//                System.out.println("  Item: " + menuItem.getName());
//            }
//        }
//
//        private static void displayIngredientQuantities(List<Ingredient> ingredients) {
//            for (Ingredient ingredient : ingredients) {
//                String message = ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnit();
//                if (ingredient.getQuantity() < 0) {
//                    message += " [OUT OF RANGE]";
//                }
//                System.out.println(message);
//            }
//        }
//
//        public static void createCustomer() {
//            Customer cus1 = new Customer(); // create a customer with cusId = C04
//            Customer cus2 = new Customer(); // create a customer with cusId = C05
//            Customer cus3 = new Customer(); // create a customer with cusId = C06
//
//            // Additional customer creation logic if needed
//        }
//    }
//
