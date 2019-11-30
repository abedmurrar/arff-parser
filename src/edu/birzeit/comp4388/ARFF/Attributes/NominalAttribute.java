package edu.birzeit.comp4388.ARFF.Attributes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NominalAttribute extends Attribute {

    private List<String> classes;

    public NominalAttribute(String name, String... values) {
        super(name);
        this.classes = Arrays.asList(values);
    }

    public List<String> getClasses() {
        return classes;
    }
    
    @Override
    public boolean validate(String value) {
        return classes.contains(value.trim());
    }

    @Override
    public String getDataType() {
        return "{" + classes.stream().map(Object::toString).collect(Collectors.joining(",")) + "}";
    }

    @Override
    public String toString() {
        return "@ATTRIBUTE " + name + " {" + classes.stream().map(Object::toString).collect(Collectors.joining(",")) + "}\n";
    }
}
