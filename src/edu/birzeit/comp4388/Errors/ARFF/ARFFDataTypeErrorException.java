package edu.birzeit.comp4388.Errors.ARFF;

/**
 * ARFFDataTypeErrorException is raised when an unknown (unexpected) datatype in @data section
 * is found
 *
 * @author Abed Al Rahman Murrar
 */
public class ARFFDataTypeErrorException extends Exception {

    public ARFFDataTypeErrorException(String message) {
        super(message);
    }
}
