package models;

import java.util.ArrayList;
import utils.ConsoleColors;
import utils.Utils;

public class MenuBuilder {
    public ArrayList<String> optionList = new ArrayList<>();
    public String title;

    public MenuBuilder(String title) {
        this.title = title;
    }

    // method
    public void addOption(String option) {
        optionList.add(option);
    }

    public void print() {
        int count = 1;
        System.out.println("----------------------------------------------" + title + "-----------------------------------------------");
        for (String item : optionList) {
            System.out.println(count + ". " + item);
            count++;
        }
    }

    public int getChoice() {
        return Utils.getInt("Input your choice: ", ConsoleColors.RED + "Required between 1 and " + optionList.size() + ConsoleColors.RESET,1, optionList.size());
    }
}