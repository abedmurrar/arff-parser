package edu.birzeit.comp4388.ARFF.Attributes;

public abstract class Attribute {
    String name;
    private float entropy;
    private float impurity;
    private boolean selected = false;

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

    /**
     * @param entropy the entropy to set
     */
    public void setEntropy(float entropy) {
        this.entropy = entropy;
    }

    /**
     * @param selected the selected to set
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    /**
     * @return the selected
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @return the entropy
     */
    public float getEntropy() {
        return entropy;
    }

    /**
     * @param impurity the impurity to set
     */
    public void setImpurity(float impurity) {
        this.impurity = impurity;
    }

    /**
     * @return the impurity
     */
    public float getImpurity() {
        return impurity;
    }

    // @Override
    public boolean equals(Attribute other) {
        return this.name.equals(other.name);
    }

}
