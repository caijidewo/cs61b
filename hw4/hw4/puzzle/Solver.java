package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import java.util.Comparator;
//import java.util.HashMap;

public class Solver {
    private MinPQ<SearchNode> pq;
    private int minMoves;
    private Stack<WorldState> answer;
    //private HashMap<WorldState, Integer> estimateDisCache;

    private class SearchNode {
        private WorldState worldState;
        private int distanceFromS;
        private SearchNode preSearchNode;
        SearchNode(WorldState ws, int M, SearchNode pre) {
            worldState = ws;
            distanceFromS = M;
            preSearchNode = pre;
        }
    }
    private class CompareSearchNode implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode node1, SearchNode node2) {
            int node1OfF = node1.distanceFromS + node1.worldState.estimatedDistanceToGoal();
            int node2OfF = node2.distanceFromS + node2.worldState.estimatedDistanceToGoal();
            /*
            int node1OfF;
            int node2OfF;
            if (estimateDisCache.containsKey(node1.worldState)) {
                node1OfF = node1.distanceFromS + estimateDisCache.get(node1.worldState);
            } else {
                estimateDisCache.put(node1.worldState, node1.worldState.estimatedDistanceToGoal());
                node1OfF = node1.distanceFromS + estimateDisCache.get(node1.worldState);
            }
            if (estimateDisCache.containsKey(node2.worldState)) {
                node2OfF = node2.distanceFromS + estimateDisCache.get(node2.worldState);
            } else {
                estimateDisCache.put(node2.worldState, node2.worldState.estimatedDistanceToGoal());
                node2OfF = node2.distanceFromS + estimateDisCache.get(node2.worldState);
            }
             */
            if (node1OfF > node2OfF) {
                return 1;
            } else if (node1OfF < node2OfF) {
                return -1;
            } else {
                return 0;
            }
        }
    }
    Solver(WorldState initial) {
        CompareSearchNode compareSearchNode = new CompareSearchNode();
        pq = new MinPQ<>(compareSearchNode);
        pq.insert(new SearchNode(initial, 0, null));
        minMoves = 0;
        //estimateDisCache = new HashMap<>(16);
    }
    public int moves() {
        answer = new Stack<>();
        SearchNode goal = aStart();
        while (goal != null) {
            answer.push(goal.worldState);
            goal = goal.preSearchNode;
            minMoves = minMoves + 1;
        }
        return minMoves - 1;
    }
    private SearchNode aStart() {
        while (!pq.isEmpty()) {
            SearchNode bMS = pq.delMin();
            if (bMS.worldState.isGoal()) {
                return bMS;
            }
            for (WorldState s : bMS.worldState.neighbors()) {
                if (!isSameGrandfatherNode(s, bMS)) {
                    pq.insert(new SearchNode(s, bMS.distanceFromS + 1, bMS));
                }
            }
        }
        return null;
    }
    private boolean isSameGrandfatherNode(WorldState nowNode, SearchNode preNode) {
        for (int i = 0; i < 2; i++) {
            if (preNode == null) {
                return false;
            }
            if (nowNode.equals(preNode.worldState)) {
                return true;
            }
            preNode = preNode.preSearchNode;
        }
        return false;
    }
    public Iterable<WorldState> solution() {
        return answer;
    }
    /*
    public static void main(String[] args) {
        WorldState w1 = new Word("cube", "tubes");
        WorldState w2 = new Word("cube", "tubes");
        HashMap<WorldState, Integer> j = new HashMap<>();
        j.put(w1, 1);
        System.out.println(j.containsKey(w2));
    }
     */
}
