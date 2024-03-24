
package utils;

import constants.Message;
import constants.Regex;

import java.util.Scanner;

public class Utils {

    private static Scanner sc = new Scanner(System.in);

    public static String getString(String welcome, String msg) {
        boolean check = true;
        String result = "";
        do {
            System.out.print("\n" + welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(msg);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String pattern, String msgreg) {
        boolean check = true;
        String result = "";
        do {
            System.out.print("\n" + welcome);
            result = sc.nextLine();
            if (!result.isEmpty() && !result.matches(pattern)) {
                System.out.println(ConsoleColors.RED + msgreg + ConsoleColors.RESET);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getString(String welcome, String pattern, String msg, String msgreg) {
        boolean check = true;
        String result = "";
        do {

            System.out.print("\n" + welcome);
            result = sc.nextLine();
            if (result.isEmpty()) {
                System.out.println(ConsoleColors.RED + msg + ConsoleColors.RESET);
            } else if (!result.matches(pattern)) {
                System.out.println(ConsoleColors.RED + msgreg + ConsoleColors.RESET);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static int getInt(String welcome, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {

                System.out.print("\n" + welcome);
                number = Integer.parseInt(sc.nextLine());
                if (number < min) {
                    System.out.println(ConsoleColors.RED + "Number must be large than " + min + ConsoleColors.RESET);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "Input integer number!!!" + ConsoleColors.RESET);
            }
        } while (check || number < min);
        return number;
    }

    public static int getInt(String inpMsg, String errMsg,
            int lowerBound, int upperBound) {
        if (lowerBound > upperBound) {
            int tmp = lowerBound;
            lowerBound = upperBound;
            upperBound = tmp;
        }

        System.out.print(inpMsg);
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine());
                if (number < lowerBound || number > upperBound) {
                    throw new Exception();
                }
                return number;
            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + errMsg + ConsoleColors.RESET);
            }
        }
    }

    public static int getInt(String inpMsg,String pattern, String errMsg){
        System.out.print(inpMsg);
        while(true){
            try{
                String number = sc.nextLine();
                if(number.isEmpty()){
                    return -1;
                }else if(number.matches(pattern)){
                    return Integer.parseInt(number.trim());
                }else{
                    throw new Exception();
                }
            }catch(Exception e){
                System.out.println(ConsoleColors.RED + errMsg + ConsoleColors.RESET);
            }
        }
    }

    public static double getDouble(String welcome, double min) {
        boolean check = true;
        double number = 0.0;
        do {
            try {

                System.out.print("\n" + welcome);
                number = Double.parseDouble(sc.nextLine());
                if (number <= min) {
                    System.out.println(ConsoleColors.RED + "Number must be large than " + min + ConsoleColors.RESET);
                } else {
                    check = false;
                }

            } catch (Exception e) {
                System.out.println(ConsoleColors.RED + "Input double number!!!" + ConsoleColors.RESET);
            }
        } while (check || number < min);
        return number;
    }

    public static double getDouble(String inpMsg,String pattern, String errMsg){
        System.out.println(inpMsg);
        while(true){
            try{
                String number = sc.nextLine();
                if(number.isEmpty()){
                    return -1;
                }else if(number.matches(pattern)){
                    return Double.parseDouble(number.trim());
                }else{
                    throw new Exception();
                }
            }catch(Exception e){
                System.out.println(ConsoleColors.RED + errMsg + ConsoleColors.RESET);
            }
        }
    }

    public static boolean getUserConfirmation(String msg) {
        return getString(msg,Regex.YES_NO, Message.OPTIONS_IS_REQUIRED, Message.PLEASE_INPUT_Y_OR_N ).equalsIgnoreCase("y");
    }

}
