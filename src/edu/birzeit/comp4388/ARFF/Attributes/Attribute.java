package edu.birzeit.comp4388.ARFF.Attributes;


public abstract class Attribute {
    String name;

    Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public abstract boolean validate(String value);

    public abstract String getDataType();

    public static boolean isNumericAttribute(String dataType) {
        return dataType.trim().toLowerCase().matches("^(numeric|integer|real)$");
    }

    public static boolean isStringAttribute(String dataType) {
        return dataType.trim().toLowerCase().matches("^(string)$");
    }

    public static boolean isDateAttribute(String dataType) {
        return dataType.trim().toLowerCase().matches("^(date).*?");
    }

    public static boolean isNominalAttribute(String dataType) {
        return dataType.trim().matches("^[{].*[}]$");
    }

    //    @Override
    public boolean equals(Attribute other) {
        return this.name.equals(other.name);
    }

}
