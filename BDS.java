package AI;

import model.Node;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class BDS {
    public void search(Node startNode, Node goalNode) {
        if (startNode.isGoal()) {
            System.out.println("score : " + startNode.sum);
            printResult(startNode, 0);
            return;
        }
        Queue<Node> frontier1 = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier1 = new Hashtable<>();
        Hashtable<String, Boolean> explored1 = new Hashtable<>();
        Queue<Node> frontier2 = new LinkedList<Node>();
        Hashtable<String, Boolean> inFrontier2 = new Hashtable<>();
        Hashtable<String, Boolean> explored2 = new Hashtable<>();

        frontier1.add(startNode);
        inFrontier1.put(startNode.hash(), true);
        frontier2.add(goalNode);
        inFrontier2.put(goalNode.hash(), true);

        while (!frontier1.isEmpty() && !frontier2.isEmpty()) {
            Node temp1 = frontier1.poll();
            inFrontier1.remove(temp1.hash());
            explored1.put(temp1.hash(), true);
            ArrayList<Node> children1 = temp1.successor();

            Node temp2 = frontier1.poll();
            inFrontier2.remove(temp2.hash());
            explored1.put(temp2.hash(), true);
            ArrayList<Node> children2 = temp2.successor2();

            for (Node child1 : children1) {
                if (!(inFrontier1.containsKey(child1.hash())) && !(explored1.containsKey(child1.hash()))) {
                    for (Node child2 : children2) {
                        if (!(inFrontier2.containsKey(child2.hash())) && !(explored2.containsKey(child2.hash()))) {
                            //it has problem
                            if (child1 == child2) {
                                printResult(child1, 0);
                                System.out.println(child1.sum);
                                return;
                            }
                        }
                    }
                    frontier1.add(child1);
                    inFrontier1.put(child1.hash(), true);
                }
            }
            for (Node child2 : children2) {
                if (!(inFrontier2.containsKey(child2.hash())) && !(explored2.containsKey(child2.hash()))) {
                    frontier2.add(child2);
                    inFrontier2.put(child2.hash(), true);
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
