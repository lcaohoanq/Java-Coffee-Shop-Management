package constants;

public class Message {
    // Ingredient information
    public static final String INPUT_INGREDIENT_ID = "Input ingredient id: ";
    public static final String INPUT_INGREDIENT_NAME = "Input ingredient name: ";
    public static final String INPUT_INGREDIENT_TYPE = "Input ingredient type: ";
    public static final String INPUT_INGREDIENT_QUANTITY = "Input ingredient quantity: ";
    public static final String INPUT_INGREDIENT_UNIT = "Input ingredient unit: ";
    public static final String INPUT_INGREDIENT_PRICE = "Input ingredient price: ";
    public static final String INGREDIENT_DOES_NOT_EXIST = "Ingredient does not exist";
    public static final String UPDATE_INGREDIENT_SUCCESSFULLY = "Update ingredient successfully";
    public static final String INGREDIENT_CODE_MUST_BE_H_AND_2_DIGITS = "Ingredient code must be 'I' and 2 digits";
    public static final String INGREDIENT_NAME_MUST_START_WITH_LETTER = "Ingredient name must start with letter";
    public static final String INGREDIENT_TYPE_MUST_A_LETTER = "Ingredient type must be a letter";
    public static final String INGREDIENT_QUANTITY_MUST_BE_A_POSITIVE_NUMBER = "Ingredient quantity must be a positive number";
    public static final String INGREDIENT_UNIT_MUST_A_LETTER = "Ingredient unit must be a letter";
    // if exist
    public static final String INGREDIENT_CODE_IS_EXISTED = "Ingredient code is existed";

    //Validation message if null value
    public static final String INGREDIENT_NAME_IS_REQUIRED = "Ingredient name is required";
    public static final String INGREDIENT_CODE_IS_REQUIRED = "Ingredient code is required";
    public static final String INGREDIENT_TYPE_IS_REQUIRED = "Ingredient type is required";
    public static final String INGREDIENT_QUANTITY_IS_REQUIRED = "Ingredient quantity is required";
    public static final String INGREDIENT_UNIT_IS_REQUIRED = "Ingredient unit is required";
    public static final String INGREDIENT_PRICE_IS_REQUIRED = "Ingredient price is required";

    // Operation 
    public static final String ADD_INGREDIENT_SUCCESSFULLY = "Add ingredient successfully";
    public static final String ADD_INGREDIENT_FAILED = "Add ingredient failed";
    public static final String DELETE_INGREDIENT_SUCCESSFULLY = "Delete ingredient successfully";

    // File
    public static final String READ_FILE_FAILED = "Read file failed ";
    public static final String SAVE_FILE_SUCCESS = "Write file success ";
    public static final String SAVE_FILE_FAILED = "Write file failed ";

    // User confirmation prompts
    public static final String DO_YOU_WANT_TO_CONTINUE = "Do you want to continue? (y/n): ";
    public static final String PLEASE_INPUT_Y_OR_N = "Please input 'y' or 'n'";

}
