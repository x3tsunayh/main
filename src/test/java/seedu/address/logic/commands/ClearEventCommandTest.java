package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.getTypicalEventBook;

import org.junit.Test;

import seedu.address.logic.CommandHistory;
import seedu.address.logic.UndoRedoStack;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearEventCommandTest {

    @Test
    public void execute_emptyEventBook_success() {
        Model model = new ModelManager();
        assertCommandSuccess(prepareCommand(model), model, ClearEventCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_nonEmptyEventBook_success() {
        Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        assertCommandSuccess(prepareCommand(model), model, ClearEventCommand.MESSAGE_SUCCESS, model);
    }

    /**
     * Generates a new {@code ClearCommand} which upon execution, clears the contents in {@code model}.
     */
    private ClearEventCommand prepareCommand(Model model) {
        ClearEventCommand command = new ClearEventCommand();
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }
}
