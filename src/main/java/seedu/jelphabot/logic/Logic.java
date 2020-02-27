package seedu.jelphabot.logic;

import javafx.collections.ObservableList;
import seedu.jelphabot.commons.core.GuiSettings;
import seedu.jelphabot.logic.commands.CommandResult;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.logic.parser.exceptions.ParseException;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.task.Task;

import java.nio.file.Path;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the JelphaBot.
     *
     * @see seedu.jelphabot.model.Model#getJelphaBot()
     */
    ReadOnlyJelphaBot getJelphaBot();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Task> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getJelphaBotFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
