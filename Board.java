package model;

import core.Constants;

public class Board {
    private int row;
    private int col;
    private Cell[][] cells;
    public Board(int row, int col, Cell[][] cells) {
        this.row = row;
        this.col = col;
        this.cells = cells;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public int countWall(int a,int b,int c,int d){
        int i = a, j, count = 0;
       // if()
        for(j = b ; j <= d ;j ++ ) {
            if (cells[i][j].getOperationType() == OPERATION_TYPE.WALL) count++;
        }
        j = d;
        for(i = a ; i <= c ;i ++ ) {
            if (cells[i][j].getOperationType() == OPERATION_TYPE.WALL) count++;
        }
        return count;
    }

    public Cell[][] getCells() {
        return cells;
    }

    @Override
    public String toString() {
        //todo needs a refactor
        StringBuilder map = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                map.append(Constants.ANSI_BRIGHT_BLUE)
                        .append(OPERATION_TYPE.getOperationTag(cells[i][j].getOperationType()))
                        .append(cells[i][j].getValue())
                        .append("\t");

            }
            map.append("\n");
        }
        return map.toString();
    }

}