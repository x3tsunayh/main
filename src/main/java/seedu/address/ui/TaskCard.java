package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.commons.util.DateUtil;
import seedu.address.model.task.Task;

//@@author CYX28
/**
 * An UI Component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {
    private static final String FXML = "TaskListCard.fxml";

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private Label description;
    @FXML
    private FlowPane categories;
    @FXML
    private HBox statusPane;
    @FXML
    private ImageView statusImage;
    @FXML
    private HBox dueDatePane;
    @FXML
    private ImageView dueDateImage;
    @FXML
    private Label dueDate;
    @FXML
    private HBox priorityPane;
    @FXML
    private ImageView priorityImage;
    @FXML
    private Label priority;

    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        task.getTaskCategories().forEach(category -> categories.getChildren()
                .add(new Label(category.taskCategoryName)));

        setTaskNameStyle(task.getTaskName().value);
        setTaskDescriptionStyle(task.getTaskDescription().value);
        setTaskPriorityDueDateAndStatusStyle(task.getTaskPriority().value, task.getTaskDueDate().value,
                task.getTaskStatus().value);
    }

    /**
     * Set the style for task name field
     * @param taskName name of task
     */
    private void setTaskNameStyle(String taskName) {
        name.setText(task.getTaskName().value);
        name.setWrapText(true);
    }

    /**
     * Set the style for task description field
     * @param taskDescription description of task
     */
    private void setTaskDescriptionStyle(String taskDescription) {
        description.setText(task.getTaskDescription().value);
        description.setWrapText(true);
    }

    /**
     * Set the style for task priority, due date and status fields
     * @param taskPriority priority level
     * @param taskDueDate due date
     * @param taskStatus status
     */
    private void setTaskPriorityDueDateAndStatusStyle(String taskPriority, String taskDueDate, String taskStatus) {
        if (taskStatus.equals("done")) {
            setTaskPriorityLabelText("");
            setTaskDueDateLabelText("");
            setTaskStatusImageForDoneTasks("images/taskStatusDone.png", 50, 50);
        } else { // undone tasks
            setTaskPriorityStyleForUndoneTasks(taskPriority);
            setTaskDueDateStyleForUndoneTasks(taskDueDate);
        }
    }

    /**
     * Set the task priority field style for undone tasks
     * @param taskPriority priority level
     */
    private void setTaskPriorityStyleForUndoneTasks(String taskPriority) {
        if (taskPriority.equals("high")) {
            setTaskPriorityPaneStyle(68, "#FF0000");
            setTaskPriorityImage("images/taskPriorityHigh.png");

        } else if (taskPriority.equals("medium")) {
            setTaskPriorityPaneStyle(88, "#FFC000");
            setTaskPriorityImage("images/taskPriorityMedium.png");
        } else {
            setTaskPriorityPaneStyle(65, "#00FF00");
            setTaskPriorityImage("images/taskPriorityLow.png");
        }
        setTaskPriorityLabelText(taskPriority.toUpperCase());
    }

    /**
     * Set the style for the task priority pane
     * @param maxWidth maximum width of the pane
     * @param colorCode color code of the border of the pane
     */
    private void setTaskPriorityPaneStyle(int maxWidth, String colorCode) {
        priorityPane.setMaxWidth(maxWidth);
        priorityPane.setStyle("-fx-border-color: " + colorCode + ";");
    }

    /**
     * Set the image to be displayed at task priority field
     * @param imageUrl url of the image to be displayed
     */
    private void setTaskPriorityImage(String imageUrl) {
        priorityImage.setImage(new Image(imageUrl, 15, 15, true, true));
    }

    /**
     * Set the text to be displayed at task priority label
     * @param taskPriority text to be displayed
     */
    private void setTaskPriorityLabelText(String taskPriority) {
        priority.setText(taskPriority);
    }

    /**
     * Set the task due date field style for undone tasks
     * - Set different text colors based on days left to task due date
     * (1) < 0 day (overdue tasks): red color text with circular exclamation symbol
     * (1) < 3 days (include overdue tasks): red color text
     * (2) 3 & 4 days: orange color text
     * (3) >= 5 days: green color text
     * - Set the label text of task due date field
     * @param taskDueDate due date of task
     */
    private void setTaskDueDateStyleForUndoneTasks(String taskDueDate) {
        int remainingDays = (int) DateUtil.getDayCountBetweenTwoDates(DateUtil.getTodayDate(),
                DateUtil.getParsedDate(taskDueDate));

        if (remainingDays < 0) { // overdue tasks
            dueDateImage.setImage(new Image("images/taskOverdue.png", 20, 20,
                    true, true));
            dueDate.setStyle("-fx-text-fill: #FF0000;");
        } else if (remainingDays < 3) {
            dueDate.setStyle("-fx-text-fill: #FF0000;");
        } else if (remainingDays < 5) {
            dueDate.setStyle("-fx-text-fill: #FFC000;");
        } else {
            dueDate.setStyle("-fx-text-fill: #00FF00;");
        }

        setTaskDueDateLabelText(taskDueDate);
    }

    /**
     * Set the text to be displayed at task due date label
     * @param taskDueDate text to be displayed
     */
    private void setTaskDueDateLabelText(String taskDueDate) {
        dueDate.setText(taskDueDate);
    }

    /**
     * Set the image of the task status field for completed tasks
     * @param taskStatusImageUrl url of the image to be displayed
     * @param width width of the image
     * @param height height of the image
     */
    private void setTaskStatusImageForDoneTasks(String taskStatusImageUrl, int width, int height) {
        statusImage.setImage(new Image(taskStatusImageUrl, width, height, true, true));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText()) && task.equals(card.task);
    }
}
