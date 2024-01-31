package constants;

public class Regex {
    public static final String I_CODE = "^[iI]\\d{2}$"; // I01, I02, I03, I04, I05, I06, I07, I08, I09
    public static final String I_NAME = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // name: start with letter, can contain number, space
    public static final String I_TYPE = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // type: start with letter, can contain number, space
    public static final String I_QUANTITY = "^[1-9][0-9]?$|^100$"; // quantity: 1-100
    public static final String I_UNIT = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // unit: start with letter, can contain number, space
    public static final String I_PRICE = "^[1-9][0-9]*$"; // price: positive number
    public static final String YES_NO = "^[yYnN]$"; //y,Y,n,N
}
