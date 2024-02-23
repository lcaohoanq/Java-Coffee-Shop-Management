package constants;

public class Regex {
    //ingredient
    public static final String I_CODE = "^[iI]\\d{2}$"; // I01, I02, I03, I04, I05, I06, I07, I08, I09
    public static final String I_NAME = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // name: start with letter, can contain number, space
    public static final String I_NUMBER = "^\\d+$"; // quantity: 1-100
    public static final String I_UNIT = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // unit: start with letter, can contain number, space
    public static final String QUANTITY = "^\\d+[.]?\\d*$"; // accept positive integer or double
    //drink
    public static final String D_CODE = "^[dD]\\d{2}$"; // D01, D02, D03, D04, D05, D06, D07, D08, D09
    public static final String D_NAME = "^[a-zA-Z]+[a-zA-Z0-9\\s]*$"; // name: start with letter, can contain number, space
    //order
    public static final String O_PATTERN = "^([dD]\\d{2}[\\s]?)*$"; //d00, d01, D18, D40
    //others
    public static final String YES_NO = "^[yYnN]$"; //y,Y,n,N
}
