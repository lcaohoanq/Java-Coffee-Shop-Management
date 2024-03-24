package constants;

import utils.ConsoleColors;

public class Message {
    //
    public static final String COFFEE_SHOP_MANAGEMENT_PROGRAM = "Coffee Shop Management Program";
    // Ingredient information
    public static final String INPUT_INGREDIENT_CODE = "Input ingredient code: ";
    public static final String INPUT_INGREDIENT_NAME = "Input ingredient name: ";
    public static final String INPUT_INGREDIENT_QUANTITY = "Input ingredient quantity: ";
    public static final String INPUT_INGREDIENT_UNIT = "Input ingredient unit: ";
    public static final String INPUT_INGREDIENT_PRICE = "Input ingredient price: ";
    public static final String INPUT_NEW_INGREDIENT_NAME = "Input new ingredient name";
    public static final String INPUT_NEW_INGREDIENT_QUANTITY = "Input new ingredient quantity";
    public static final String INPUT_NEW_INGREDIENT_UNIT = "Input new ingredient unit";
    public static final String INPUT_NEW_INGREDIENT_PRICE = "Input new ingredient price";

    // Drink information
    public static final String INPUT_DRINK_CODE = "Input drink code: ";
    public static final String INPUT_DRINK_NAME = "Input drink name: ";
    public static final String INPUT_NEW_DRINK_NAME = "Input new drink name: ";
    public static final String INPUT_NEW_QUANTITY = "Input new quantity: ";

    // Order information
    public static final String INPUT_NEW_QUANTITY_OF_ORDER = "Input new quantity of order: ";
    public static final String NO_ONE_ORDERED = ConsoleColors.RED + "No one ordered" + ConsoleColors.RESET;
    public static final String ORDER_PATTERN = ConsoleColors.RED + "Order information is the drink code and separate by one space if more than one" + ConsoleColors.RESET;

    // if exist
    public static final String INGREDIENT_CODE_IS_EXISTED = ConsoleColors.RED + "Ingredient code is existed" + ConsoleColors.RESET;
    public static final String INGREDIENT_IS_NOT_EXIST = ConsoleColors.RED + "Ingredient is not exist" + ConsoleColors.RESET;
    public static final String DRINK_CODE_IS_EXISTED = ConsoleColors.RED + "Drink code is existed" + ConsoleColors.RESET;
    public static final String DRINK_IS_NOT_EXIST = ConsoleColors.RED + "Drink is not exist" + ConsoleColors.RESET;
    public static final String DRINK_CODE_IS_NOT_EXIST_IN_CURRENT_ORDER = ConsoleColors.RED + "Drink code is not exist in current order" + ConsoleColors.RESET;

    // tittle
    public static final String SELECT_INGREDIENT_AT_LIST_BELOW = "\n" + "Select ingredient at list below: ";
    public static final String SELECT_DRINK_AT_LIST_BELOW = "\n" + "Select drink at list below: ";
    public static final String SELECT_ORDER_AT_LIST_BELOW = "\n" + "Select order at list below: ";

    //Validation message if null value
    public static final String INGREDIENT_CODE_IS_REQUIRED = ConsoleColors.RED + "Ingredient code is required" + ConsoleColors.RESET;
    public static final String INGREDIENT_NAME_IS_REQUIRED = ConsoleColors.RED + "Ingredient name is required" + ConsoleColors.RESET;
    public static final String INGREDIENT_UNIT_IS_REQUIRED = ConsoleColors.RED + "Ingredient unit is required" + ConsoleColors.RESET;
    public static final String DRINK_CODE_IS_REQUIRED = ConsoleColors.RED + "Drink code is required" + ConsoleColors.RESET;
    public static final String DRINK_NAME_IS_REQUIRED = ConsoleColors.RED + "Drink name is required" + ConsoleColors.RESET;
    public static final String OPTIONS_IS_REQUIRED = ConsoleColors.RED + "Options is required" + ConsoleColors.RESET;

    // Validation form data input
    public static final String INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS = "Ingredient code must be 'I' and 2 digits";
    public static final String INGREDIENT_NAME_MUST_START_WITH_LETTER = "Ingredient name must start with letter";
    public static final String INGREDIENT_UNIT_MUST_A_LETTER = "Ingredient unit must be a letter";
    public static final String INGREDIENT_NAME_REQUIRED_A_LETTER_OR_BLANK = "Ingredient name required a letter or blank";
    public static final String INGREDIENT_UNIT_REQUIRED_A_LETTER_OR_BLANK = "Ingredient unit is required a letter or blank";
    public static final String QUANTITY_REQUIRED_A_POSITIVE_INTEGER_OR_DOUBLE = "Quantity required a positive integer or double";
    public static final String INGREDIENT_PRICE_REQUIRED_A_NUMBER_OR_BLANK = "Ingredient price required a number or blank";
    public static final String DRINK_CODE_MUST_BE_D_AND_2_DIGITS = "Drink code must be 'D' and 2 digits";
    public static final String DRINK_NAME_MUST_START_WITH_LETTER = "Drink name must start with letter";

