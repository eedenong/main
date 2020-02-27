package seedu.jelphabot.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.jelphabot.testutil.Assert.assertThrows;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidEmail_throwsIllegalArgumentException() {
        String invalidEmail = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidEmail));
    }

    @Test
    public void isValidEmail() {
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidEmail(null));

        // blank module code
        assertFalse(ModuleCode.isValidEmail("")); // empty string
        assertFalse(ModuleCode.isValidEmail(" ")); // spaces only

        // missing parts
        assertFalse(ModuleCode.isValidEmail("@example.com")); // missing local part
        assertFalse(ModuleCode.isValidEmail("peterjackexample.com")); // missing '@' symbol
        assertFalse(ModuleCode.isValidEmail("peterjack@")); // missing domain name

        // invalid parts
        assertFalse(ModuleCode.isValidEmail("peterjack@-")); // invalid domain name
        assertFalse(ModuleCode.isValidEmail("peterjack@exam_ple.com")); // underscore in domain name
        assertFalse(ModuleCode.isValidEmail("peter jack@example.com")); // spaces in local part
        assertFalse(ModuleCode.isValidEmail("peterjack@exam ple.com")); // spaces in domain name
        assertFalse(ModuleCode.isValidEmail(" peterjack@example.com")); // leading space
        assertFalse(ModuleCode.isValidEmail("peterjack@example.com ")); // trailing space
        assertFalse(ModuleCode.isValidEmail("peterjack@@example.com")); // double '@' symbol
        assertFalse(ModuleCode.isValidEmail("peter@jack@example.com")); // '@' symbol in local part
        assertFalse(ModuleCode.isValidEmail("peterjack@example@com")); // '@' symbol in domain name
        assertFalse(ModuleCode.isValidEmail("peterjack@.example.com")); // domain name starts with a period
        assertFalse(ModuleCode.isValidEmail("peterjack@example.com.")); // domain name ends with a period
        assertFalse(ModuleCode.isValidEmail("peterjack@-example.com")); // domain name starts with a hyphen
        assertFalse(ModuleCode.isValidEmail("peterjack@example.com-")); // domain name ends with a hyphen

        // valid module code
        assertTrue(ModuleCode.isValidEmail("PeterJack_1190@example.com"));
        assertTrue(ModuleCode.isValidEmail("a@bc")); // minimal
        assertTrue(ModuleCode.isValidEmail("test@localhost")); // alphabets only
        assertTrue(ModuleCode.isValidEmail("!#$%&'*+/=?`{|}~^.-@example.org")); // special characters local part
        assertTrue(ModuleCode.isValidEmail("123@145")); // numeric local part and domain name
        assertTrue(ModuleCode.isValidEmail("a1+be!@example1.com")); // mixture of alphanumeric and special characters
        assertTrue(ModuleCode.isValidEmail("peter_jack@very-very-very-long-example.com")); // long domain name
        assertTrue(ModuleCode.isValidEmail("if.you.dream.it_you.can.do.it@example.com")); // long local part
    }
}
