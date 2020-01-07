package edu.birzeit.comp4388;

import edu.birzeit.comp4388.ARFF.DataSet;

class Main {

    public static void main(String[] args) throws Exception {
        // try{
        String file = "src/doc.arff";
        DataSet dataSet = new DataSet(file);
        DecisionTree tree = new DecisionTree(dataSet);
        // tree.findRoot(null, null);
        System.out.println(tree);

    }

}