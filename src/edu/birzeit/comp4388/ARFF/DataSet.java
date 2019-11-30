package edu.birzeit.comp4388.ARFF;

import edu.birzeit.comp4388.ARFF.Attributes.*;
import edu.birzeit.comp4388.Errors.ARFF.*;
import edu.birzeit.comp4388.Errors.Attributes.NominalAttributeErrorException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.stream.Collectors;

public class DataSet {

    private String fileName;
    private Relation relation = null;


    public DataSet(String fileName) throws IOException {
        this.fileName = fileName;
        read();
    }

    public Relation getRelation() {
        return relation;
    }

    private void read() throws IOException {
        String currentLine = null;
        boolean isDataLines = false;
        int lineCounter = 0;

        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        try {
            while ((currentLine = reader.readLine()) != null) {
                /**
                 * For comments in middle of line
                 */
                currentLine = currentLine.replaceAll("%.*", "");
                lineCounter++;
                /**
                 * General for all file, if a line is a comment or empty, ignore it
                 */
                if (isComment(currentLine) || isEmpty(currentLine)) {
                    continue;
                }

                /**
                 * First line to read must (or is expected to) be the relation name
                 */
                if (relation == null) {
                    if (isRelation(currentLine)) {
                        relation = new Relation(getRelationName(currentLine));
                    } else {
                        throw new ARFFFileFormatErrorException("Relation does not exist");
                    }
                }

                /**
                 * Collect Attributes
                 */

                else if (isAttribute(currentLine)) {
                    relation.addAttribute(getAttribute(currentLine));
                }

                /**
                 * Collect Data
                 */

                else if (isData(currentLine)) {
                    isDataLines = true;
                    continue;
                } else if (isDataLines) {
                    relation.addDataRow(getDataRow(currentLine));
                } else {
                    throw new ARFFFileFormatErrorException("Unexpected line at line " + lineCounter + " " + currentLine);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        reader.close();
    }

    private boolean isComment(String line) {
        return line.trim().startsWith("%");
    }

    private boolean isEmpty(String line) {
        return line.trim().isEmpty();
    }

    private boolean isRelation(String line) {
        return line.trim().toLowerCase().matches("^(@relation)\\s+.*$");
    }

    private boolean isAttribute(String line) {
        return line.trim().toLowerCase().matches("^(@attribute\\s+.*)$");
    }

    private boolean isData(String line) {
        return line.trim().toLowerCase().matches("^(@data)$");
    }

    private String getRelationName(String line) throws ARFFRelationNameErrorException {
        String[] tokens = line.trim().split("\\s+", 2);
        if (tokens.length < 2) {
            throw new ARFFRelationNameErrorException("Relation line error");
        }
        return tokens[1];
    }

    private Attribute getAttribute(String line) throws ARFFFileFormatErrorException, ARFFDataTypeErrorException, NominalAttributeErrorException {
        String[] tokens = line.trim().split("\\s+", 3);
        Attribute attribute = null;
        if (tokens.length < 3) {
            throw new ARFFFileFormatErrorException("Attribute line error, " + line);
        }
        String attributeName = tokens[1];
        String attributeDataType = tokens[2];

        if (Attribute.isNumericAttribute(attributeDataType)) {
            /**
             * @ATTRIBUTE name NUMERIC
             */
            attribute = new NumericAttribute(attributeName);
        } else if (Attribute.isStringAttribute(attributeDataType)) {
            /**
             * @ATTRIBUTE name STRING
             */
            attribute = new StringAttribute(attributeName);
        } else if (Attribute.isDateAttribute(attributeDataType)) {
            /**
             * @ATTRIBUTE name DATE [?FORMAT]
             */
            String[] dateTokens = attributeDataType.split("\\s+", 2);
            String dateFormat = null;
            if (dateTokens.length > 1) {
                dateFormat = dateTokens[1];
            }
            attribute = new DateAttribute(dateTokens[0], dateFormat);
        } else if (Attribute.isNominalAttribute(attributeDataType)) {
            /**
             * @ATTRIBUTE name {x,y,z}
             * Get rid of curly brackets then split by comma
             */
            String[] classesTokens = attributeDataType
                    .trim()
                    .substring(1, attributeDataType.trim().length() - 1)
                    .split(",");

            /**
             * Clean values
             */
            for (int i = 0; i < classesTokens.length; i++) {
                classesTokens[i] = classesTokens[i].trim();
                if (classesTokens[i].isEmpty()) {
                    throw new NominalAttributeErrorException("Empty Nominal Data Type at attribute: " + attributeName);
                }
            }
            attribute = new NominalAttribute(attributeName, classesTokens);
        } else {
            throw new ARFFDataTypeErrorException("Unknown Data Type, " + attributeDataType);
        }
        return attribute;
    }

    private DataRow getDataRow(String line) throws ARFFRedundantValuesErrorException, ARFFDataValidationErrorException {
        String[] values = line.trim().split(",");
        if (values.length != relation.getAttributes().size()) {
            throw new ARFFRedundantValuesErrorException("Number of values is not same as attributes at line: " + line);
        }
        DataRow dataRow = new DataRow();

        for (int i = 0, length = values.length; i < length; i++) {
            Attribute _attribute = relation.getAttributes().get(i);
            /**
             * If it is a missing value that is "?" then present it as null
             */
            if (values[i].trim().equals("?"))
                dataRow.addProperty(_attribute.getName(), null);
            /**
             * If it is not a missing value, then validate it
             */
            else if (_attribute.validate(values[i]))
                dataRow.addProperty(_attribute.getName(), values[i].trim());
            /**
             * If it fails validation then raise an exception
             */
            else
                throw new ARFFDataValidationErrorException("Data Validation Error on attribute " + _attribute.getName() + " of type " + _attribute.getDataType() + ", value: " + values[i]);
        }

        return dataRow;
    }

    @Override
    public String toString() {
        return "@RELATION " + relation.getName() + "\n" +
                relation.getAttributes().stream().map(Object::toString).collect(Collectors.joining("\n")) + "\n" +
                "@DATA" + "\n" +
                relation.getData().stream().map(Object::toString).collect(Collectors.joining("\n"));
    }
}
