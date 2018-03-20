package guitests.guihandles;

import javafx.stage.Stage;

/**
 * Provides a handle for {@code MainWindow}.
 */
public class MainWindowHandle extends StageHandle {

    private final PersonListPanelHandle personListPanel;
    private final ResultDisplayHandle resultDisplay;
    private final CommandBoxHandle commandBox;
    private final StatusBarFooterHandle statusBarFooter;
    private final MainMenuHandle mainMenu;
<<<<<<< HEAD
    private final BrowserPanelHandle browserPanel;
    private final TaskListPanelHandle taskListPanel;
=======
    //private final BrowserPanelHandle browserPanel;
>>>>>>> 9cddf0343a5e7cbe5f941aa1014656a642972479

    public MainWindowHandle(Stage stage) {
        super(stage);

        personListPanel = new PersonListPanelHandle(getChildNode(PersonListPanelHandle.PERSON_LIST_VIEW_ID));
        resultDisplay = new ResultDisplayHandle(getChildNode(ResultDisplayHandle.RESULT_DISPLAY_ID));
        commandBox = new CommandBoxHandle(getChildNode(CommandBoxHandle.COMMAND_INPUT_FIELD_ID));
        statusBarFooter = new StatusBarFooterHandle(getChildNode(StatusBarFooterHandle.STATUS_BAR_PLACEHOLDER));
        mainMenu = new MainMenuHandle(getChildNode(MainMenuHandle.MENU_BAR_ID));
<<<<<<< HEAD
        browserPanel = new BrowserPanelHandle(getChildNode(BrowserPanelHandle.BROWSER_ID));
        taskListPanel = new TaskListPanelHandle(getChildNode(TaskListPanelHandle.TASK_LIST_VIEW_ID));
=======
        //browserPanel = new BrowserPanelHandle(getChildNode(BrowserPanelHandle.BROWSER_ID));
>>>>>>> 9cddf0343a5e7cbe5f941aa1014656a642972479
    }

    public PersonListPanelHandle getPersonListPanel() {
        return personListPanel;
    }

    public ResultDisplayHandle getResultDisplay() {
        return resultDisplay;
    }

    public CommandBoxHandle getCommandBox() {
        return commandBox;
    }

    public StatusBarFooterHandle getStatusBarFooter() {
        return statusBarFooter;
    }

    public MainMenuHandle getMainMenu() {
        return mainMenu;
    }

    /*
    public BrowserPanelHandle getBrowserPanel() {
        return browserPanel;
    }
<<<<<<< HEAD

    public TaskListPanelHandle getTaskListPanel() {
        return taskListPanel;
    }
=======
    */
>>>>>>> 9cddf0343a5e7cbe5f941aa1014656a642972479
}
