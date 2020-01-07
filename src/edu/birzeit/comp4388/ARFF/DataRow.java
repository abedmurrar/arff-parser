package edu.birzeit.comp4388.ARFF;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.birzeit.comp4388.ARFF.Attributes.Attribute;

public class DataRow {
    private Map<Attribute, Object> properties;

    public DataRow() {
        this.properties = new HashMap<>();
    }

    public void addProperty(Attribute property, Object propertyValue) {
        this.properties.put(property, propertyValue);
    }

    public boolean isAttributeValueEqual(Attribute attribute, Object value) {
        if (this.getProperties().get(attribute) == null)
            return false;
        return this.getProperties().get(attribute).equals(value);
    }

    public Map<Attribute, Object> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return properties.entrySet().stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
