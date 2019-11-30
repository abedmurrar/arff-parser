package edu.birzeit.comp4388.ARFF;

import edu.birzeit.comp4388.ARFF.Attributes.Attribute;
import edu.birzeit.comp4388.Errors.Attributes.DuplicateAttributeNameErrorException;

import java.util.ArrayList;

public class Relation {
    private String name;
    private ArrayList<Attribute> attributes;
    private ArrayList<DataRow> data;

    public Relation(String name) {
        this.name = name;
        this.attributes = new ArrayList<>();
        this.data = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<Attribute> getAttributes() {
        return attributes;
    }

    public ArrayList<DataRow> getData() {
        return data;
    }

    public void addAttribute(Attribute attribute) throws DuplicateAttributeNameErrorException {
        if (this.attributes.contains(attribute)) {
            throw new DuplicateAttributeNameErrorException("Attribute " + attribute.getName() + " already exists in relation " + this.getName());
        }
        this.attributes.add(attribute);
    }

    public void addDataRow(DataRow dataRow) {
        this.data.add(dataRow);
    }

    @Override
    public String toString() {
        return this.name + "\n";
    }
}
