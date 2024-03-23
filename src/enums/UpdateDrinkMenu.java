package enums;

public enum UpdateDrinkMenu {
    ADD_NEW_INGREDIENT_TO_DRINK(1, "Add new ingredient to the drink"),
    REMOVE_INGREDIENT_FROM_DRINK(2, "Remove ingredient from the drink"),
    ADJUST_INGREDIENT_IN_DRINK(3, "Adjust the ingredient in the drink"),
    EXIT_TO_MAIN_MENU(4, "Exit to manage drinks menu");
    private final int key;
    private final String value;
    UpdateDrinkMenu(int key, String value) {
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
