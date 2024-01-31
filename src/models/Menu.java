package models;

import java.util.List;

public class Menu {
    private String code;
    private String name;
    private List<Ingredient> ingredients;
    private List<Integer> quantities;

    public Menu(String code, String name, List<Ingredient> ingredients, List<Integer> quantities) {
        this.code = code;
        this.name = name;
        this.ingredients = ingredients;
        this.quantities = quantities;
    }

    public void updateIngredientQuantities() {
        for (int i = 0; i < ingredients.size(); i++) {
            Ingredient ingredient = ingredients.get(i);
            int newQuantity = ingredient.getQuantity() - quantities.get(i);
            ingredient.setQuantity(newQuantity);
        }
    }

    public String get_code() {
        return code;
    }

    public String get_name() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }
}
