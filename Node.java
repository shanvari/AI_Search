package model;

import core.Constants;
import java.util.ArrayList;
import java.util.Hashtable;

public class Node {
    public Board board;
    public int sum = 0;
    public Node parent;
    public Cell currentCell;
    private Cell[][] cells;
    private int goalValue;
    private Hashtable<String, Boolean> repeatedStates;
    private ACTION_TYPE previousAction;
    public boolean explored =false;
    public int cost = 0;
    public Node(Cell currentCell, int currentValue, int goalValue, Board board, Node parent, Hashtable<String, Boolean> repeated, ACTION_TYPE previousAction) {
        this.currentCell = currentCell;
        this.sum = currentValue;
        this.board = board;
        this.cells = board.getCells();
        this.parent = parent;
        this.previousAction = previousAction;
        this.goalValue = goalValue;
        Hashtable<String, Boolean> hashtableTemp = new Hashtable<String, Boolean>(repeated);
        hashtableTemp.put(this.toString(), true);
        this.repeatedStates = hashtableTemp;
        setGoalValue();
    }


    public ArrayList<Node> successor() {
        ArrayList<Node> result = new ArrayList<Node>();
        fetchLeftCell(result);
        fetchDownCell(result);
        fetchRightCell(result);
        fetchUpCell(result);
        return result;
    }

    public ArrayList<Node> successor2() {
        ArrayList<Node> result = new ArrayList<Node>();
        fetchLeftCell2(result);
        fetchDownCell2(result);
        fetchRightCell2(result);
        fetchUpCell2(result);
        return result;
    }

