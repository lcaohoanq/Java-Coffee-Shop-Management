package enums;

public enum OrderMenu {
    DISPENSING_DRINK(1, "Dispensing a drink"),
    UPDATE_DISPENSING_DRINK(2, "Update the dispensing drink"),
    EXIT_TO_MAIN_MENU(3, "Exit to main menu");
    private final int key;
    private final String value;
    OrderMenu(int key, String value) {
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
