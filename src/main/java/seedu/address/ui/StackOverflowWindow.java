package seedu.address.ui;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;

//@@author x3tsunayh

/**
 * Controller for a stackoverflow page
 */
public class StackOverflowWindow extends UiPart<Stage> {

    public static final String STACKOVERFLOW_URL = "https://www.youtube.com/watch?v=dQw4w9WgXcQ";

    private static final Logger logger = LogsCenter.getLogger(StackOverflowWindow.class);
    private static final String FXML = "StackOverflowWindow.fxml";

    @FXML
    private WebView browser;

    /**
     * Creates a new StackOverflowWindow.
     *
     * @param root Stage to use as the root of the StackOverflowWindow.
     */
    public StackOverflowWindow(Stage root) {
        super(FXML, root);
        browser.getEngine().load(STACKOVERFLOW_URL);
    }

    /**
     * Creates a new StackOverflowWindow.
     */
    public StackOverflowWindow() {
        this(new Stage());
    }

    /**
     * Shows the stackoverflow window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing the stackoverflow page.");
        getRoot().show();
    }
}
