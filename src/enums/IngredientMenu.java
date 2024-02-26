package enums;

public enum IngredientMenu {
    // 1. Ingredient menu
    ADD_INGREDIENT(1, "Add ingredient"),
    UPDATE_INGREDIENT(2, "Update ingredient information"),
    DELETE_INGREDIENT(3, "Delete ingredient"),
    SHOW_INGREDIENTS(4, "Show all ingredients"),
    EXIT_TO_MAIN_MENU(5, "Exit to main menu");

    private final int key;
    private final String value;
    IngredientMenu (int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
