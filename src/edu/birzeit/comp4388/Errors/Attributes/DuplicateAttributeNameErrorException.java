package edu.birzeit.comp4388.Errors.Attributes;

public class DuplicateAttributeNameErrorException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -5096612283211234701L;

    public DuplicateAttributeNameErrorException(String message) {
        super(message);
    }
}
