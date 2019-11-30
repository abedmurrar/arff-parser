package edu.birzeit.comp4388;

import edu.birzeit.comp4388.ARFF.Attributes.Attribute;
import edu.birzeit.comp4388.ARFF.DataRow;
import edu.birzeit.comp4388.ARFF.DataSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DecisionTree {

    private DecisionTreeNode root;
    private DataSet training /*, validation, test*/;

    public DecisionTree() {
    }

    public DecisionTree(DecisionTreeNode root, DataSet training/*, DataSet validation, DataSet test*/) {
        this.root = root;
        this.training = training;
        /*
         this.validation = validation;
         this.test = test;
         */
    }

    private void findRoot() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0, length = training.getRelation().getData().size(); i < length; i++) {
            DataRow dataRow = training.getRelation().getData().get(i);
            dataRow.getProperties();
        }
    }

    private float entropy(ArrayList<String> properties) {
        float entropy = 0;
//        for
        return entropy;
    }

    private float informationGain() {
        float inGain = 0;
        return inGain;
    }
}
