package edu.birzeit.comp4388.ARFF.Attributes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateAttribute extends Attribute {

    private String format;
    private final String GENERAL_DATE_REGEX = "(\\b(0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](\\d{4}|\\d{2})\\b)|(\\b(0?[1-9]|1[0-2])[^\\w\\d\\r\\n:](0?[1-9]|[12]\\d|30|31)[^\\w\\d\\r\\n:](\\d{4}|\\d{2})\\b)";

    public DateAttribute(String name, String format) {
        super(name);
        this.format = format;
    }

    @Override
    public boolean validate(String value) {
        if (this.format != null) {
            DateFormat dateFormat = new SimpleDateFormat(this.format);
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(value);
            } catch (ParseException e) {
                return false;
            }
            return true;
        }
        return value
                .trim()
                .matches(GENERAL_DATE_REGEX);

    }

    @Override
    public String getDataType() {
        return "date";
    }

    @Override
    public String toString() {
        return "@ATTRIBUTE " + name + " DATE" + (format != null ? " " + format : "") + "\n";
    }
}
