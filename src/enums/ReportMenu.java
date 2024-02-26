package enums;

public enum ReportMenu {
    SHOW_AVAILABLE_INGREDIENTS(1, "Show available ingredients"),
    SHOW_OUT_INGREDIENTS(2, "Show out ingredients"),
    SHOW_CURRENT_ORDER(3, "Show current order"),
    EXIT_TO_MAIN_MENU(4, "Exit to main menu");
    private final int key;
    private final String value;
    ReportMenu(int key, String value) {
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
