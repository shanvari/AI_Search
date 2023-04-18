package model;

public class Cell {
    int i;
    int j;
    int cost;
    private int value;
    private OPERATION_TYPE operationType;
    public String op;
    private static Cell start;
    private static Cell goal;

    public Cell(int i, int j, int value, String op) {
        this.i = i;
        this.j = j;
        this.op = op;
        this.value = value;
        this.operationType = OPERATION_TYPE.getOperation(op);
        if (this.operationType == OPERATION_TYPE.GOAL) goal = this;
        if (this.operationType == OPERATION_TYPE.START) start = this;
        switch(this.operationType){
            case ADD:
            case DECREASE_GOAL:
                cost = 2;
                break;
            case POW:
                cost = 11;
                break;
            case MULT:
                cost = 5;
                break;
            case START:
                cost = 0;
            //need to check
            default:
                cost = 1;
        }
    }

    public int getValue() {
        return value;
    }

    public int distanceToG(){
        return Math.abs((i - goal.i) + (j - goal.j));
    }
    public int distanceToS(){
        return Math.abs((i - start.i) + (j - start.j));
    }

    public static Cell getGoal() {
        return goal;
    }

    public static Cell getStart() {
        return start;
    }

    public OPERATION_TYPE getOperationType() {
        return operationType;
    }

    @Override
    public String toString() {
        return "(" + this.i + "," + this.j + ")";
    }
}