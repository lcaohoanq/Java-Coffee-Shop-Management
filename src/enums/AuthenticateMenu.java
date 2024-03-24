package enums;

public enum AuthenticateMenu {
    LOGIN(1, "Login"),
    REGISTER(2, "Register"),
    EXIT(3, "Exit");
    private final int key;
    private final String value;
    AuthenticateMenu(int key, String value) {
        this.key = key;
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}