package seedu.address.logic.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import java.util.Arrays;

import org.junit.Test;

import seedu.address.commons.core.Config;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.CalendarEvent;
import seedu.address.model.event.TitleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code DeleteEventCommand}.
 */
public class DeleteEventCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        CalendarEvent eventToDelete = model.getFilteredEventList().get(Index.fromOneBased(1).getZeroBased());
        DeleteEventCommand deleteEventCommand = prepareCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        ModelManager expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        CalendarEvent expectedEventToDelete = expectedModel.getFilteredEventList()
                .get(Index.fromOneBased(1).getZeroBased());
        expectedModel.deleteEvent(expectedEventToDelete);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        DeleteEventCommand deleteEventCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        CalendarEvent event = model.getEventBook().getEventList().get(0);
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(event.getTitle())));
        // ensures only one event is in the filtered event list
        assert model.getFilteredEventList().size() == 1;

        CalendarEvent eventToDelete = model.getFilteredEventList().get(Index.fromOneBased(1).getZeroBased());
        DeleteEventCommand deleteEventCommand = prepareCommand(Index.fromOneBased(1));

        String expectedMessage = String.format(DeleteEventCommand.MESSAGE_DELETE_EVENT_SUCCESS, eventToDelete);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        CalendarEvent expectedEventToDelete = expectedModel.getFilteredEventList()
                .get(Index.fromOneBased(1).getZeroBased());
        expectedModel.deleteEvent(expectedEventToDelete);
        showNoEvent(expectedModel);

        assertCommandSuccess(deleteEventCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_failure() {
        CalendarEvent event = model.getEventBook().getEventList().get(0);
        model.updateFilteredEventList(new TitleContainsKeywordsPredicate(Arrays.asList(event.getTitle())));
        // ensures only one event is in the filtered event list
        assert model.getFilteredEventList().size() == 1;

        Index outOfBoundIndex = Index.fromOneBased(2);
        // ensures outOfBoundIndex is still within size of Event Book
        assertTrue(outOfBoundIndex.getZeroBased() < model.getEventBook().getEventList().size());

        DeleteEventCommand deleteEventCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(deleteEventCommand, model, Messages.MESSAGE_INVALID_EVENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteEventCommand deleteFirstEventCommand = new DeleteEventCommand(Index.fromOneBased(1));
        DeleteEventCommand deleteSecondEventCommand = new DeleteEventCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommand));

        // same values -> returns true
        DeleteEventCommand deleteFirstEventCommandCopy = new DeleteEventCommand(Index.fromOneBased(1));
        assertTrue(deleteFirstEventCommand.equals(deleteFirstEventCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstEventCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstEventCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstEventCommand.equals(deleteSecondEventCommand));
    }

    /**
     * Returns a {@code DeleteCommand} with the parameter {@code index}.
     */
    private DeleteEventCommand prepareCommand(Index index) {
        DeleteEventCommand deleteEventCommand = new DeleteEventCommand(index);
        deleteEventCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return deleteEventCommand;
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoEvent(Model model) {
        model.updateFilteredEventList(p -> false);

        assert model.getFilteredEventList().isEmpty();
    }
}