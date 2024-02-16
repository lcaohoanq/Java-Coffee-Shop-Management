package models;

import controllers.MenuManagement;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class Order implements Serializable {
    private String code;
    private String name;
    private LocalDateTime time;

    public Order(String code) {
        this.code = code;
        this.time = LocalDateTime.now();
    }

    public Order(String code, String name){
        this.code = code;
        this.name = name;
        this.time = LocalDateTime.now();
    }

    public Order(String code, String name, LocalDateTime time) {
        this.code = code;
        this.name = name;
        this.time = time;
    }

    public String getCode() {
        return code;
    }

    public String getName(){
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return String.format("%5s",code);
    }

    public void showInfo(){
        // Define the format for displaying the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the creation time using the defined formatter
        String timeFormatted = time.format(formatter);
        String str = String.format("| %5s | %-15s | %20s |",code,name,timeFormatted);
        System.out.println(str);
    }
}
