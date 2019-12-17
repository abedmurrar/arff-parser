package edu.birzeit.comp4388;

import java.util.ArrayList;
import java.util.Map;

import edu.birzeit.comp4388.ARFF.Attributes.Attribute;

public class DecisionTreeNode {
    // Because children are distributed depending on the value
    private Map<String, DecisionTreeNode> children;
    private final DecisionTreeNode parent;
    private final Attribute attribute;

    public DecisionTreeNode(DecisionTreeNode parent, Attribute attribute) {
        this.parent = parent;
        this.attribute = attribute;
    }

    public void addChild(String value, DecisionTreeNode child) {
        children.put(value, child);
    }

    /**
     * @return the parent
     */
    public DecisionTreeNode getParent() {
        return parent;
    }

    /**
     * @return the attribute
     */
    public Attribute getAttribute() {
        return attribute;
    }

    /**
     * @return the children
     */
    public ArrayList<DecisionTreeNode> getChildren() {
        return new ArrayList<DecisionTreeNode>(children.values());
    }

    public DecisionTreeNode getChild(String value) {
        return children.get(value);
    }

}
