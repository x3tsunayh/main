package seedu.address.logic.commands;

//@@author CYX28
/**
 * Sorts tasks by priority in decreasing order of importance (i.e. high > medium > low) and
 * lists all tasks in the address book to the user.
 */
public class TaskSortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "task-sort";
    public static final String COMMAND_ALIAS = "ts";
    public static final String MESSAGE_SUCCESS =
            "Listed all tasks sorted by priority from high to low importance";

    @Override
    public CommandResult executeUndoableCommand() {
        model.sortTasksByPriority();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
