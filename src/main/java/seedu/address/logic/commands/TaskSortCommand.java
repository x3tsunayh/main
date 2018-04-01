package seedu.address.logic.commands;

/**
 * Sorts based on priority from high to low importance and lists all tasks in the address book to the user.
 */
public class TaskSortCommand extends UndoableCommand {

    public static final String COMMAND_WORD = "task-sort";
    public static final String COMMAND_ALIAS = "ts";
    public static final String MESSAGE_SUCCESS =
            "Listed all tasks sorted based on priority from high to low importance";

    @Override
    public CommandResult executeUndoableCommand() {
        model.sortTasksByPriority();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