    private void fetchRightCell(ArrayList<Node> result) {
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            if (isValidMove(rightCell)) {
                int calculatedValue = calculate(rightCell);
                Node rightNode = new Node(rightCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.LEFT);
                result.add(rightNode);
            }
        }
    }

    private void fetchRightCell2(ArrayList<Node> result) {
        if (canMoveRight()) {
            Cell rightCell = this.cells[this.currentCell.i][this.currentCell.j + 1];
            if (isValidMove(rightCell)) {
                int calculatedValue = calculate(rightCell);
                Node rightNode = new Node(rightCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.LEFT);
                result.add(rightNode);
            }
        }
    }

    private void fetchLeftCell(ArrayList<Node> result) {
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            if (isValidMove(leftCell)) {
                int calculatedValue = calculate(leftCell);
                Node leftNode = new Node(leftCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.RIGHT);
                result.add(leftNode);
            }
        }
    }

    private void fetchLeftCell2(ArrayList<Node> result) {
        if (canMoveLeft()) {
            Cell leftCell = this.cells[this.currentCell.i][this.currentCell.j - 1];
            if (isValidMove(leftCell)) {
                int calculatedValue = calculate(leftCell);
                Node leftNode = new Node(leftCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.RIGHT);
                result.add(leftNode);
            }
        }
    }

    private void fetchDownCell(ArrayList<Node> result) {
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            if (isValidMove(downCell)) {
                int calculatedValue = calculate(downCell);
                Node downNode = new Node(downCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.UP);
                result.add(downNode);
            }
        }
    }

    private void fetchDownCell2(ArrayList<Node> result) {
        if (canMoveDown()) {
            Cell downCell = this.cells[this.currentCell.i + 1][this.currentCell.j];
            if (isValidMove(downCell)) {
                int calculatedValue = calculate(downCell);
                Node downNode = new Node(downCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.UP);
                result.add(downNode);
            }
        }
    }

    private void fetchUpCell(ArrayList<Node> result) {
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            if (isValidMove(upCell)) {
                int calculatedValue = calculate(upCell);
                Node upNode = new Node(upCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.DOWN);
                result.add(upNode);
            }
        }
    }

    private void fetchUpCell2(ArrayList<Node> result) {
        if (canMoveUp()) {
            Cell upCell = this.cells[this.currentCell.i - 1][this.currentCell.j];
            if (isValidMove(upCell)) {
                int calculatedValue = calculate(upCell);
                Node upNode = new Node(upCell, calculatedValue, goalValue, board, this, repeatedStates, ACTION_TYPE.DOWN);
                result.add(upNode);
            }
        }
    }

    private int calculate(Cell cell) {
         switch (cell.getOperationType()) {
            case MINUS : return sum - cell.getValue();
            case ADD : return sum + cell.getValue();
            case POW : return (int) Math.pow(sum, cell.getValue());
            case MULT : return sum * cell.getValue();
            default : return sum;
        }

    }

    public int pathCost() {
         switch (currentCell.getOperationType()) {
            case MINUS :
            case GOAL:
            case DECREASE_GOAL : return 1;
            case INCREASE_GOAL :
            case ADD :return 2;
            case MULT :return 5;
            case POW :return 11;
            default :return 0;
        }
    }

    public int heuristic() {
        int h;
        h = currentCell.distanceToG() * 5 + 2 * board.countWall(currentCell.i,currentCell.j,Cell.getGoal().i,Cell.getGoal().j);
        return h;
    }

    public String hash() {
        StringBuilder hash = new StringBuilder();
        hash.append("i:")
                .append(currentCell.i)
                .append("j:")
                .append(currentCell.j)
                .append("sum:")
                .append(sum)
                .append("op:")
                .append(currentCell.op)
                .append("val:")
                .append(currentCell.getValue()
                ).append(this.previousAction);
        return hash.toString();
    }

    public void drawState() {

        for (int i = 0; i < board.getRow(); i++) {
            for (int j = 0; j < board.getCol(); j++) {
                if (cells[i][j].getOperationType() == OPERATION_TYPE.GOAL) {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN +
                            OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType())
                            + goalValue + spaceRequired(cells[i][j])
                    );
                    continue;
                }
                if (currentCell.j == j && currentCell.i == i) {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN + Constants.PLAYER + sum + spaceRequired(cells[i][j]));
                } else {
                    System.out.print(Constants.ANSI_BRIGHT_GREEN +
                            OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType())
                            + cells[i][j].getValue() + spaceRequired(cells[i][j])
                    );
                }
            }
            System.out.println();

        }
        System.out.println("-----------------------------------------");

    }

    private void setGoalValue() {
        if (currentCell.getOperationType() == OPERATION_TYPE.DECREASE_GOAL)
            goalValue -= currentCell.getValue();
        if (currentCell.getOperationType() == OPERATION_TYPE.INCREASE_GOAL)
            goalValue += currentCell.getValue();
    }

    private String spaceRequired(Cell cell) {
        int length = String.valueOf(cell.getValue()).length();
        String result = " ".repeat(5 - length);
        if (cell.op.equals("+") || cell.op.equals("-") || cell.op.equals("*") || cell.op.equals("^")) {
            result += " ";
        }
        return result;
    }

    private boolean canEnterGoal(Cell downCell) {
        if (downCell != Cell.getGoal()) return true;
        else {
            return sum >= goalValue;
        }
    }

    private boolean isWall(Cell cell) {
        return cell.getOperationType() == OPERATION_TYPE.WALL;
    }

    private boolean canMoveRight() {
        return this.currentCell.j < this.board.getRow() - 1;
    }

    private boolean canMoveLeft() {
        return this.currentCell.j > 0;
    }

    private boolean canMoveUp() {
        return this.currentCell.i > 0;
    }

    private boolean canMoveDown() {
        return this.currentCell.i < this.board.getCol() - 1;
    }

    private Boolean isValidMove(Cell destCell) {
        return destCell != Cell.getStart() && canEnterGoal(destCell) && !isWall(destCell) && !repeatedStates.containsKey(destCell.toString());
    }
    private Boolean isValidMove2(Cell destCell) {
        return destCell != Cell.getGoal() && canEnterGoal(destCell) && !isWall(destCell) && !repeatedStates.containsKey(destCell.toString());
    }

    public boolean isGoal() {
        if (currentCell.getOperationType() == OPERATION_TYPE.GOAL) {
            return sum >= goalValue;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + this.currentCell.i + "," + this.currentCell.j + ")";
    }
}