    // Operation
    public static final String ADD_INGREDIENT_SUCCESSFULLY = ConsoleColors.GREEN + "Add ingredient successfully" + ConsoleColors.RESET;
    public static final String UPDATE_INGREDIENT_SUCCESSFULLY = ConsoleColors.GREEN + "Update ingredient successfully" + ConsoleColors.RESET;
    public static final String DELETE_INGREDIENT_SUCCESSFULLY = ConsoleColors.GREEN + "Delete ingredient successfully" + ConsoleColors.RESET;
    public static final String UPDATE_DRINK_SUCCESSFULLY = "Update drink successfully";
    public static final String ADD_INGREDIENT_TO_DRINK_SUCCESSFULLY = ConsoleColors.GREEN + "Add ingredient to drink successfully" + ConsoleColors.RESET;
    public static final String ADD_DRINK_SUCCESSFULLY = ConsoleColors.GREEN + "Add drink successfully" + ConsoleColors.RESET;
    public static final String DELETE_DRINK_SUCCESSFULLY = ConsoleColors.GREEN + "Delete drink successfully" + ConsoleColors.RESET;
    public static final String DELETE_INGREDIENT_FROM_DRINK_SUCCESSFULLY = ConsoleColors.GREEN + "Delete ingredient from drink successfully" + ConsoleColors.RESET;
    public static final String ADD_DRINK_FAILED = ConsoleColors.RED + "Add drink failed" + ConsoleColors.RESET;
    public static final String DELETE_INGREDIENT_FAILED = ConsoleColors.RED + "Delete ingredient failed" + ConsoleColors.RESET;
    public static final String ORDER_SUCCESSFULLY = ConsoleColors.GREEN + "Order successfully" + ConsoleColors.RESET;
    public static final String ORDER_FAILED_OUT_OF_INGREDIENT = ConsoleColors.RED + "Order failed, out of ingredient for your order" + ConsoleColors.RESET;
    public static final String ENTER_TO_KEEP_THE_OLD_INFORMATION = " enter to keep the old information: ";

    //Processing
    public static final String BEFORE_UPDATING = ConsoleColors.RED + "Before updating: " + ConsoleColors.RESET;
    public static final String AFTER_UPDATING = ConsoleColors.GREEN + "After updating: " + ConsoleColors.RESET;
    public static final String BEFORE_DELETING = ConsoleColors.RED + "Before deleting: " + ConsoleColors.RESET;
    public static final String AFTER_DELETING = ConsoleColors.GREEN + "After deleting: " + ConsoleColors.RESET;
    public static final String BEFORE_ADDING = ConsoleColors.RED + "Before adding: " + ConsoleColors.RESET;
    public static final String AFTER_ADDING = ConsoleColors.GREEN + "After adding: " + ConsoleColors.RESET;

    // File
    public static final String FILE_NOT_FOUND = ConsoleColors.RED + "File not found " + ConsoleColors.RESET;
    public static final String READ_FILE_FAILED = ConsoleColors.RED + "Read file failed " + ConsoleColors.RESET;
    public static final String SAVE_FILE_FAILED = ConsoleColors.RED + "Write file failed " + ConsoleColors.RESET;
    public static final String READ_FILE_SUCCESSFULLY = ConsoleColors.GREEN + "Read file successfully " + ConsoleColors.RESET;
    public static final String SAVE_FILE_SUCCESSFULLY = ConsoleColors.GREEN + "Write file successfully " + ConsoleColors.RESET;

    // User confirmation prompts
    public static final String DO_YOU_WANT_TO_CONTINUE = "Do you want to continue? (y/n): ";
    public static final String DO_YOU_WANT_TO_CONTINUE_TO_PICK_INGREDIENT = "Do you want to continue to pick ingredient? (y/n): ";
    public static final String DO_YOU_READY_WANT_TO_DELETE = "Do you ready want to delete? (y/n): ";
    public static final String DO_YOU_WANT_TO_CONTINUE_TO_DELETE_DRINK = "Do you want to continue to delete drink? (y/n): ";
    public static final String DO_YOU_WANT_TO_ORDER_MORE_DRINK = "Do you want to order more drink? (y/n): ";
    public static final String PLEASE_INPUT_Y_OR_N = "Please input 'y' or 'n'";

    // others
    public static final String LOGIN_SUCCESS = ConsoleColors.GREEN + "Login successfully!" + ConsoleColors.RESET;
    public static final String LOGIN_FAILED = ConsoleColors.RED + "Login failed!" + ConsoleColors.RESET;
    public static final String REGISTER_SUCCESS = ConsoleColors.GREEN + "Register successfully!" + ConsoleColors.RESET;
    public static final String REGISTER_FAILED = ConsoleColors.RED + "Register failed!" + ConsoleColors.RESET;
    public static final String INGREDIENT_LIST_IS_EMPTY = ConsoleColors.RED + "Ingredient list is empty" + ConsoleColors.RESET;
    public static final String MENU_DRINK_IS_EMPTY = ConsoleColors.RED + "Menu drink is empty" + ConsoleColors.RESET;
    public static final String OUT_OF_INGREDIENT = ConsoleColors.RED + "Out of ingredient" + ConsoleColors.RESET;
    public static final String OUT_OF_ALL_INGREDIENT = ConsoleColors.RED + "Out of all ingredient" + ConsoleColors.RESET;
    public static final String ORDER_LIST_IS_EMPTY = ConsoleColors.RED + "Order list is empty" + ConsoleColors.RESET;
    public static final String NOTHING_TO_SHOW = ConsoleColors.RED + "Nothing to show" + ConsoleColors.RESET;
    public static final String BLANK_TO_KEEP_THE_OLD_INFORMATION = " blank to keep the old information";
    public static final String TOTAL = ConsoleColors.PURPLE_BACKGROUND + "Total: " + ConsoleColors.RESET;

}
