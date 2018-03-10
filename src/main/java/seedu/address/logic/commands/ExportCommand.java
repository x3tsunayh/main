package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Exports the address book to a user-defined location {@code filePath}
 */
public class ExportCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "export";
    public static final String COMMAND_ALIAS = "exp";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exports current data into defined file path. "
            + "Parameters: FILEPATH (must end with an extension of .xml)\n"
            + "Example: " + COMMAND_WORD + " "
            + " C:\\Users\\John Doe\\Documents\\addressbook.xml\n";

    public static final String MESSAGE_EXPORT_SUCCESS = "Addressbook data exported to: %1$s";
    public static final String MESSAGE_NOT_XML_FILE = "Filepath does not lead to an XML file.";
    public static final String MESSAGE_ERROR = "Addressbook data not exported successfully.";

    private final String filePath;

    /**
     * Creates an ExportCommand to add the specified {@code String}
     */
    public ExportCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public CommandResult executeUndoableCommand() throws CommandException {
        model.exportAddressBook(filePath);
        return new CommandResult(String.format(MESSAGE_EXPORT_SUCCESS, filePath));
    }


}

