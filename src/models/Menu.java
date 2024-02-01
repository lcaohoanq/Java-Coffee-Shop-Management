package models;

import java.util.List;

public class Menu {
    private String code;
    private String name;
    private List<Ingredient> ingredients;
    private List<Integer> quantities;
    private static final int MIN_INGREDIENT_QUANTITY = 0;
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
            // Ensure the new quantity does not go below the minimum allowed
            ingredient.setQuantity(Math.max(newQuantity, MIN_INGREDIENT_QUANTITY));
        }
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Integer> getQuantities() {
        return quantities;
    }
}
