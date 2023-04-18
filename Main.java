package core;


import model.ACTION_TYPE;
import model.Board;
import model.Cell;
import model.Node;
import AI.*;
import java.util.Hashtable;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println(" please enter rows and columns of your board : \n");
        Scanner sc = new Scanner(System.in);
        String mn = sc.nextLine();
        int rows = Integer.parseInt(mn.split(" ")[0]);
        int columns = Integer.parseInt(mn.split(" ")[1]);
        String[][] board = new String[rows][columns];
        String[] lines = new String[rows];
        for (int i = 0; i < rows; i++) {
            lines[i] = sc.nextLine();
            String[] line = lines[i].split(" ");
            if (columns >= 0) System.arraycopy(line, 0, board[i], 0, columns);
        }
        Mapper mapper = new Mapper();
        Cell[][] cells = mapper.createCells(board, rows, columns);
        Board gameBoard = mapper.createBoard(cells, rows, columns);
        Hashtable<String, Boolean> initHash = new Hashtable<>();
        initHash.put(Cell.getStart().toString(), true);
        Node start = new Node(Cell.getStart(), Cell.getStart().getValue(), Cell.getGoal().getValue(), gameBoard, null, initHash, ACTION_TYPE.RIGHT);
        Node goal = new Node(Cell.getGoal(), Cell.getGoal().getValue(), Cell.getStart().getValue(), gameBoard, null, initHash, ACTION_TYPE.LEFT);

        IDS ids = new IDS();
        ids.search(start);
       /* BFS bfs = new BFS();
        bfs.search(start);

        //it has problem
        BDS bds = new BDS();
        bds.search(start,goal);



        A a = new A();
        a.search(start);

        IDA ida = new IDA();
        ida.search(start);
        */

    }
}
/*
d4
3 3
s1 +7 +3
*3 -2 +1
-3 *8 g10

d6
4 4
s1 *1 *10 *2
*1 -10 -100 +10
+5 *10 g100 +5
*1 +5 +90 +10

d12
6 6
s1 +9 *1 *1 *1 *1
*5 +5 *1 *1 *1 *1
*2 +15 w0 w0 w0 +10
-50 -50 w0 g100 w0 +10
*1 *1 a10 -10 +10 +10
+10 +10 b10 w0 -10 -100
* */