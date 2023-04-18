package model;
import core.Constants;
public enum OPERATION_TYPE {
    MINUS,
    ADD,
    GOAL,
    START,
    MULT,
    POW,
    WALL,
    DECREASE_GOAL,
    INCREASE_GOAL,
    UNDEFINED;

    public static OPERATION_TYPE getOperation(String op) {
        switch (op) {
            case "-" :return MINUS;
            case "+" :return ADD;
            case "g" :return GOAL;
            case "*" :return MULT;
            case "^" :return POW;
            case "s" :return START;
            case "w" :return WALL;
            case "a" :return INCREASE_GOAL;
            case "b" :return DECREASE_GOAL;
            default :return UNDEFINED;
        }
    }

    public static String getOperationTag(OPERATION_TYPE opt) {
         switch (opt) {
            case MINUS :return "-";
            case ADD :return "+";
            case MULT :return "*";
            case POW :return "^";
            case GOAL :return Constants.GOAL;
            case START :return Constants.CASTLE;
            case WALL :return Constants.WALL;
            case INCREASE_GOAL :return Constants.FLOWER;
            case DECREASE_GOAL :return Constants.BOMB;
             default : return " ";
        }
    }
}