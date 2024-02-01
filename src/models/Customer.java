package models;

public class Customer extends Person{
    private String cusId;  // Auto-incremented customer ID
    private static int lastCusId = 0;
    public Customer(String code, String name, int yob, String sex) {
        super(code, name, yob, sex);
        this.cusId = generateCusId();
    }

    //normal case, we just need a cusId to create a customer
    //cusId will be generated automatically
    public Customer(){
        super();
        this.cusId = generateCusId();
    }
    private String generateCusId() {
        int numericPart = ++lastCusId;
        return "C" + String.format("%02d", numericPart);
    }

    public String getCusId() {
        return cusId;
    }

    public void showFullInfo(){
        String infor = String.format("%-10s %-6s %-20s %-8d %-10s", cusId, getCode(), getName(), getYob(), getSex());
        System.out.println(infor);
    }

    //print customer id and name
    @Override
    public String toString() {
        return String.format("%-10s %-20s", cusId,getName());
    }
}
