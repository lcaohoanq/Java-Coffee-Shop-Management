package models;

public class Customer extends Person{
    private String cusId;  // Auto-incremented customer ID
    private static int lastCusId = 0;
    public Customer(String code, String name, int yob, String sex) {
        super(code, name, yob, sex);
        this.cusId = generateCusId();
    }

    private String generateCusId() {
        int numericPart = ++lastCusId;
        return "C" + String.format("%02d", numericPart);
    }

    public String getCusId() {
        return cusId;
    }
}
