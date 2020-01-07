package edu.birzeit.comp4388;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import edu.birzeit.comp4388.ARFF.Attributes.Attribute;

public class DecisionTreeNode {
    // Because children are distributed depending on the value
    private Map<String, DecisionTreeNode> children;
    private final DecisionTreeNode parent;
    private final Attribute attribute;
    private String answer = null;

    public DecisionTreeNode(DecisionTreeNode parent, Attribute attribute) {
        this.parent = parent;
        this.attribute = attribute;
        this.children = new HashMap<>();
    }

    public void addChild(String value, DecisionTreeNode child) {
        children.put(value, child);
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public DecisionTreeNode getParent() {
        return parent;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public ArrayList<DecisionTreeNode> getChildren() {
        return new ArrayList<DecisionTreeNode>(children.values());
    }

    public DecisionTreeNode getChild(String value) {
        return children.get(value);
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isLeaf() {
        return children.size() == 0;
    }

    public boolean hasAnswer() {
        return answer != null;
    }

}
