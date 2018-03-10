package seedu.address.commons.exceptions;

public class InvalidFileException extends Exception {
    /**
     * Returns InvalidFileException if {@code filePath} is not a valid XML file
     */
    public InvalidFileException() {
        super();
    }
    public InvalidFileException(String message) {
        super(message);
    }

}

