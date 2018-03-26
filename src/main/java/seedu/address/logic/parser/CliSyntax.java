package seedu.address.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_PHONE = new Prefix("p/");
    public static final Prefix PREFIX_EMAIL = new Prefix("e/");
    public static final Prefix PREFIX_ADDRESS = new Prefix("a/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");

    public static final Prefix PREFIX_TITLE = new Prefix("et/");
    public static final Prefix PREFIX_DESCRIPTION = new Prefix("ed/");
    public static final Prefix PREFIX_LOCATION = new Prefix("el/");
    public static final Prefix PREFIX_DATETIME = new Prefix("edt/");
    public static final Prefix PREFIX_COMMAND = new Prefix("c/");
    public static final Prefix PREFIX_ALIAS = new Prefix("al/");

}
