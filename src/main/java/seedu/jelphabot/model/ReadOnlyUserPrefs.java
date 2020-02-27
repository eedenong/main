package seedu.jelphabot.model;

import seedu.jelphabot.commons.core.GuiSettings;

import java.nio.file.Path;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getJelphaBotFilePath();

}
