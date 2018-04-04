# CYX28
###### \java\guitests\guihandles\TaskCardHandle.java
``` java
/**
 * Provides a handle to a task card in the task list panel.
 */
public class TaskCardHandle extends NodeHandle<Node> {
    private static final String TASK_ID_FIELD_ID = "#id";
    private static final String TASK_NAME_FIELD_ID = "#name";
    private static final String TASK_PRIORITY_FIELD_ID = "#priority";
    private static final String TASK_DESCRIPTION_FIELD_ID = "#description";
    private static final String TASK_DUE_DATE_FIELD_ID = "#dueDate";
    private static final String TASK_STATUS_FIELD_ID = "#status";
    private static final String TASK_CATEGORIES_FIELD_ID = "#categories";

    private final Label idLabel;
    private final Label nameLabel;
    private final Label priorityLabel;
    private final Label descriptionLabel;
    private final Label dueDateLabel;
    private final Label statusLabel;
    private final List<Label> categoriesLabels;

    public TaskCardHandle(Node cardNode) {
        super(cardNode);

        this.idLabel = getChildNode(TASK_ID_FIELD_ID);
        this.nameLabel = getChildNode(TASK_NAME_FIELD_ID);
        this.priorityLabel = getChildNode(TASK_PRIORITY_FIELD_ID);
        this.descriptionLabel = getChildNode(TASK_DESCRIPTION_FIELD_ID);
        this.dueDateLabel = getChildNode(TASK_DUE_DATE_FIELD_ID);
        this.statusLabel = getChildNode(TASK_STATUS_FIELD_ID);

        Region taskCategoriesContainer = getChildNode(TASK_CATEGORIES_FIELD_ID);
        this.categoriesLabels = taskCategoriesContainer
                .getChildrenUnmodifiable()
                .stream()
                .map(Label.class::cast)
                .collect(Collectors.toList());
    }

    public String getTaskId() {
        return idLabel.getText();
    }

    public String getTaskName() {
        return nameLabel.getText();
    }

    public String getTaskPriority() {
        return priorityLabel.getText();
    }

    public String getTaskDescription() {
        return descriptionLabel.getText();
    }

    public String getTaskDueDate() {
        return dueDateLabel.getText();
    }

    public String getTaskStatus() {
        return statusLabel.getText();
    }

    public List<String> getTaskCategories() {
        return categoriesLabels
                .stream()
                .map(Label::getText)
                .collect(Collectors.toList());
    }
}
```
###### \java\guitests\guihandles\TaskListPanelHandle.java
``` java
/**
 * Provides a handle for {@code TaskListPanel} containing the list of {@code TaskCard}.
 */
public class TaskListPanelHandle extends NodeHandle<ListView<TaskCard>> {
    public static final String TASK_LIST_VIEW_ID = "#taskListView";

    public TaskListPanelHandle(ListView<TaskCard> taskListPanelNode) {
        super(taskListPanelNode);
    }

    /**
     * Returns the task card handle of a task associated with the {@code index} in the list.
     */
    public TaskCardHandle getTaskCardHandle(int index) {
        return getTaskCardHandle(getRootNode().getItems().get(index).task);
    }

    /**
     * Returns the {@code TaskCardHandle} of the specified {@code task} in the list.
     */
    public TaskCardHandle getTaskCardHandle(Task task) {
        Optional<TaskCardHandle> handle = getRootNode().getItems().stream()
                .filter(card -> card.task.equals(task))
                .map(card -> new TaskCardHandle(card.getRoot()))
                .findFirst();
        return handle.orElseThrow(() -> new IllegalArgumentException("Task does not exist."));
    }
}
```
###### \java\seedu\address\commons\util\XmlUtilTest.java
``` java
    @Test
    public void xmlAdaptedTaskFromFile_fileWithMissingTaskField_validResult() throws Exception {
        XmlAdaptedTask actualTask = XmlUtil.getDataFromFile(
                MISSING_TASK_FIELD_FILE, XmlAdaptedTaskWithRootElement.class);
        XmlAdaptedTask expectedTask = new XmlAdaptedTask(null, VALID_TASK_PRIORITY, VALID_TASK_DESCRIPTION,
                VALID_TASK_DUE_DATE, VALID_TASK_STATUS, VALID_TASK_CATEGORIES);
        assertEquals(expectedTask, actualTask);
    }

```
###### \java\seedu\address\commons\util\XmlUtilTest.java
``` java
    @Test
    public void xmlAdaptedTaskFromFile_fileWithInvalidTaskField_validResult() throws Exception {
        XmlAdaptedTask actualTask = XmlUtil.getDataFromFile(
                INVALID_TASK_FIELD_FILE, XmlAdaptedTaskWithRootElement.class);
        XmlAdaptedTask expectedTask = new XmlAdaptedTask(VALID_TASK_NAME, VALID_TASK_PRIORITY, VALID_TASK_DESCRIPTION,
                INVALID_TASK_DUE_DATE, VALID_TASK_STATUS, VALID_TASK_CATEGORIES);
        assertEquals(expectedTask, actualTask);
    }

```
###### \java\seedu\address\commons\util\XmlUtilTest.java
``` java
    @Test
    public void xmlAdaptedTaskFromFile_fileWithValidTask_validResult() throws Exception {
        XmlAdaptedTask actualTask = XmlUtil.getDataFromFile(
                VALID_TASK_FILE, XmlAdaptedTaskWithRootElement.class);
        XmlAdaptedTask expectedTask = new XmlAdaptedTask(VALID_TASK_NAME, VALID_TASK_PRIORITY, VALID_TASK_DESCRIPTION,
                VALID_TASK_DUE_DATE, VALID_TASK_STATUS, VALID_TASK_CATEGORIES);
        assertEquals(expectedTask, actualTask);
    }

```
###### \java\seedu\address\logic\commands\CommandTestUtil.java
``` java
    public static final String VALID_TASK_NAME_TASKFIRST = "First task";
    public static final String VALID_TASK_NAME_TASKSECOND = "Second task";
    public static final String VALID_TASK_PRIORITY_TASKFIRST = "high";
    public static final String VALID_TASK_PRIORITY_TASKSECOND = "low";
    public static final String VALID_TASK_DESCRIPTION_TASKFIRST = "Create a new app";
    public static final String VALID_TASK_DESCRIPTION_TASKSECOND = "Discuss outing details";
    public static final String VALID_TASK_DUE_DATE_TASKFIRST = "2018-04-02";
    public static final String VALID_TASK_DUE_DATE_TASKSECOND = "2018-05-10";
    public static final String VALID_TASK_STATUS_TASKFIRST = "undone";
    public static final String VALID_TASK_STATUS_TASKSECOND = "done";
    public static final String VALID_TASK_CATEGORY_PERSONAL = "personal";
    public static final String VALID_TASK_CATEGORY_WORK = "work";
    public static final String VALID_TASK_CATEGORY_MEETING = "meeting";

    public static final String TASK_NAME_DESC_TASKFIRST =
            " " + PREFIX_TASK_NAME + VALID_TASK_NAME_TASKFIRST;
    public static final String TASK_NAME_DESC_TASKSECOND =
            " " + PREFIX_TASK_NAME + VALID_TASK_NAME_TASKSECOND;
    public static final String TASK_PRIORITY_DESC_TASKFIRST =
            " " + PREFIX_TASK_PRIORITY + VALID_TASK_PRIORITY_TASKFIRST;
    public static final String TASK_PRIORITY_DESC_TASKSECOND =
            " " + PREFIX_TASK_PRIORITY + VALID_TASK_PRIORITY_TASKSECOND;
    public static final String TASK_DESCRIPTION_DESC_TASKFIRST =
            " " + PREFIX_TASK_DESCRIPTION + VALID_TASK_DESCRIPTION_TASKFIRST;
    public static final String TASK_DESCRIPTION_DESC_TASKSECOND =
            " " + PREFIX_TASK_DESCRIPTION + VALID_TASK_DESCRIPTION_TASKSECOND;
    public static final String TASK_DUE_DATE_DESC_TASKFIRST =
            " " + PREFIX_TASK_DUE_DATE + VALID_TASK_DUE_DATE_TASKFIRST;
    public static final String TASK_DUE_DATE_DESC_TASKSECOND =
            " " + PREFIX_TASK_DUE_DATE + VALID_TASK_DUE_DATE_TASKSECOND;
    public static final String TASK_STATUS_DESC_TASKFIRST =
            " " + PREFIX_TASK_STATUS + VALID_TASK_STATUS_TASKFIRST;
    public static final String TASK_STATUS_DESC_TASKSECOND =
            " " + PREFIX_TASK_STATUS + VALID_TASK_STATUS_TASKSECOND;
    public static final String TASK_CATEGORY_DESC_PERSONAL =
            " " + PREFIX_TASK_CATEGORY + VALID_TASK_CATEGORY_PERSONAL;
    public static final String TASK_CATEGORY_DESC_WORK =
            " " + PREFIX_TASK_CATEGORY + VALID_TASK_CATEGORY_WORK;

    public static final String INVALID_TASK_NAME_DESC = " "
            + PREFIX_TASK_NAME + "Task*"; // '*' not allowed in taskNames
    public static final String INVALID_TASK_PRIORITY_DESC = " "
            + PREFIX_TASK_PRIORITY + "random"; // only 'high', 'medium', 'low' allowed in taskPriority
    public static final String INVALID_TASK_DESCRIPTION_DESC = " "
            + PREFIX_TASK_DESCRIPTION + ""; // empty string not allowed in taskDescription
    public static final String INVALID_TASK_DUE_DATE_DESC = " "
            + PREFIX_TASK_DUE_DATE + "2018 05 10"; // date without formatter not allowed in taskDueDate
    public static final String INVALID_TASK_STATUS_DESC = " "
            + PREFIX_TASK_STATUS + "random"; // only 'done', 'undone' allowed in taskStatus
    public static final String INVALID_TASK_CATEGORY_DESC = " "
            + PREFIX_TASK_CATEGORY + "meeting & work"; // '&' not allowed in taskCategory

```
###### \java\seedu\address\logic\commands\CommandTestUtil.java
``` java
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTaskName = task.getTaskName().value.split("\\s+");
        model.updateFilteredTaskList(new TaskNameContainsKeywordsPredicate(Arrays.asList(splitTaskName[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Deletes the first task in {@code model}'s filtered list from {@code model}'s address book.
     */
    public static void deleteFirstTask(Model model) {
        Task firstTask = model.getFilteredTaskList().get(0);
        try {
            model.deleteTask(firstTask);
        } catch (TaskNotFoundException tnfe) {
            throw new AssertionError("Task in filtered list must exist in model.", tnfe);
        }
    }

```
###### \java\seedu\address\logic\commands\EditTaskDescriptorTest.java
``` java
public class EditTaskDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditTaskDescriptor descriptorWithSameValues = new EditTaskDescriptor(DESC_TASKFIRST);
        assertTrue(DESC_TASKFIRST.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_TASKFIRST.equals(DESC_TASKFIRST));

        // null -> returns false
        assertFalse(DESC_TASKFIRST.equals(null));

        // different types -> returns false
        assertFalse(DESC_TASKFIRST.equals(1));

        // different values -> returns false
        assertFalse(DESC_TASKFIRST.equals(DESC_TASKSECOND));

        // different task name -> returns false
        EditTaskDescriptor editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskName(VALID_TASK_NAME_TASKSECOND).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));

        // different task priority -> returns false
        editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));

        // different task description -> returns false
        editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskDescription(VALID_TASK_DESCRIPTION_TASKSECOND).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));

        // different task due date -> returns false
        editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKSECOND).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));

        // different task status -> returns false
        editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskStatus(VALID_TASK_STATUS_TASKSECOND).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));

        // different task categories -> returns false
        editedTaskFirst = new EditTaskDescriptorBuilder(DESC_TASKFIRST)
                .withTaskCategories(VALID_TASK_CATEGORY_MEETING).build();
        assertFalse(DESC_TASKFIRST.equals(editedTaskFirst));
    }

}
```
###### \java\seedu\address\logic\commands\SortCommandTest.java
``` java
public class SortCommandTest {

    private Model model;
    private Model expectedModel;
    private SortCommand sortCommand;

    @Before
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalEventBook(), new UserPrefs());

        sortCommand = new SortCommand();
        sortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_nonEmptyListIsSorted_success() {
        model.sortPersons();
        expectedModel.sortPersons();
        assertCommandSuccess(sortCommand, model, sortCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
```
###### \java\seedu\address\logic\commands\TaskAddCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code TaskAddCommand}.
 */
