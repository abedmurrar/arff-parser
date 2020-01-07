package edu.birzeit.comp4388;

import edu.birzeit.comp4388.ARFF.DataRow;
import edu.birzeit.comp4388.ARFF.DataSet;
import edu.birzeit.comp4388.ARFF.Attributes.Attribute;
import edu.birzeit.comp4388.ARFF.Attributes.NominalAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DecisionTree {

    private final DecisionTreeNode root;
    private DataSet training /* , validation, test */;
    private Attribute _classAttribute;

    public DecisionTree(DataSet training/* , DataSet validation, DataSet test */) throws Exception {
        this.training = training;
        this._classAttribute = training.getRelation().getAttributes()
                .get(training.getRelation().getAttributes().size() - 1);
        if (!(_classAttribute instanceof NominalAttribute)) {
            throw new Exception("Target/Last Attribute is not of nominal data type");
        }

        this.root = buildTree(new ArrayList<Attribute>(), training.getRelation().getData(), null);
        /*
         * this.validation = validation; this.test = test;
         */
    }

    private float findSetEntropy(ArrayList<DataRow> data) {
        if (data.size() == 0) {
            return 0;
        }
        List<String> classes = ((NominalAttribute) this._classAttribute).getClasses();
        float propabilities[] = new float[classes.size()];
        for (int j = 0, len = classes.size(); j < len; j++) {
            String class_val = classes.get(j);
            propabilities[j] = (float) data.stream()
                    .filter(row -> row.getProperties().get(this._classAttribute).equals(class_val)).count()
                    / (float) data.size();
            if (propabilities[j] == 1) {
                return 0;
            }
        }
        return entropy(propabilities);
    }

    public DecisionTreeNode buildTree(ArrayList<Attribute> notAllowedAttributes, ArrayList<DataRow> data,
            DecisionTreeNode parent) {
        // stop criteria
        if (data.isEmpty()) {
            return null;
        }
        if (parent != null) {
            notAllowedAttributes.add(parent.getAttribute());
        }

        int rowsCount = data.size();
        Map<String, Long> countPerValue = new HashMap<>();
        ((NominalAttribute) this._classAttribute).getClasses().stream().forEach(_class -> {
            countPerValue.put(_class,
                    data.stream().filter(row -> row.isAttributeValueEqual(this._classAttribute, _class)).count());
        });

        float dataSetEntropy = findSetEntropy(data);
        DecisionTreeNode node;

        if (dataSetEntropy == 0) {
            node = new DecisionTreeNode(parent, null);
            node.setAnswer((String) data.get(0).getProperties().get(this._classAttribute));
            return node;
        }
        Attribute highestGainAttrinAttribute = findHighestGainAttribute(dataSetEntropy, data, notAllowedAttributes);
        node = new DecisionTreeNode(parent, highestGainAttrinAttribute);
        // get highest gain attribute
        // for each value of it recursively call the same function
        // filter the data for each recursive call
        ((NominalAttribute) highestGainAttrinAttribute).getClasses().stream().forEach(_class -> {
            node.addChild(_class,
                    buildTree((ArrayList<Attribute>) notAllowedAttributes.clone(),
                            data.stream().filter(row -> row.isAttributeValueEqual(highestGainAttrinAttribute, _class))
                                    .collect(Collectors.toCollection(ArrayList::new)),
                            node));
        });
        return node;
    }

    private float entropy(float probabilities[]) {
        float sum = 0;
        for (float probability : probabilities) {
            if (probability == 0) {
                continue;
            }
            sum -= (probability) * (Math.log10(probability) / Math.log10(2));
        }
        return sum;
    }

    private float gain(Float entropy, ArrayList<Float> probabilityByValueEntropy) {
        float _gain = 0;
        for (Float entry : probabilityByValueEntropy) {
            _gain += entry;
        }
        return entropy.floatValue() - _gain;
    }

    private Attribute findHighestGainAttribute(float setEntropy, ArrayList<DataRow> data,
            ArrayList<Attribute> notAllowedAttributes) {
        var wrapper = new Object() {
            float maxGain = Float.NEGATIVE_INFINITY;
            Attribute attribute = null;
        };
        ArrayList<Attribute> no_intersection = training.getRelation().getAttributes().stream()
                .filter(att -> !notAllowedAttributes.contains(att)).collect(Collectors.toCollection(ArrayList::new));
        no_intersection.forEach(_attribute -> {
            if (_attribute == this._classAttribute) {
                return;
            }
            int rowsCount = data.size();
            ArrayList<Float> probabilityPerValueEntropy = new ArrayList<>();
            ((NominalAttribute) _attribute).getClasses().stream().forEach(_class -> {
                probabilityPerValueEntropy
                        .add((float) data.stream().filter(row -> row.isAttributeValueEqual(_attribute, _class)).count()
                                / (float) rowsCount
                                * findSetEntropy(
                                        data.stream().filter(row -> row.isAttributeValueEqual(_attribute, _class))
                                                .collect(Collectors.toCollection(ArrayList::new))));
            });
            float _gain = gain(setEntropy, probabilityPerValueEntropy);
            if (_gain > wrapper.maxGain) {
                wrapper.maxGain = _gain;
                wrapper.attribute = _attribute;
            }
        });
        return wrapper.attribute;
    }

}
