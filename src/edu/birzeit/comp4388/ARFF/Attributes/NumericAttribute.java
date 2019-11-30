package edu.birzeit.comp4388.ARFF.Attributes;

public class NumericAttribute extends Attribute {

    public NumericAttribute(String name) {
        super(name);
    }

    @Override
    public boolean validate(String value) {
        return value.trim().matches("^(\\d+(\\.\\d+)?)$");
    }

    @Override
    public String getDataType() {
        return "numeric";
    }

    @Override
    public String toString() {
        return "@ATTRIBUTE " + name + " numeric";
    }
}
