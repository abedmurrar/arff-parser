package edu.birzeit.comp4388;

public class DecisionTreeEdge {
    private String value;
    private DecisionTreeNode parent;
    private DecisionTreeNode child;

    public DecisionTreeEdge(DecisionTreeNode parent, DecisionTreeNode child, String value) {
        this.parent = parent;
        this.child = child;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public DecisionTreeNode getChild() {
        return child;
    }

    public DecisionTreeNode getParent() {
        return parent;
    }
}
