package utils;

public class StringTools {

    //khi chuỗi có 2 khoảng trắng thừa thì sẽ xử lí còn 1
    public static String removeTwoSpace(String inp) {
        inp = inp.trim();
        return inp.replaceAll("\\s+", " ");
    }

    public static void printTitle(String type) {
        String tittle = "";
        switch (type){
            case "i":
                tittle = String.format("| %-5s | %-15s | %10s | %5s | %15s |\n","Code","Name","Quantity","Unit","Price");
                break;
            case "m":
                tittle = String.format("| %-10s | %-20s | %-20s |\n", "Code", "Name", "Price");
                break;
            case "o":

                break;
        }
        System.out.print(ConsoleColors.GREEN + tittle + ConsoleColors.RESET);
    }

    public static void printLine(String type) {
        String line = "";
        switch (type){
            case "i":
                line = "------------------------------------------------------------------";
                break;
            case "m":
                line = "---------------------------------------------";
                break;
            case "o":
                line = "-----------------------------------------------------------------------------------";
                break;
        }
        System.out.println(line);
    }
}
