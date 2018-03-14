package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteByNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteByNameCommandParser implements Parser<DeleteByNameCommand> {

    private Name inputName;
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteByNameCommand
     * and returns an DeleteByNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteByNameCommand parse(String args)  {
        String trimmedInput = args.trim();
        inputName = new Name(trimmedInput);
        return new DeleteByNameCommand(inputName);
    }

}
