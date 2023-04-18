package AI;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class IDA {
    Queue<Node> frontier;
    public Node lowcost(int min){
        int f;
        while(true) {
            for (Node n : frontier) {
                f = n.heuristic() + n.cost + n.pathCost();
                if (f < min) {
                    return n;
                }
            }
            min ++;
        }
    }
    public void search(Node startNode) {
        int g = 0;
        Queue<Node> frontier = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier = new Hashtable<>();
        //Hashtable<String, Boolean> explored = new Hashtable<>();
        if (startNode.isGoal()) {
            System.out.println("score : " + startNode.sum);
            printResult(startNode, 0);
            return;
        }
        frontier.add(startNode);
        inFrontier.put(startNode.hash(), true);
        g = startNode.pathCost();
        while (!frontier.isEmpty()) {
            Node temp = lowcost(g+1);
            frontier.remove(temp);
            inFrontier.remove(temp.hash());
            if (temp.isGoal()) {
                printResult(temp, 0);
                System.out.println(temp.sum);
                return;
            }
            g += temp.pathCost();
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
                if (!inFrontier.containsKey(child.hash())) {
                    child.cost = g;
                    frontier.add(child);
                    inFrontier.put(child.hash(), true);
                }
            }
        }
        System.out.println("no solution");
    }

    public void printResult(Node node, int depthCounter) {
        if (node.parent == null) {
            System.out.println("problem solved at a depth of  : " + depthCounter);
            return;
        }
        System.out.println(node.toString());
        node.drawState();
        printResult(node.parent, depthCounter + 1);
    }
}
