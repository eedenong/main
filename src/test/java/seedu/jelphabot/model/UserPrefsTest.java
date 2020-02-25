package seedu.jelphabot.model;

import org.junit.jupiter.api.Test;

import static seedu.jelphabot.testutil.Assert.assertThrows;

public class UserPrefsTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefs userPref = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setJelphaBotFilePath_nullPath_throwsNullPointerException() {
        UserPrefs userPrefs = new UserPrefs();
        assertThrows(NullPointerException.class, () -> userPrefs.setJelphaBotFilePath(null));
    }

}