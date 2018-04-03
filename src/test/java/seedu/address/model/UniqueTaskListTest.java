package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Comparator;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskPriority;
import seedu.address.model.task.UniqueTaskList;
import seedu.address.testutil.TypicalAddressBook;
import seedu.address.testutil.TypicalTasks;

//@@author CYX28
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
