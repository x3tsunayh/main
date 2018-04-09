package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.category.TaskCategory;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Person> getPersonList();

    /**
     * Returns an unmodifiable view of the tags list.
     * This list will not contain any duplicate tags.
     */
    ObservableList<Tag> getTagList();

    /**
     * Returns an unmodifiable view of the tasks list before sorting.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getOriginalTaskList();

    /**
     * Returns an unmodifiable view of the tasks list sorted by task status (i.e. undone and done)
     * followed by taskDueDate.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();

    /**
     * Returns an unmodifiable view of the taskCategories list.
     * This list will not contain any duplicate taskCategories.
     */
    ObservableList<TaskCategory> getTaskCategoryList();

}
