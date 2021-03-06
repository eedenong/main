package seedu.jelphabot.ui;

import java.util.Comparator;

import javafx.beans.binding.NumberBinding;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.jelphabot.model.task.Priority;
import seedu.jelphabot.model.task.Task;

/**
 * An UI component that displays information of a {@code Task}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "GroupedTaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on JelphaBot level 4</a>
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label description;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label status;
    @FXML
    private Label timeSpent;
    @FXML
    private FlowPane tags;
    @FXML
    private Label dateTime;

    public TaskCard(Task task, NumberBinding displayedIndex) {
        super(FXML);
        this.task = task;
        setId(displayedIndex);
        populateTaskElements(task);
    }

    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        setId(displayedIndex);
        populateTaskElements(task);
    }

    private void setId(NumberBinding displayedIndex) {
        id.textProperty().bind(displayedIndex.asString("%d. "));
    }

    private void setId(int displayedIndex) {
        id.setText(String.format("%d. ", displayedIndex));
    }

    /**
     * Populates TaskCard with data from a model.
     * @param task the task containing model data for this TaskCard
     */
    private void populateTaskElements(Task task) {
        description.setText(task.getDescription().fullDescription);
        moduleCode.setText(task.getModuleCode().value);
        applyPriorityMarkdown(task);
        status.setText(task.getStatus().name());
        timeSpent.setText("(SPENT: " + task.getTimeSpent().toString() + ")");
        dateTime.setText(task.getDateTime().getDisplayValue());
        task.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

    }

    /**
     * Applies markdown to TaskCard based on Task priority
     * @param task the task containing model data for this TaskCard.
     */
    private void applyPriorityMarkdown(Task task) {
        description.getStyleClass().add("description");
        moduleCode.getStyleClass().add("description");
        if (task.getPriority().equals(Priority.HIGH)) {
            description.setId("highPriority");
            moduleCode.setId("highPriority");
        } else if (task.getPriority() == Priority.LOW) {
            description.setId("lowPriority");
            moduleCode.setId("lowPriority");
        }
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
        return id.getText().equals(card.id.getText())
                   && task.equals(card.task);
    }
}
