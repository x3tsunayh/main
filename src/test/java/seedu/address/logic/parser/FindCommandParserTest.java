package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.tag.TagContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "n/Alice Bob", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/Alice \n \t Bob  \t", expectedFindNameCommand);

        FindCommand expectedFindTagCommand =
                new FindCommand(new TagContainsKeywordsPredicate(Arrays.asList("owesMoney", "friends")));

        assertParseSuccess(parser, "t/owesMoney friends", expectedFindTagCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "t/owesMoney\t friends  \t", expectedFindTagCommand);
    }

}
