package models;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MenuDrink implements Serializable {
    private String code;
    private String name;
    private Map<Ingredient,Integer> recipe;

    public MenuDrink(String code, String name, Map<Ingredient, Integer> recipe) {
        this.code = code.toUpperCase();
        this.name = name;
        this.recipe = recipe;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showInfo(){
        String str = String.format("| %5s | %20s | %20s |", code, name, recipe);
        System.out.println(str);
    }
}
