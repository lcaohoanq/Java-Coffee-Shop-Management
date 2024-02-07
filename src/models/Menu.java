package models;

import java.util.ArrayList;
import utils.Utils;

/*
Menu: là 1 khuôn chuyên đúc ra object quản lý menu
cái khuôn này chứa 1 danh sách các lựa chọn (opt)
method addOption: thêm 1 Option
method print: in ra danh sách các menu
method getChoice: lấy lựa chọn từ người dùng
*/
public class Menu {
    public ArrayList<String> optionList = new ArrayList<>();
    public String title;

    // constructor
    public Menu(String title) {
        this.title = title;
    }

    // method
    public void addOption(String option) {
        optionList.add(option);
    }

    // in ra menu
    public void print() {
        int count = 1;
        System.out.println("----------------------------------------------" + title + "-----------------------------------------------");
        for (String item : optionList) {
            System.out.println(count + ". " + item);
            count++;
        }
    }

    // lấy ra lựa chọn
    public int getChoice() {
        return Utils.getInt("Input your choice: ","Required between 1 and " + optionList.size(),1, optionList.size());
    }
}