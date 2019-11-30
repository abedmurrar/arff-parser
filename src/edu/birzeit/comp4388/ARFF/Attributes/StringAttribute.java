package edu.birzeit.comp4388.ARFF.Attributes;

public class StringAttribute extends Attribute {


    public StringAttribute(String name) {
        super(name);
    }

    public String getDataType() {
        return "string";
    }

    @Override
    public boolean validate(String value) {
        return !value.isEmpty();
    }

    @Override
    public String toString() {
        return "@ATTRIBUTE " + name + " string";
    }
}
