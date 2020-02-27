package seedu.jelphabot.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.testutil.Assert.assertThrows;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Description.isValidName(null));

        // invalid name
        assertFalse(Description.isValidName("")); // empty string
        assertFalse(Description.isValidName(" ")); // spaces only
        assertFalse(Description.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Description.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Description.isValidName("peter jack")); // alphabets only
        assertTrue(Description.isValidName("12345")); // numbers only
        assertTrue(Description.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Description.isValidName("Capital Tan")); // with capital letters
        assertTrue(Description.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
