package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILEPATH;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AddPictureCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddPictureCommand object
 */
public class AddPictureCommandParser implements Parser<AddPictureCommand> {

    private String path;
    private Index index;
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteByNameCommand
     * and returns an DeleteByNameCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPictureCommand parse(String args) throws ParseException {

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILEPATH);

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPictureCommand.MESSAGE_USAGE));
        }

        try {
            path = ParserUtil.parseFilePath(argMultimap.getValue(PREFIX_FILEPATH).get());
        } catch (IllegalValueException ive) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPictureCommand.MESSAGE_USAGE));
        }

        return new AddPictureCommand(index, path);
    }

}
