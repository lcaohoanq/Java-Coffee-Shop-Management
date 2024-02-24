package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order implements Serializable {
    private Map<String, Integer> order; //String prevent for menu code, Integer prevent for quantity of drink
    private LocalDateTime time;

    public Order(Map<String, Integer> order) {
        this.order = order;
        this.time = LocalDateTime.now();
    }

    public Map<String, Integer> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Integer> order) {
        this.order = order;
    }

    public void showInfo(){
        // Define the format for displaying the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the creation time using the defined formatter
        String timeFormatted = time.format(formatter);
        for(Map.Entry<String, Integer> entry: this.order.entrySet()){
            String code = entry.getKey();
            int quantity = entry.getValue();
            String str = String.format("| %5s | %19s | %20s |",code,quantity,timeFormatted);
            System.out.println(str);
        }
    }
}
