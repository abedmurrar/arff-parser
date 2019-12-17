package edu.birzeit.comp4388.Errors.ARFF;

/**
 * ARFFDataTypeErrorException is raised when an unknown (unexpected) datatype
 * in @data section is found
 *
 * @author Abed Al Rahman Murrar
 */
public class ARFFDataTypeErrorException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2906936429869467350L;

    public ARFFDataTypeErrorException(String message) {
        super(message);
    }
}
