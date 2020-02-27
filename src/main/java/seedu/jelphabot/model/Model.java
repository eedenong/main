package seedu.jelphabot.model;

import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.model.task.Task;

import java.nio.file.Path;
import java.util.function.Predicate;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getJelphaBotFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setJelphaBotFilePath(Path addressBookFilePath);

    /** Returns the JelphaBot */
    ReadOnlyJelphaBot getJelphaBot();

    /**
     * Replaces address book data with the data in {@code addressBook}.
     */
    void setJelphaBot(ReadOnlyJelphaBot addressBook);

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    boolean hasPerson(Task task);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePerson(Task target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPerson(Task task);

    /**
     * Replaces the given person {@code target} with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    void setPerson(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Task> getFilteredPersonList();

    /**
     * Updates the filter of the filtered person list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPersonList(Predicate<Task> predicate);
}
