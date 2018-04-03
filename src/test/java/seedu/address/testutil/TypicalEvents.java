package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.EventBook;
import seedu.address.model.event.ReadOnlyEvent;

//@@author x3tsunayh

/**
 * A utility class containing a list of {@code Event} objects to be used in tests.
 */
public class TypicalEvents {

    public static final ReadOnlyEvent CNY = new EventBuilder().withTitle("CNY")
            .withDescription("CNY Celebration at FOS").withLocation("NUS S16 Level 3")
            .withDatetime("15-02-2018 1000").build();
    public static final ReadOnlyEvent CHRISTMAS = new EventBuilder().withTitle("Christmas")
            .withDescription("Christmas Party at SOC").withLocation("NUS COM1")
            .withDatetime("24-12-2018 1830").build();
    public static final ReadOnlyEvent MOVIE = new EventBuilder().withTitle("Movie Outing")
            .withDescription("Black Panther Movie").withLocation("Suntec GV")
            .withDatetime("21-04-2018 1500").build();
    public static final ReadOnlyEvent REUNION = new EventBuilder().withTitle("Class Reunion")
            .withDescription("With Secondary School Classmates").withLocation("Samantha's House")
            .withDatetime("06-05-2018 1730").build();

    private TypicalEvents() {
        // prevents instantiation
    }

    /**
     * Returns an {@code EventBook} with all the typical events.
     */
    public static EventBook getTypicalEventBook() {
        EventBook eb = new EventBook();
        for (ReadOnlyEvent event : getTypicalEvents()) {
            try {
                eb.addEvent(event);
            } catch (CommandException e) {
                assert false : "Invalid Command";
            }
        }
        return eb;
    }

    public static List<ReadOnlyEvent> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(CNY, CHRISTMAS, MOVIE, REUNION));
    }
}
