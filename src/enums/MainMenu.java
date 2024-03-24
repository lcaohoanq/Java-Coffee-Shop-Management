package enums;

public enum MainMenu {
    MANAGE_INGREDIENTS(1, "Manage ingredients"),
    MANAGE_BEVERAGES(2, "Manage beverages"),
    MANAGE_ORDERS(3, "Manage orders"),
    MANAGE_REPORTS(4, "Manage reports"),
    STORE_DATA_TO_FILE(5, "Store data to file"),
    EXIT(6, "Exit");

    private final int key;
    private final String value;

    MainMenu(int key, String value) {
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
