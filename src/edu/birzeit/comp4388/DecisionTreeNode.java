package edu.birzeit.comp4388;

import java.util.*;

public class DecisionTreeNode {
    private Map<String, Integer> occurrences;
    private List<DecisionTreeEdge> downEdges;
    private DecisionTreeEdge upEdge;

    public DecisionTreeNode(DecisionTreeEdge upEdge) {
        this.occurrences = new HashMap<>();
        this.downEdges = new ArrayList<>();
        this.upEdge = upEdge;
    }

    public void addEdge(DecisionTreeEdge edge) {
        this.downEdges.add(edge);
    }

    public void addOccurence(String property, Integer value) {
        this.occurrences.put(property, value);
    }

    public Map<String, Integer> getOccurrences() {
        return occurrences;
    }

    public List<DecisionTreeEdge> getDownEdges() {
        return downEdges;
    }

    public DecisionTreeEdge getUpEdge() {
        return upEdge;
    }
}
