package AI;

import model.Node;

import java.util.*;

public class IDS {
    public void search(Node startNode) {
        int i,n=1;
        for(i = 0; i < n; i++)
            if (dfs(startNode,i)) return;
    }
    public boolean dfs(Node startNode, int max){
        int depth =0;
        Stack<Node> frontier = new Stack<Node>();
     //   Hashtable<String, Boolean> inFrontier = new Hashtable<>();
     //   Hashtable<String, Boolean> explored = new Hashtable<>();
        if (startNode.isGoal()) {
            System.out.println("score : " + startNode.sum);
            printResult(startNode, 0);
            return true;
        }
        frontier.push(startNode);
     //   inFrontier.put(startNode.hash(), true);
        while (!frontier.isEmpty() && depth <= max) {
            Node temp = frontier.pop();
            if (temp.isGoal()){
                printResult(temp, 0);
                System.out.println(temp.sum);
                return true;
            }
       //     inFrontier.remove(temp.hash());
        //    explored.put(temp.hash(), true);
            ArrayList<Node> children = temp.successor();
            for (Node child : children) {
           /*     if (!(inFrontier.containsKey(child.hash())) && !(explored.containsKey(child.hash()))) {
                    if (child.isGoal()) {
                        printResult(child, 0);
                        System.out.println(child.sum);
                        return true;
                    }

                    inFrontier.put(child.hash(), true);
                }
            */
                frontier.push(child);
            }
            depth ++;
        }

        System.out.println("no solution");
        return false;
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