public class TaskAddCommandTest {

    private Model model;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() throws Exception {
        Task validTask = new TaskBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel.addTask(validTask);

        assertCommandSuccess(prepareCommand(validTask, model), model,
                String.format(TaskAddCommand.MESSAGE_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getAddressBook().getTaskList().get(0);
        assertCommandFailure(prepareCommand(taskInList, model), model, TaskAddCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void equals() {
        Task taskone = new TaskBuilder().withTaskName("Taskone").build();
        Task tasktwo = new TaskBuilder().withTaskName("Tasktwo").build();
        TaskAddCommand addTaskOneCommand = new TaskAddCommand(taskone);
        TaskAddCommand addTaskTwoCommand = new TaskAddCommand(tasktwo);

        // same object -> returns true
        assertTrue(addTaskOneCommand.equals(addTaskOneCommand));

        // same values -> returns true
        TaskAddCommand addTaskOneCommandCopy = new TaskAddCommand(taskone);
        assertTrue(addTaskOneCommand.equals(addTaskOneCommandCopy));

        // different types -> returns false
        assertFalse(addTaskOneCommand.equals(5));

        // null -> returns false
        assertFalse(addTaskOneCommand.equals(null));

        // different task -> returns false
        assertFalse(addTaskOneCommand.equals(addTaskTwoCommand));
    }

    /**
     * Generates a new {@code TaskAddCommand} which upon execution, adds {@code task} into the {@code model}.
     */
    private TaskAddCommand prepareCommand(Task task, Model model) {
        TaskAddCommand command = new TaskAddCommand(task);
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

}
```
###### \java\seedu\address\logic\commands\TaskDeleteCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand) and unit tests for
 * {@code TaskDeleteCommand}.
 */
public class TaskDeleteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() throws Exception {
        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TaskDeleteCommand taskDeleteCommand = prepareCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(TaskDeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);

        assertCommandSuccess(taskDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() throws Exception {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        TaskDeleteCommand taskDeleteCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(taskDeleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws Exception {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskToDelete = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        TaskDeleteCommand taskDeleteCommand = prepareCommand(INDEX_FIRST_TASK);

        String expectedMessage = String.format(TaskDeleteCommand.MESSAGE_DELETE_TASK_SUCCESS, taskToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel.deleteTask(taskToDelete);
        showNoTask(expectedModel);

        assertCommandSuccess(taskDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        TaskDeleteCommand taskDeleteCommand = prepareCommand(outOfBoundIndex);

        assertCommandFailure(taskDeleteCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void equals() throws Exception {
        TaskDeleteCommand taskDeleteFirstCommand = prepareCommand(INDEX_FIRST_TASK);
        TaskDeleteCommand taskDeleteSecondCommand = prepareCommand(INDEX_SECOND_TASK);

        // same object -> returns true
        assertTrue(taskDeleteFirstCommand.equals(taskDeleteFirstCommand));

        // same values -> returns true
        TaskDeleteCommand taskDeleteFirstCommandCopy = prepareCommand(INDEX_FIRST_TASK);
        assertTrue(taskDeleteFirstCommand.equals(taskDeleteFirstCommandCopy));

        // one command preprocessed when previously equal -> returns false
        taskDeleteFirstCommandCopy.preprocessUndoableCommand();
        assertFalse(taskDeleteFirstCommand.equals(taskDeleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskDeleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(taskDeleteFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(taskDeleteFirstCommand.equals(taskDeleteSecondCommand));
    }

    /**
     * Returns a {@code TaskDeleteCommand} with the parameter {@code index}.
     */
    private TaskDeleteCommand prepareCommand(Index index) {
        TaskDeleteCommand taskDeleteCommand = new TaskDeleteCommand(index);
        taskDeleteCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return taskDeleteCommand;
    }

    /**
     * Updates {@code model}'s filtered list to show no task.
     */
    private void showNoTask(Model model) {
        model.updateFilteredTaskList(t -> false);

        assertTrue(model.getFilteredTaskList().isEmpty());
    }

}
```
###### \java\seedu\address\logic\commands\TaskEditCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model, UndoCommand and RedoCommand)
 * and unit tests for TaskEditCommand.
 */
public class TaskEditCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() throws Exception {
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK, descriptor);

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.updateTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() throws Exception {
        Index indexLastTask = Index.fromOneBased(model.getFilteredTaskList().size());
        Task lastTask = model.getFilteredTaskList().get(indexLastTask.getZeroBased());

        TaskBuilder taskInList = new TaskBuilder(lastTask);
        Task editedTask = taskInList.withTaskName(VALID_TASK_NAME_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).withTaskCategories(VALID_TASK_CATEGORY_WORK).build();

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).withTaskCategories(VALID_TASK_CATEGORY_WORK).build();
        TaskEditCommand taskEditCommand = prepareCommand(indexLastTask, descriptor);

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.updateTask(lastTask, editedTask);

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK, new EditTaskDescriptor());
        Task editedTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() throws Exception {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        Task taskInFilteredList = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        Task editedTask = new TaskBuilder(taskInFilteredList).withTaskName(VALID_TASK_NAME_TASKFIRST).build();
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK, new EditTaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_TASKFIRST).build());

        String expectedMessage = String.format(TaskEditCommand.MESSAGE_EDIT_TASK_SUCCESS, editedTask);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());
        expectedModel.updateTask(model.getFilteredTaskList().get(0), editedTask);

        assertCommandSuccess(taskEditCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateTaskUnfilteredList_failure() {
        Task firstTask = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(firstTask).build();
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_SECOND_TASK, descriptor);

        assertCommandFailure(taskEditCommand, model, taskEditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_duplicateTaskFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);

        // edit task in filtered list into a duplicate in address book
        Task taskInList = model.getAddressBook().getTaskList().get(INDEX_SECOND_TASK.getZeroBased());
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK,
                new EditTaskDescriptorBuilder(taskInList).build());

        assertCommandFailure(taskEditCommand, model, TaskEditCommand.MESSAGE_DUPLICATE_TASK);
    }

