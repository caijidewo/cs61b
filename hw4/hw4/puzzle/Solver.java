package hw4.puzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;
import java.util.HashMap;

public class Solver {
    private MinPQ<SearchNode> pq;
    private int minMoves;
    private Stack<WorldState> answer;
    private HashMap<WorldState, Integer> estimateDisCache;

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
            //int node1OfF = node1.distanceFromS + node1.worldState.estimatedDistanceToGoal();
            //int node2OfF = node2.distanceFromS + node2.worldState.estimatedDistanceToGoal();
            int node1OfF;
            int node2OfF;
            if (estimateDisCache.containsKey(node1.worldState)) {
                node1OfF = estimateDisCache.get(node1.worldState);
            } else {
                node1OfF = node1.distanceFromS + node1.worldState.estimatedDistanceToGoal();
            }
            if (estimateDisCache.containsKey(node2.worldState)) {
                node2OfF = estimateDisCache.get(node2.worldState);
            } else {
                node2OfF = node2.distanceFromS + node2.worldState.estimatedDistanceToGoal();
            }
            return Integer.compare(node1OfF,node2OfF);
        }
    }
    Solver(WorldState initial) {
        CompareSearchNode compareSearchNode = new CompareSearchNode();
        pq = new MinPQ<>(compareSearchNode);
        pq.insert(new SearchNode(initial, 0, null));
        minMoves = 0;
        estimateDisCache = new HashMap<>();
    }
    public int moves() {
        answer = new Stack<>();
        SearchNode goal = AStart();
        while (goal != null) {
            answer.push(goal.worldState);
            goal = goal.preSearchNode;
            minMoves = minMoves + 1;
        }
        return minMoves - 1;
    }
    private SearchNode AStart() {
        while (!pq.isEmpty()) {
            SearchNode BMS = pq.delMin();
            if (BMS.worldState.isGoal()) {
                return BMS;
            }
            for (WorldState s : BMS.worldState.neighbors()) {
                if (!s.equals(BMS.worldState)) {
                    if (!isSamePreNode(s, BMS)) {
                        pq.insert(new SearchNode(s, BMS.distanceFromS + 1, BMS));
                    }
                }
            }
        }
        return null;
    }
    private boolean isSamePreNode(WorldState nowNode, SearchNode preNode) {
        for (int i = 0; i < 2; i++) {
            if (preNode != null && nowNode.equals(preNode.worldState)) {
                return true;
            }
            if (preNode != null) {
                preNode = preNode.preSearchNode;
            }
        }
        return false;
    }
    public Iterable<WorldState> solution() {
        return answer;
    }
}
