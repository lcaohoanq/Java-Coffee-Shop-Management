package models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order2 implements Serializable {
    private Map<String, Integer> order; //String prevent for menu code, Integer prevent for quantity of drink
    private LocalDateTime time;

    public Order2(Map<String, Integer> order) {
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
        for(Map.Entry<String, Integer> entry: order.entrySet()){
            System.out.println("Drink code: " + entry.getKey() + " - Quantity: " + entry.getValue());
        }
    }
}
