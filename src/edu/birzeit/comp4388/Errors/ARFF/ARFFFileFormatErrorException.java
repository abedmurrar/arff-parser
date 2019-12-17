package edu.birzeit.comp4388.Errors.ARFF;

/**
 * ARFFFileFormatErrorException is raised when an unknown (unexpected) token or
 * line is found when parsing an ARFF File
 *
 * @author Abed Al Rahman Murrar
 */
public class ARFFFileFormatErrorException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 6106614103976147230L;

    public ARFFFileFormatErrorException(String message) {
        super(message);
    }
}
