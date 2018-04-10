package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DATETIME;

import java.time.YearMonth;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.JumpToCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.Datetime;

public class JumpToCommandParser implements Parser<JumpToCommand>{

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns an DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public JumpToCommand parse(String args) throws ParseException {
        try {
            YearMonth yearMonth = ParserUtil.parseYearmonth(args);
            return new JumpToCommand(yearMonth);
        } catch (IllegalValueException ive) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, JumpToCommand.MESSAGE_USAGE));
        }
    }
}
