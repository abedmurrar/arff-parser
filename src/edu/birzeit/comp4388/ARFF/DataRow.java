package edu.birzeit.comp4388.ARFF;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DataRow {
    private Map<String, Object> properties;

    public DataRow() {
        this.properties = new HashMap<>();
    }

    public void addProperty(String propertyName, Object propertyValue) {
        this.properties.put(propertyName, propertyValue);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    @Override
    public String toString() {
        return properties.entrySet().stream().map(Object::toString).collect(Collectors.joining(","));
    }
}
