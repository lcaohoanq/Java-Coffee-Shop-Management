package models;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Menu implements Serializable {
    private String code;
    private String name;
    private Map<String,Integer> recipe;

//    private List<Ingredient> ingredients;
//    private List<Integer> quantities;
//    private static final int MIN_INGREDIENT_QUANTITY = 0;
//    public Menu(String code, String name, List<Ingredient> ingredients, List<Integer> quantities) {
//        this.code = code;
//        this.name = name;
//        this.ingredients = ingredients;
//        this.quantities = quantities;
//    }
//
//    public void updateIngredientQuantities() {
//        for (int i = 0; i < ingredients.size(); i++) {
//            Ingredient ingredient = ingredients.get(i);
//            int newQuantity = ingredient.getQuantity() - quantities.get(i);
//            // Ensure the new quantity does not go below the minimum allowed
//            ingredient.setQuantity(Math.max(newQuantity, MIN_INGREDIENT_QUANTITY));
//        }
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<Ingredient> getIngredients() {
//        return ingredients;
//    }
//
//    public List<Integer> getQuantities() {
//        return quantities;
//    }

    public Menu(String code, String name, Map<String, Integer> recipe) {
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

    public Map<String, Integer> getRecipe() {
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