    @Test
    public void execute_invalidTaskIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST).build();
        TaskEditCommand taskEditCommand = prepareCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(taskEditCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidTaskIndexFilteredList_failure() {
        showTaskAtIndex(model, INDEX_FIRST_TASK);
        Index outOfBoundIndex = INDEX_SECOND_TASK;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getTaskList().size());

        TaskEditCommand taskEditCommand = prepareCommand(outOfBoundIndex, new EditTaskDescriptorBuilder()
                .withTaskName(VALID_TASK_NAME_TASKFIRST).build());

        assertCommandFailure(taskEditCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void executeUndoRedo_validIndexUnfilteredList_success() throws Exception {
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        UndoCommand undoCommand = prepareUndoCommand(model, undoRedoStack);
        RedoCommand redoCommand = prepareRedoCommand(model, undoRedoStack);
        Task editedTask = new TaskBuilder().build();
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK, descriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());

        // edit -> first task edited
        taskEditCommand.execute();
        undoRedoStack.push(taskEditCommand);

        // undo -> reverts addressbook back to previous state and filtered task list to show all tasks
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        // redo -> same first task edited again
        expectedModel.updateTask(taskToEdit, editedTask);
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void executeUndoRedo_invalidIndexUnfilteredList_failure() {
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        UndoCommand undoCommand = prepareUndoCommand(model, undoRedoStack);
        RedoCommand redoCommand = prepareRedoCommand(model, undoRedoStack);
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST).build();
        TaskEditCommand taskEditCommand = prepareCommand(outOfBoundIndex, descriptor);

        // execution failed -> taskEditCommand not pushed into undoRedoStack
        assertCommandFailure(taskEditCommand, model, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);

        // no commands in undoRedoStack -> undoCommand and redoCommand fail
        assertCommandFailure(undoCommand, model, UndoCommand.MESSAGE_FAILURE);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_FAILURE);
    }

    /**
     * 1. Edits a {@code Task} from a filtered list.
     * 2. Undo the edit.
     * 3. The unfiltered list should be shown now. Verify that the index of the previously edited task in the
     *    unfiltered list is different from the index at the filtered list.
     * 4. Redo the edit. This ensures {@code RedoCommand} edits the task object regardless of indexing.
     */
    @Test
    public void executeUndoRedo_validIndexFilteredList_sameTaskEdited() throws Exception {
        UndoRedoStack undoRedoStack = new UndoRedoStack();
        UndoCommand undoCommand = prepareUndoCommand(model, undoRedoStack);
        RedoCommand redoCommand = prepareRedoCommand(model, undoRedoStack);
        Task editedTask = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(editedTask).build();
        TaskEditCommand taskEditCommand = prepareCommand(INDEX_FIRST_TASK, descriptor);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()),
                new EventBook(model.getEventBook()), new UserPrefs());

        showTaskAtIndex(model, INDEX_SECOND_TASK);
        Task taskToEdit = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        // edit -> edits second task in unfiltered task list / first task in filtered task list
        taskEditCommand.execute();
        undoRedoStack.push(taskEditCommand);

        // undo -> reverts addressbook back to previous state and filtered task list to show all tasks
        assertCommandSuccess(undoCommand, model, UndoCommand.MESSAGE_SUCCESS, expectedModel);

        expectedModel.updateTask(taskToEdit, editedTask);
        assertNotEquals(model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased()), taskToEdit);
        // redo -> edits same second task in unfiltered task list
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() throws Exception {
        final TaskEditCommand standardCommand = prepareCommand(INDEX_FIRST_TASK, DESC_TASKFIRST);

        // same values -> returns true
        EditTaskDescriptor copyDescriptor = new EditTaskDescriptor(DESC_TASKFIRST);
        TaskEditCommand commandWithSameValues = prepareCommand(INDEX_FIRST_TASK, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // one command preprocessed when previously equal -> returns false
        commandWithSameValues.preprocessUndoableCommand();
        assertFalse(standardCommand.equals(commandWithSameValues));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new TaskEditCommand(INDEX_SECOND_TASK, DESC_TASKFIRST)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new TaskEditCommand(INDEX_FIRST_TASK, DESC_TASKSECOND)));
    }

    /**
     * Returns an {@code TaskEditCommand} with parameters {@code index} and {@code descriptor}
     */
    private TaskEditCommand prepareCommand(Index index, EditTaskDescriptor descriptor) {
        TaskEditCommand taskEditCommand = new TaskEditCommand(index, descriptor);
        taskEditCommand.setData(model, new CommandHistory(), new UndoRedoStack());
        return taskEditCommand;
    }

}
```
###### \java\seedu\address\logic\commands\TaskFindCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) for {@code TaskFindCommand}.
 */
public class TaskFindCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());

    @Test
    public void equals() {
        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("first"));
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("second"));

        TaskFindCommand taskFindFirstCommand = new TaskFindCommand(firstPredicate);
        TaskFindCommand taskFindSecondCommand = new TaskFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(taskFindFirstCommand.equals(taskFindFirstCommand));

        // same values -> returns true
        TaskFindCommand taskFindFirstCommandCopy = new TaskFindCommand(firstPredicate);
        assertTrue(taskFindFirstCommand.equals(taskFindFirstCommandCopy));

        // different types -> returns false
        assertFalse(taskFindFirstCommand.equals(5));

        // null -> returns false
        assertFalse(taskFindFirstCommand.equals(null));

        // different task -> returns false
        assertFalse(taskFindFirstCommand.equals(taskFindSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTaskFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 0);
        TaskFindCommand command = prepareCommand(" ");
        assertCommandSuccess(command, expectedMessage, Collections.emptyList());
    }

    @Test
    public void execute_multipleKeywords_multipleTasksFound() {
        String expectedMessage = String.format(MESSAGE_TASKS_LISTED_OVERVIEW, 3);
        TaskFindCommand command = prepareCommand("one two three");
        assertCommandSuccess(command, expectedMessage, Arrays.asList(TASKONE, TASKTWO, TASKTHREE));
    }

    /**
     * Parses {@code userInput} into a {@code TaskFindCommand}.
     */
    private TaskFindCommand prepareCommand(String userInput) {
        TaskFindCommand command = new TaskFindCommand(
                new TaskNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+"))));
        command.setData(model, new CommandHistory(), new UndoRedoStack());
        return command;
    }

    /**
     * Asserts that {@code command} is successfully executed, and<br>
     *     - the command feedback is equal to {@code expectedMessage}<br>
     *     - the {@code FilteredList<Task>} is equal to {@code expectedList}<br>
     *     - the {@code AddressBook} in model remains the same after executing the {@code command}
     */
    private void assertCommandSuccess(TaskFindCommand command, String expectedMessage, List<Task> expectedList) {
        AddressBook expectedAddressBook = new AddressBook(model.getAddressBook());
        CommandResult commandResult = command.execute();

        assertEquals(expectedMessage, commandResult.feedbackToUser);
        assertEquals(expectedList, model.getFilteredTaskList());
        assertEquals(expectedAddressBook, model.getAddressBook());
    }

}
```
###### \java\seedu\address\logic\commands\TaskListCommandTest.java
``` java
/**
 * Contains integration tests (interaction with the Model) and unit tests for TaskListCommand.
 */
public class TaskListCommandTest {

    private Model model;
    private Model expectedModel;
    private TaskListCommand taskListCommand;

    @Before
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), model.getEventBook(), new UserPrefs());

        taskListCommand = new TaskListCommand();
        taskListCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(taskListCommand, model, TaskListCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
```
###### \java\seedu\address\logic\commands\TaskSortCommandTest.java
``` java
public class TaskSortCommandTest {

    private Model model;
    private Model expectedModel;
    private TaskSortCommand taskSortCommand;

    @Before
    public void setUp() throws Exception {
        model = new ModelManager(getTypicalAddressBook(), getTypicalEventBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), getTypicalEventBook(), new UserPrefs());

        taskSortCommand = new TaskSortCommand();
        taskSortCommand.setData(model, new CommandHistory(), new UndoRedoStack());
    }

    @Test
    public void execute_nonEmptyListIsSorted_success() {
        model.sortTasksByPriority();
        expectedModel.sortTasksByPriority();
        assertCommandSuccess(taskSortCommand, model, taskSortCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
```
###### \java\seedu\address\logic\parser\AddressBookParserTest.java
``` java
    @Test
    public void parseCommand_taskAdd() throws Exception {
        Task task = new TaskBuilder().build();
        TaskAddCommand command = (TaskAddCommand) parser.parseCommand(TaskUtil.getTaskAddCommand(task));
        assertEquals(new TaskAddCommand(task), command);
    }

    @Test
    public void parseCommand_taskDelete() throws Exception {
        TaskDeleteCommand command = (TaskDeleteCommand) parser.parseCommand(
                TaskDeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new TaskDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_taskEdit() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        TaskEditCommand command = (TaskEditCommand) parser.parseCommand(TaskEditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_TASK.getOneBased() + " " + TaskUtil.getTaskDetails(task));
        assertEquals(new TaskEditCommand(INDEX_FIRST_TASK, descriptor), command);
    }

    @Test
    public void parseCommand_taskFind() throws Exception {
        List<String> keywords = Arrays.asList("agenda", "day", "meet");
        TaskFindCommand command = (TaskFindCommand) parser.parseCommand(TaskFindCommand.COMMAND_WORD + " "
                + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new TaskFindCommand(new TaskNameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_taskList() throws Exception {
        assertTrue(parser.parseCommand(TaskListCommand.COMMAND_WORD) instanceof TaskListCommand);
        assertTrue(parser.parseCommand(TaskListCommand.COMMAND_WORD + " 5") instanceof TaskListCommand);
    }

    @Test
    public void parseCommand_taskSort() throws Exception {
        assertTrue(parser.parseCommand(TaskSortCommand.COMMAND_WORD) instanceof TaskSortCommand);
        assertTrue(parser.parseCommand(TaskSortCommand.COMMAND_WORD + " 5") instanceof TaskSortCommand);
    }
}
```
###### \java\seedu\address\logic\parser\TaskAddCommandParserTest.java
``` java
public class TaskAddCommandParserTest {

    private TaskAddCommandParser parser = new TaskAddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Task expectedTask = new TaskBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST).withTaskStatus(VALID_TASK_STATUS_TASKFIRST)
                .withTaskCategories(VALID_TASK_CATEGORY_WORK).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskNames - last taskName accepted
        assertParseSuccess(parser, TASK_NAME_DESC_TASKSECOND + TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskPriorities - last taskPriority accepted
        assertParseSuccess(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKSECOND
                + TASK_PRIORITY_DESC_TASKFIRST + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskDescriptions - last taskDescription accepted
        assertParseSuccess(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskDueDates - last taskDueDate accepted
        assertParseSuccess(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskStatuses - last taskStatus accepted
        assertParseSuccess(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKSECOND
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_PERSONAL, new TaskAddCommand(expectedTask));

        // multiple taskCategories - all accepted
        Task expectedTaskMultipleCategories = new TaskBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST).withTaskStatus(VALID_TASK_STATUS_TASKFIRST)
                .withTaskCategories(VALID_TASK_CATEGORY_PERSONAL, VALID_TASK_CATEGORY_WORK).build();
        assertParseSuccess(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK, new TaskAddCommand(expectedTask));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero taskCategories
        Task expectedTask = new TaskBuilder().withTaskName(VALID_TASK_NAME_TASKSECOND)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND).withTaskDescription(VALID_TASK_DESCRIPTION_TASKSECOND)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKSECOND).withTaskStatus(VALID_TASK_STATUS_TASKSECOND)
                .withTaskCategories().build();
        assertParseSuccess(parser, TASK_NAME_DESC_TASKSECOND + TASK_PRIORITY_DESC_TASKSECOND
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKSECOND + TASK_STATUS_DESC_TASKSECOND,
                new TaskAddCommand(expectedTask));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE);

        // missing taskName prefix
        assertParseFailure(parser, VALID_TASK_NAME_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKSECOND,
                expectedMessage);

        // missing taskPriority prefix
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + VALID_TASK_PRIORITY_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKSECOND,
                expectedMessage);

        // missing taskDescription prefix
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + VALID_TASK_DESCRIPTION_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKSECOND,
                expectedMessage);

        // missing taskDueDate prefix
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + VALID_TASK_DUE_DATE_TASKFIRST + TASK_STATUS_DESC_TASKSECOND,
                expectedMessage);

        // missing taskStatus prefix
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + VALID_TASK_STATUS_TASKFIRST,
                expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_TASK_NAME_TASKFIRST + VALID_TASK_PRIORITY_TASKFIRST
                + VALID_TASK_DESCRIPTION_TASKFIRST + VALID_TASK_DUE_DATE_TASKFIRST + VALID_TASK_STATUS_TASKFIRST,
                expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid taskName
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK, TaskName.MESSAGE_TASK_NAME_CONSTRAINTS);

        // invalid taskPriority
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + INVALID_TASK_PRIORITY_DESC
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK,
                TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS);

        // invalid taskDescription
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + INVALID_TASK_DESCRIPTION_DESC + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK,
                TaskDescription.MESSAGE_TASK_DESCRIPTION_CONSTRAINTS);

        // invalid taskDueDate
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + INVALID_TASK_DUE_DATE_DESC + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK, TaskDueDate.MESSAGE_TASK_DUE_DATE_CONSTRAINTS);

        // invalid taskStatus
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + INVALID_TASK_STATUS_DESC
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK, TaskStatus.MESSAGE_TASK_STATUS_CONSTRAINTS);

        // invalid taskCategory
        assertParseFailure(parser, TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKSECOND + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + INVALID_TASK_CATEGORY_DESC + VALID_TASK_CATEGORY_PERSONAL,
                TaskCategory.MESSAGE_TASK_CATEGORY_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, INVALID_TASK_NAME_DESC + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + INVALID_TASK_DUE_DATE_DESC + TASK_STATUS_DESC_TASKFIRST,
                TaskName.MESSAGE_TASK_NAME_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TASK_NAME_DESC_TASKFIRST + TASK_PRIORITY_DESC_TASKFIRST
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST
                + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskAddCommand.MESSAGE_USAGE));
    }

}
```
###### \java\seedu\address\logic\parser\TaskDeleteCommandParserTest.java
``` java
public class TaskDeleteCommandParserTest {

    private TaskDeleteCommandParser parser = new TaskDeleteCommandParser();

    @Test
    public void parse_validArgs_returnsTaskDeleteCommand() {
        assertParseSuccess(parser, "1", new TaskDeleteCommand(INDEX_FIRST_TASK));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                TaskDeleteCommand.MESSAGE_USAGE));
    }

}
```
###### \java\seedu\address\logic\parser\TaskEditCommandParserTest.java
``` java
public class TaskEditCommandParserTest {

    private static final String TASK_CATEGORY_EMPTY = " " + PREFIX_TASK_CATEGORY;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskEditCommand.MESSAGE_USAGE);

    private TaskEditCommandParser parser = new TaskEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TASK_NAME_TASKFIRST, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "5", TaskEditCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TASK_NAME_DESC_TASKFIRST, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + TASK_NAME_DESC_TASKFIRST, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "5 random task", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "5 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1"
                + INVALID_TASK_NAME_DESC, TaskName.MESSAGE_TASK_NAME_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1"
                + INVALID_TASK_PRIORITY_DESC, TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS); // invalid priority
        assertParseFailure(parser, "1"
                + INVALID_TASK_DESCRIPTION_DESC,
                TaskDescription.MESSAGE_TASK_DESCRIPTION_CONSTRAINTS); // invalid description
        assertParseFailure(parser, "1"
                + INVALID_TASK_DUE_DATE_DESC, TaskDueDate.MESSAGE_TASK_DUE_DATE_CONSTRAINTS); // invalid due date
        assertParseFailure(parser, "1"
                + INVALID_TASK_STATUS_DESC, TaskStatus.MESSAGE_TASK_STATUS_CONSTRAINTS); // invalid status
        assertParseFailure(parser, "1"
                + INVALID_TASK_CATEGORY_DESC, TaskCategory.MESSAGE_TASK_CATEGORY_CONSTRAINTS); // invalid category

        // invalid task priority followed by valid task description
        assertParseFailure(parser, "1" + INVALID_TASK_PRIORITY_DESC + TASK_DESCRIPTION_DESC_TASKFIRST,
                TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS);

        // valid task priority followed by invalid task priority.
        // The test case for invalid task priority followed by valid task priority is tested at
        // {@code parse_invalidValueFollowedByValidValue_success()}
        assertParseFailure(parser, "1" + TASK_PRIORITY_DESC_TASKSECOND + INVALID_TASK_PRIORITY_DESC,
                TaskPriority.MESSAGE_TASK_PRIORITY_CONSTRAINTS);

        // while parsing {@code PREFIX_TASK_CATEGORY} alone will reset the task categories of the
        // {@code Task} being edited, parsing it together with a valid task category results in error
        assertParseFailure(parser, "1" + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_DESC_WORK
                + TASK_CATEGORY_EMPTY, TaskCategory.MESSAGE_TASK_CATEGORY_CONSTRAINTS);
        assertParseFailure(parser, "1" + TASK_CATEGORY_DESC_PERSONAL + TASK_CATEGORY_EMPTY
                + TASK_CATEGORY_DESC_WORK, TaskCategory.MESSAGE_TASK_CATEGORY_CONSTRAINTS);
        assertParseFailure(parser, "1" + TASK_CATEGORY_EMPTY + TASK_CATEGORY_DESC_PERSONAL
                + TASK_CATEGORY_DESC_WORK, TaskCategory.MESSAGE_TASK_CATEGORY_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TASK_NAME_DESC + INVALID_TASK_PRIORITY_DESC
                + INVALID_TASK_DESCRIPTION_DESC + VALID_TASK_DUE_DATE_TASKFIRST + VALID_TASK_STATUS_TASKFIRST,
                TaskName.MESSAGE_TASK_NAME_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_PRIORITY_DESC_TASKSECOND + TASK_CATEGORY_DESC_PERSONAL
                + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST + TASK_NAME_DESC_TASKFIRST
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_WORK;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST)
                .withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND).withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST).withTaskStatus(VALID_TASK_STATUS_TASKFIRST)
                .withTaskCategories(VALID_TASK_CATEGORY_PERSONAL, VALID_TASK_CATEGORY_WORK).build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TASK_PRIORITY_DESC_TASKSECOND + TASK_STATUS_DESC_TASKFIRST;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND)
                .withTaskStatus(VALID_TASK_STATUS_TASKFIRST).build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // task name
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_NAME_DESC_TASKFIRST;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST).build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task priority
        userInput = targetIndex.getOneBased() + TASK_PRIORITY_DESC_TASKFIRST;
        descriptor = new EditTaskDescriptorBuilder().withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task description
        userInput = targetIndex.getOneBased() + TASK_DESCRIPTION_DESC_TASKFIRST;
        descriptor = new EditTaskDescriptorBuilder().withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task due date
        userInput = targetIndex.getOneBased() + TASK_DUE_DATE_DESC_TASKFIRST;
        descriptor = new EditTaskDescriptorBuilder().withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task status
        userInput = targetIndex.getOneBased() + TASK_STATUS_DESC_TASKFIRST;
        descriptor = new EditTaskDescriptorBuilder().withTaskStatus(VALID_TASK_STATUS_TASKFIRST).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // task categories
        userInput = targetIndex.getOneBased() + TASK_CATEGORY_DESC_WORK;
        descriptor = new EditTaskDescriptorBuilder().withTaskCategories(VALID_TASK_CATEGORY_WORK).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + TASK_PRIORITY_DESC_TASKFIRST + TASK_DESCRIPTION_DESC_TASKFIRST
                + TASK_DUE_DATE_DESC_TASKFIRST + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_WORK
                + TASK_PRIORITY_DESC_TASKFIRST + TASK_DESCRIPTION_DESC_TASKFIRST + TASK_DUE_DATE_DESC_TASKFIRST
                + TASK_STATUS_DESC_TASKFIRST + TASK_CATEGORY_DESC_WORK + TASK_CATEGORY_DESC_PERSONAL;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST)
                .withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST).withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST)
                .withTaskStatus(VALID_TASK_STATUS_TASKFIRST).withTaskCategories(VALID_TASK_CATEGORY_PERSONAL,
                        VALID_TASK_CATEGORY_WORK).build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidValueFollowedByValidValue_success() {
        // no other valid values specified
        Index targetIndex = INDEX_FIRST_TASK;
        String userInput = targetIndex.getOneBased() + INVALID_TASK_PRIORITY_DESC + TASK_PRIORITY_DESC_TASKSECOND;
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder()
                .withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND).build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // other valid values specified
        userInput = targetIndex.getOneBased() + TASK_PRIORITY_DESC_TASKSECOND + INVALID_TASK_DESCRIPTION_DESC
                + TASK_DUE_DATE_DESC_TASKSECOND + TASK_DESCRIPTION_DESC_TASKSECOND;
        descriptor = new EditTaskDescriptorBuilder().withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND)
                .withTaskDescription(VALID_TASK_DESCRIPTION_TASKSECOND)
                .withTaskDueDate(VALID_TASK_DUE_DATE_TASKSECOND).build();
        expectedCommand = new TaskEditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_resetTaskCategories_success() {
        Index targetIndex = INDEX_SECOND_TASK;
        String userInput = targetIndex.getOneBased() + TASK_CATEGORY_EMPTY;

        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder().withTaskCategories().build();
        TaskEditCommand expectedCommand = new TaskEditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


}
```
###### \java\seedu\address\logic\parser\TaskFindCommandParserTest.java
``` java
public class TaskFindCommandParserTest {

    private TaskFindCommandParser parser = new TaskFindCommandParser();

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskFindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsTaskFindCommand() {
        // no leading and trailing whitespaces
        TaskFindCommand expectedTaskFindCommand =
                new TaskFindCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList("discuss", "meeting")));
        assertParseSuccess(parser, "discuss meeting", expectedTaskFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n discuss \n \t meeting  \t", expectedTaskFindCommand);
    }

}
```
###### \java\seedu\address\model\AddressBookTest.java
``` java
    @Test
    public void resetData_withDuplicateTasks_throwsAssertionError() {
        List<Person> newPersons = Arrays.asList();
        List<Tag> newTags = new ArrayList<>();
        // Repeat TASKONE twice
        List<Task> newTasks = Arrays.asList(TASKONE, TASKONE);
        List<TaskCategory> newTaskCategories = new ArrayList<>(TASKONE.getTaskCategories());
        AddressBookStub newData = new AddressBookStub(newPersons, newTags, newTasks, newTaskCategories);

        thrown.expect(AssertionError.class);
        addressBook.resetData(newData);
    }

```
###### \java\seedu\address\model\AddressBookTest.java
``` java
    @Test
    public void getTaskList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getTaskList().remove(0);
    }

    @Test
    public void getTaskCategoryList_modifyList_throwsUnsupportedOperationException() {
        thrown.expect(UnsupportedOperationException.class);
        addressBook.getTaskCategoryList().remove(0);
    }

```
###### \java\seedu\address\model\category\TaskCategoryTest.java
``` java
public class TaskCategoryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskCategory(null));
    }

    @Test
    public void constructor_invalidTaskCategoryName_throwsIllegalArgumentException() {
        String invalidCategoryName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskCategory(invalidCategoryName));
    }

    @Test
    public void isValidTaskCategoryName() {
        // null task category name
        Assert.assertThrows(NullPointerException.class, () -> TaskCategory.isValidTaskCategoryName(null));

        // invalid task category name
        assertFalse(TaskCategory.isValidTaskCategoryName("")); // empty string
        assertFalse(TaskCategory.isValidTaskCategoryName(" ")); // spaces only
        assertFalse(TaskCategory.isValidTaskCategoryName("^")); // only non-alphanumeric characters
        assertFalse(TaskCategory.isValidTaskCategoryName("meet&ing")); // contains non-alphanumeric characters
        assertFalse(TaskCategory.isValidTaskCategoryName("project meeting")); // contains spaces

        // valid task category name
        assertTrue(TaskCategory.isValidTaskCategoryName("firstcategory")); // alphabets only without whitespaces
        assertTrue(TaskCategory.isValidTaskCategoryName("12345")); // numbers only
        assertTrue(TaskCategory.isValidTaskCategoryName("firstcategory5")); // alphanumeric characters
    }

}
```
###### \java\seedu\address\model\ModelManagerTest.java
``` java
    @Test
    public void getFilteredTaskList_modifyList_throwsUnsupportedOperationException() {
        ModelManager modelManager = new ModelManager();
        thrown.expect(UnsupportedOperationException.class);
        modelManager.getFilteredTaskList().remove(0);
    }

```
###### \java\seedu\address\model\task\TaskDescriptionTest.java
``` java
public class TaskDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskDescription(null));
    }

    @Test
    public void constructor_invalidTaskDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskDescription(invalidDescription));
    }

    @Test
    public void isValidTaskDescription() {
        // null task description
        Assert.assertThrows(NullPointerException.class, () -> TaskDescription.isValidTaskDescription(null));

        // invalid task description
        assertFalse(TaskDescription.isValidTaskDescription("")); // empty string
        assertFalse(TaskDescription.isValidTaskDescription(" ")); // spaces only

        // valid task description
        assertTrue(TaskDescription.isValidTaskDescription("random xyz")); // random string
        assertTrue(TaskDescription.isValidTaskDescription("12345")); // numbers
        assertTrue(TaskDescription.isValidTaskDescription("random 123")); // alphanumeric
        assertTrue(TaskDescription.isValidTaskDescription("Agenda for meeting")); // with capital letters
        assertTrue(TaskDescription.isValidTaskDescription("Super long tasks to be done this week")); // long string
        assertTrue(TaskDescription.isValidTaskDescription("Hello World!")); // non-alphanumeric characters
        assertTrue(TaskDescription.isValidTaskDescription("Attend proposal discussion @ LT100 at 2:00pm"));
    }

}
```
###### \java\seedu\address\model\task\TaskDueDateTest.java
``` java
public class TaskDueDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskDueDate(null));
    }

    @Test
    public void constructor_invalidTaskDueDate_throwsIllegalArgumentException() {
        String invalidDueDate = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskDueDate(invalidDueDate));
    }

    @Test
    public void isValidTaskDueDate() {
        // null task due date
        Assert.assertThrows(NullPointerException.class, () -> TaskDueDate.isValidTaskDueDate(null));

        // invalid task due date
        assertFalse(TaskDueDate.isValidTaskDueDate("")); // empty string
        assertFalse(TaskDueDate.isValidTaskDueDate(" ")); // spaces only
        assertFalse(TaskDueDate.isValidTaskDueDate("random xyz")); // random string
        assertFalse(TaskDueDate.isValidTaskDueDate("12345")); // numbers
        assertFalse(TaskDueDate.isValidTaskDueDate("random 123")); // alphanumeric

        // invalid task due date format
        assertFalse(TaskDueDate.isValidTaskDueDate("2018 05 05")); // without formatter
        assertFalse(TaskDueDate.isValidTaskDueDate("2018-02-29")); // date existing in leap year only
        assertFalse(TaskDueDate.isValidTaskDueDate("2018-12-32")); // non-existing date
        assertFalse(TaskDueDate.isValidTaskDueDate("1898-12-31")); // valid year starts from 1900

        // valid task due date
        assertTrue(TaskDueDate.isValidTaskDueDate("2018-05-05")); // correct format
        assertTrue(TaskDueDate.isValidTaskDueDate("2018-12-31")); // existing date
        assertTrue(TaskDueDate.isValidTaskDueDate("2020-02-29")); // date in leap year
    }

}
```
###### \java\seedu\address\model\task\TaskNameContainsKeywordsPredicateTest.java
``` java
public class TaskNameContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("firstTask");
        List<String> secondPredicateKeywordList = Arrays.asList("firstTask", "secondTask");

        TaskNameContainsKeywordsPredicate firstPredicate =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);
        TaskNameContainsKeywordsPredicate secondPredicate =
                new TaskNameContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TaskNameContainsKeywordsPredicate firstPredicateCopy =
                new TaskNameContainsKeywordsPredicate(firstPredicateKeywordList);

        // different types -> returns false
        assertFalse(firstPredicate.equals(5));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different task -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_taskNameContainsKeywords_returnsTrue() {
        // One keyword
        TaskNameContainsKeywordsPredicate predicate =
                new TaskNameContainsKeywordsPredicate(Collections.singletonList("TaskRandom"));
        assertTrue(predicate.test((new TaskBuilder().withTaskName("TaskRandom xyz").build())));

        // Multiple keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("TaskRandom", "Project"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));

        // Only one matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Project", "nothing"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("Nothing to do").build()));

        // Mixed-case keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("tAskRanDom", "pRojEct"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));

        // Uppercase keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("TASKRANDOM", "PROJECT"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));

        // Different keywords order
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("Project", "TaskRandom"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));

        // Partial keywords
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("ject", "rand"));
        assertTrue(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));
    }

    @Test
    public void test_taskNameDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TaskNameContainsKeywordsPredicate predicate = new TaskNameContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new TaskBuilder().withTaskName("TaskRandom").build()));

        // Non-matching keyword
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("unrelated"));
        assertFalse(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").build()));

        // Keywords match due date and status, but does not match name
        predicate = new TaskNameContainsKeywordsPredicate(Arrays.asList("2018-04-20", "undone"));
        assertFalse(predicate.test(new TaskBuilder().withTaskName("TaskRandom Project").withTaskDueDate("2018-04-20")
                .withTaskStatus("undone").build()));
    }

}
```
###### \java\seedu\address\model\task\TaskNameTest.java
``` java
public class TaskNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskName(null));
    }

    @Test
    public void constructor_invalidTaskName_throwsIllegalArgumentException() {
        String invalidName = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskName(invalidName));
    }

    @Test
    public void isValidTaskName() {
        // null task name
        Assert.assertThrows(NullPointerException.class, () -> TaskName.isValidTaskName(null));

        // invalid task name
        assertFalse(TaskName.isValidTaskName("")); // empty string
        assertFalse(TaskName.isValidTaskName(" ")); // spaces only
        assertFalse(TaskName.isValidTaskName("^")); // only non-alphanumeric characters
        assertFalse(TaskName.isValidTaskName("task*one")); // contains non-alphanumeric characters

        // valid task name
        assertTrue(TaskName.isValidTaskName("task random")); // alphabets only
        assertTrue(TaskName.isValidTaskName("12345")); // numbers only
        assertTrue(TaskName.isValidTaskName("task random 5")); // alphanumeric characters
        assertTrue(TaskName.isValidTaskName("Task Fabulous")); // with capital letters
        assertTrue(TaskName.isValidTaskName("Super Tough Task With More Than 100 hours Spent")); // long names
    }

}
```
###### \java\seedu\address\model\task\TaskPriorityTest.java
``` java
public class TaskPriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskPriority(null));
    }

    @Test
    public void constructor_inValidTaskPriority_throwsIllegalArgumentException() {
        String invalidPriority = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskPriority(invalidPriority));
    }

    @Test
    public void isValidTaskPriority() {
        // null task priority
        Assert.assertThrows(NullPointerException.class, () -> TaskPriority.isValidTaskPriority(null));

        // invalid task priority
        assertFalse(TaskPriority.isValidTaskPriority("")); // empty string
        assertFalse(TaskPriority.isValidTaskPriority(" ")); // spaces only
        assertFalse(TaskPriority.isValidTaskPriority("random xyz")); // random string
        assertFalse(TaskPriority.isValidTaskPriority("12345")); // numbers
        assertFalse(TaskPriority.isValidTaskPriority("random 123")); // alphanumeric

        // valid task priority (high, medium, low)
        assertTrue(TaskPriority.isValidTaskPriority("high"));
        assertTrue(TaskPriority.isValidTaskPriority("medium"));
        assertTrue(TaskPriority.isValidTaskPriority("low"));
    }

}
```
###### \java\seedu\address\model\task\TaskStatusTest.java
``` java
public class TaskStatusTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new TaskStatus(null));
    }

    @Test
    public void constructor_invalidTaskStatus_throwsIllegalArgumentException() {
        String invalidStatus = "";
        Assert.assertThrows(IllegalArgumentException.class, () -> new TaskStatus(invalidStatus));
    }

    @Test
    public void isValidTaskStatus() {
        // null task status
        Assert.assertThrows(NullPointerException.class, () -> TaskStatus.isValidTaskStatus(null));

        // invalid task status
        assertFalse(TaskStatus.isValidTaskStatus("")); // empty string
        assertFalse(TaskStatus.isValidTaskStatus(" ")); // spaces only
        assertFalse(TaskStatus.isValidTaskStatus("random xyz")); // random string
        assertFalse(TaskStatus.isValidTaskStatus("12345")); // numbers
        assertFalse(TaskStatus.isValidTaskStatus("random 123")); // alphanumeric

        // valid task status (done or undone)
        assertTrue(TaskStatus.isValidTaskStatus("done"));
        assertTrue(TaskStatus.isValidTaskStatus("undone"));
    }

}

```
###### \java\seedu\address\model\UniquePersonListTest.java
``` java
    @Test
    public void sort_ascendingOrder_success() {
        // Setup actual result
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBook();
        addressBook.sortPersons();
        ObservableList<Person> actualPersonList = addressBook.getPersonList();

        // Setup expected result
        List<Person> personList = TypicalPersons.getTypicalPersons();
        personList.sort((person1, person2) -> person1.getName().toString()
                .compareToIgnoreCase(person2.getName().toString()));
        ObservableList<Person> expectedPersonList = FXCollections.observableList(personList);

        assertEquals(actualPersonList, expectedPersonList);
    }

    @Test
    public void sort_descendingOrder_fail() {
        // Setup actual result
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBook();
        addressBook.sortPersons();
        ObservableList<Person> actualPersonList = addressBook.getPersonList();

        // Setup expected result
        List<Person> personList = TypicalPersons.getTypicalPersons();
        personList.sort((person1, person2) -> person2.getName().toString()
                .compareToIgnoreCase(person1.getName().toString()));
        ObservableList<Person> expectedPersonList = FXCollections.observableList(personList);

        assertNotEquals(actualPersonList, expectedPersonList);
    }
}
```
###### \java\seedu\address\model\UniqueTaskCategoryListTest.java
``` java
public class UniqueTaskCategoryListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueTaskCategoryList uniqueTaskCategoryList = new UniqueTaskCategoryList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueTaskCategoryList.asObservableList().remove(0);
    }
}
```
###### \java\seedu\address\model\UniqueTaskListTest.java
``` java
public class UniqueTaskListTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        UniqueTaskList uniqueTaskList = new UniqueTaskList();
        thrown.expect(UnsupportedOperationException.class);
        uniqueTaskList.asObservableList().remove(0);
    }

    @Test
    public void sortByPriority_decreasingOrder_success() {
        // Setup actual result
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBook();
        addressBook.sortTasksByPriority();
        ObservableList<Task> actualTaskList = addressBook.getTaskList();

        // Setup expected result
        List<Task> taskList = TypicalTasks.getTypicalTasks();

        // Custom comparator to sort based on high to low priority
        taskList.sort(Comparator.comparing(Task::getTaskPriority, (t1, t2) -> {
            return TaskPriority.PRIORITY_ORDER.indexOf(t1.value.toLowerCase())
                    - TaskPriority.PRIORITY_ORDER.indexOf(t2.value.toLowerCase());
        }));

        ObservableList<Task> expectedTaskList = FXCollections.observableList(taskList);

        assertEquals(actualTaskList, expectedTaskList);
    }

    @Test
    public void sortByPriority_ascendingOrder_fail() {
        // Setup actual result
        AddressBook addressBook = TypicalAddressBook.getTypicalAddressBook();
        addressBook.sortTasksByPriority();
        ObservableList<Task> actualTaskList = addressBook.getTaskList();

        // Setup expected result
        List<Task> taskList = TypicalTasks.getTypicalTasks();

        // Custom comparator to sort based on low to high priority
        taskList.sort(Comparator.comparing(Task::getTaskPriority, (t1, t2) -> {
            return TaskPriority.PRIORITY_ORDER.indexOf(t1.value.toLowerCase())
                    - TaskPriority.PRIORITY_ORDER.indexOf(t2.value.toLowerCase());
        }).reversed());

        ObservableList<Task> expectedTaskList = FXCollections.observableList(taskList);

        assertNotEquals(actualTaskList, expectedTaskList);
    }
}
```
###### \java\seedu\address\storage\XmlAddressBookStorageTest.java
``` java
    @Test
    public void readAddressBook_invalidTaskAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAddressBook("invalidTaskAddressBook.xml");
    }

```
###### \java\seedu\address\storage\XmlAddressBookStorageTest.java
``` java
    @Test
    public void readAddressBook_invalidAndValidTaskAddressBook_throwDataConversionException() throws Exception {
        thrown.expect(DataConversionException.class);
        readAddressBook("invalidAndValidTaskAddressBook.xml");
    }

```
###### \java\seedu\address\storage\XmlSerializableAddressBookTest.java
``` java
    @Test
    public void toModelType_invalidTaskFile_throwsIllegalValueException() throws Exception {
        XmlSerializableAddressBook dataFromFile = XmlUtil.getDataFromFile(INVALID_TASK_FILE,
                XmlSerializableAddressBook.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }

    @Test
    public void toModelType_invalidTaskCategoryFile_throwsIllegalValueException() throws Exception {
        XmlSerializableAddressBook dataFromFile = XmlUtil.getDataFromFile(INVALID_TASK_CATEGORY_FILE,
                XmlSerializableAddressBook.class);
        thrown.expect(IllegalValueException.class);
        dataFromFile.toModelType();
    }
}
```
###### \java\seedu\address\testutil\EditTaskDescriptorBuilder.java
``` java
/**
 * A utility class to help with building EditTaskDescriptor objects.
 */
public class EditTaskDescriptorBuilder {

    private EditTaskDescriptor descriptor;

    public EditTaskDescriptorBuilder() {
        descriptor = new EditTaskDescriptor();
    }

    public EditTaskDescriptorBuilder(EditTaskDescriptor descriptor) {
        this.descriptor = new EditTaskDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditTaskDescriptor} with fields containing {@code task}'s details.
     */
    public EditTaskDescriptorBuilder(Task task) {
        descriptor = new EditTaskDescriptor();
        descriptor.setTaskName((task.getTaskName()));
        descriptor.setTaskPriority(task.getTaskPriority());
        descriptor.setTaskDescription(task.getTaskDescription());
        descriptor.setTaskDueDate(task.getTaskDueDate());
        descriptor.setTaskStatus(task.getTaskStatus());
        descriptor.setTaskCategories(task.getTaskCategories());
    }

    /**
     * Sets the {@code TaskName} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskName(String taskName) {
        descriptor.setTaskName(new TaskName(taskName));
        return this;
    }

    /**
     * Sets the {@code TaskPriority} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskPriority(String taskPriority) {
        descriptor.setTaskPriority(new TaskPriority(taskPriority));
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDescription(String taskDescription) {
        descriptor.setTaskDescription(new TaskDescription(taskDescription));
        return this;
    }

    /**
     * Sets the {@code TaskDueDate} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskDueDate(String taskDueDate) {
        descriptor.setTaskDueDate(new TaskDueDate(taskDueDate));
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code EditTaskDescriptor} that we are building.
     */
    public EditTaskDescriptorBuilder withTaskStatus(String taskStatus) {
        descriptor.setTaskStatus(new TaskStatus(taskStatus));
        return this;
    }

    /**
     * Parses the {@code taskCategories} into a {@code Set<TaskCategory>} and set it to the {@code EditTaskDescriptor}
     * that we are building.
     */
    public EditTaskDescriptorBuilder withTaskCategories(String... taskCategories) {
        Set<TaskCategory> taskCategorySet =
                Stream.of(taskCategories).map(TaskCategory::new).collect(Collectors.toSet());
        descriptor.setTaskCategories(taskCategorySet);
        return this;
    }

    public EditTaskDescriptor build() {
        return descriptor;
    }

}
```
###### \java\seedu\address\testutil\TaskBuilder.java
``` java
/**
 * A utility class to help with building Task objects.
 */
public class TaskBuilder {

    public static final String DEFAULT_TASK_NAME = "TASK ONE";
    public static final String DEFAULT_TASK_PRIORITY = "medium";
    public static final String DEFAULT_TASK_DESCRIPTION = "Sample task description";
    public static final String DEFAULT_TASK_DUE_DATE = "2018-10-10";
    public static final String DEFAULT_TASK_STATUS = "undone";
    public static final String DEFAULT_TASK_CATEGORIES = "Work";

    private TaskName taskName;
    private TaskPriority taskPriority;
    private TaskDescription taskDescription;
    private TaskDueDate taskDueDate;
    private TaskStatus taskStatus;
    private Set<TaskCategory> taskCategories;

    public TaskBuilder() {
        taskName = new TaskName(DEFAULT_TASK_NAME);
        taskPriority = new TaskPriority(DEFAULT_TASK_PRIORITY);
        taskDescription = new TaskDescription(DEFAULT_TASK_DESCRIPTION);
        taskDueDate = new TaskDueDate(DEFAULT_TASK_DUE_DATE);
        taskStatus = new TaskStatus(DEFAULT_TASK_STATUS);
        taskCategories = SampleDataUtil.getTaskCategorySet(DEFAULT_TASK_CATEGORIES);
    }

    /**
     * Initializes the TaskBuilder with the data of {@code taskToCopy}.
     */
    public TaskBuilder(Task taskToCopy) {
        taskName = taskToCopy.getTaskName();
        taskPriority = taskToCopy.getTaskPriority();
        taskDescription = taskToCopy.getTaskDescription();
        taskDueDate = taskToCopy.getTaskDueDate();
        taskStatus = taskToCopy.getTaskStatus();
        taskCategories = new HashSet<>(taskToCopy.getTaskCategories());
    }

    /**
     * Sets the {@code TaskName} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskName(String taskName) {
        this.taskName = new TaskName(taskName);
        return this;
    }

    /**
     * Sets the {@code TaskPriority} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskPriority(String taskPriority) {
        this.taskPriority = new TaskPriority(taskPriority);
        return this;
    }

    /**
     * Sets the {@code TaskDescription} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDescription(String taskDescription) {
        this.taskDescription = new TaskDescription(taskDescription);
        return this;
    }

    /**
     * Sets the {@code TaskDueDate} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskDueDate(String taskDueDate) {
        this.taskDueDate = new TaskDueDate(taskDueDate);
        return this;
    }

    /**
     * Sets the {@code TaskStatus} of the {@code Task} that we are building.
     */
    public TaskBuilder withTaskStatus(String taskStatus) {
        this.taskStatus = new TaskStatus(taskStatus);
        return this;
    }

    /**
     * Parses the {@code taskCategories} into a {@code Set<TaskCategory>} and
     * set it to the {@code Task} that we are building.
     */
    public TaskBuilder withTaskCategories(String ... taskCategories) {
        this.taskCategories = SampleDataUtil.getTaskCategorySet(taskCategories);
        return this;
    }

    public Task build() {
        return new Task(taskName, taskPriority, taskDescription, taskDueDate, taskStatus, taskCategories);
    }

}
```
###### \java\seedu\address\testutil\TaskUtil.java
``` java
/**
 * A utility class for Task.
 */
public class TaskUtil {

    /**
     * Returns a taskAdd command string for adding the {@code task}.
     */
    public static String getTaskAddCommand(Task task) {
        return TaskAddCommand.COMMAND_WORD + " " + getTaskDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code task}'s details.
     */
    public static String getTaskDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TASK_NAME + task.getTaskName().value + " ");
        sb.append(PREFIX_TASK_PRIORITY + task.getTaskPriority().value + " ");
        sb.append(PREFIX_TASK_DESCRIPTION + task.getTaskDescription().value + " ");
        sb.append(PREFIX_TASK_DUE_DATE + task.getTaskDueDate().value + " ");
        sb.append(PREFIX_TASK_STATUS + task.getTaskStatus().value + " ");
        task.getTaskCategories().stream().forEach(s -> sb.append(PREFIX_TASK_CATEGORY + s.taskCategoryName + " "));
        return sb.toString();
    }

}
```
###### \java\seedu\address\testutil\TypicalAddressBook.java
``` java
/**
 * A utility class containing a list of {@code AddressBook} objects to be used in tests.
 */
public class TypicalAddressBook {

    private TypicalAddressBook() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons and typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : TypicalPersons.getTypicalPersons()) {
            try {
                ab.addPerson(person);
            } catch (DuplicatePersonException e) {
                throw new AssertionError("not possible");
            }
        }
        for (Task task : TypicalTasks.getTypicalTasks()) {
            try {
                ab.addTask(task);
            } catch (DuplicateTaskException e) {
                throw new AssertionError("not possible");
            }
        }
        return ab;
    }

}
```
###### \java\seedu\address\testutil\TypicalTasks.java
``` java
/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task TASKONE = new TaskBuilder().withTaskName("TaskOne")
            .withTaskPriority("medium")
            .withTaskDescription("Tasks to be done for task 1")
            .withTaskDueDate("2018-06-15")
            .withTaskStatus("undone")
            .withTaskCategories("Work").build();
    public static final Task TASKTWO = new TaskBuilder().withTaskName("TaskTwo")
            .withTaskPriority("high")
            .withTaskDescription("Agenda for task 2")
            .withTaskDueDate("2018-03-28")
            .withTaskStatus("undone")
            .withTaskCategories("Personal").build();
    public static final Task TASKTHREE = new TaskBuilder().withTaskName("TaskThree")
            .withTaskPriority("low")
            .withTaskDescription("Purchase office supplies")
            .withTaskDueDate("2018-04-10")
            .withTaskStatus("undone")
            .withTaskCategories("Work").build();

    // Manuall added - Task's details found in {@code CommandTestUtil}
    public static final Task TASKFIRST = new TaskBuilder().withTaskName(VALID_TASK_NAME_TASKFIRST)
            .withTaskPriority(VALID_TASK_PRIORITY_TASKFIRST).withTaskDescription(VALID_TASK_DESCRIPTION_TASKFIRST)
            .withTaskDueDate(VALID_TASK_DUE_DATE_TASKFIRST).withTaskStatus(VALID_TASK_STATUS_TASKFIRST)
            .withTaskCategories(VALID_TASK_CATEGORY_WORK).build();
    public static final Task TASKSECOND = new TaskBuilder().withTaskName(VALID_TASK_NAME_TASKSECOND)
            .withTaskPriority(VALID_TASK_PRIORITY_TASKSECOND).withTaskDescription(VALID_TASK_DESCRIPTION_TASKSECOND)
            .withTaskDueDate(VALID_TASK_DUE_DATE_TASKSECOND).withTaskStatus(VALID_TASK_STATUS_TASKSECOND)
            .withTaskCategories(VALID_TASK_CATEGORY_PERSONAL).build();

    // Manually added
    public static final Task TASKFOUR = new TaskBuilder().withTaskName("Task 4")
            .withTaskPriority("medium")
            .withTaskDescription("Work in progress: Development and documentation")
            .withTaskDueDate("2018-05-10")
            .withTaskStatus("undone").build();

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TASKONE, TASKTWO, TASKTHREE));
    }

}
```
###### \java\seedu\address\ui\TaskCardTest.java
``` java
public class TaskCardTest extends GuiUnitTest {

    @Test
    public void display() {
        // no task categories
        Task taskWithNoCategories = new TaskBuilder().withTaskCategories(new String[0]).build();
        TaskCard taskCard = new TaskCard(taskWithNoCategories, 1);
        uiPartRule.setUiPart(taskCard);
        assertCardDisplay(taskCard, taskWithNoCategories, 1);

        // with task categories
        Task taskWithCategories = new TaskBuilder().build();
        taskCard = new TaskCard(taskWithCategories, 2);
        uiPartRule.setUiPart(taskCard);
        assertCardDisplay(taskCard, taskWithCategories, 2);
    }

    @Test
    public void equals() {
        Task task = new TaskBuilder().build();
        TaskCard taskCard = new TaskCard(task, 0);

        // same task, same index -> returns true
        TaskCard copy = new TaskCard(task, 0);
        assertTrue(taskCard.equals(copy));

        // same object -> returns true
        assertTrue(taskCard.equals(taskCard));

        // null -> returns false
        assertFalse(taskCard.equals(null));

        // different types -> returns false
        assertFalse(taskCard.equals(0));

        //different task, same index -> returns false
        Task differentTask = new TaskBuilder().withTaskName("differentTaskName").build();
        assertFalse(taskCard.equals(new TaskCard(differentTask, 0)));

        // same task, different index -> returns false
        assertFalse(taskCard.equals(new TaskCard(task, 1)));
    }

    /**
     * Asserts that {@code taskCard} displays the details of {@code expectedTask} correctly and matches
     * {@code expectedId}.
     */
    private void assertCardDisplay(TaskCard taskCard, Task expectedTask, int expectedId) {
        guiRobot.pauseForHuman();

        TaskCardHandle taskCardHandle = new TaskCardHandle((taskCard.getRoot()));

        // Verify id is displaed correctly
        assertEquals(Integer.toString(expectedId) + ". ", taskCardHandle.getTaskId());

        // Verify task details are displayed correctly
        assertCardDisplaysTask(expectedTask, taskCardHandle);
    }

}
```
###### \java\seedu\address\ui\TaskListPanelTest.java
``` java
public class TaskListPanelTest extends GuiUnitTest {
    private static final ObservableList<Task> TYPICAL_TASKS = FXCollections.observableList(getTypicalTasks());

    private TaskListPanelHandle taskListPanelHandle;

    @Before
    public void setUp() {
        TaskListPanel taskListPanel = new TaskListPanel(TYPICAL_TASKS);
        uiPartRule.setUiPart(taskListPanel);

        taskListPanelHandle = new TaskListPanelHandle(getChildNode(taskListPanel.getRoot(),
                TaskListPanelHandle.TASK_LIST_VIEW_ID));
    }

    @Test
    public void display() {
        for (int i = 0; i < TYPICAL_TASKS.size(); i++) {
            Task expectedTask = TYPICAL_TASKS.get(i);
            TaskCardHandle actualCard = taskListPanelHandle.getTaskCardHandle(i);

            assertCardDisplaysTask(expectedTask, actualCard);
            assertEquals(Integer.toString(i + 1) + ". ", actualCard.getTaskId());
        }
    }
}
```
###### \java\systemtests\SampleDataTest.java
``` java
    @Test
    public void addressBook_taskDataFileDoesNotExist_loadSampleData() {
        Task[] expectedTaskList = SampleDataUtil.getSampleTasks();
        assertTaskListMatching(getTaskListPanel(), expectedTaskList);
    }
}
```
