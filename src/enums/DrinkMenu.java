package enums;

public enum DrinkMenu {
    ADD_DRINK(1, "Add the drink to menu"),
    ADD_INGREDIENT_TO_DRINK(2, "Add ingredient to the drink"),
    REMOVE_INGREDIENT_FROM_DRINK(3, "Remove ingredient from the drink"),
    ADJUST_INGREDIENT_IN_DRINK(4, "Adjust the ingredient in the drink"),
    EXIT_TO_MAIN_MENU(5, "Exit to main menu");
    private final int key;
    private final String value;
    DrinkMenu(int key, String value) {
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
