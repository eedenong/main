package seedu.jelphabot.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.jelphabot.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's module code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidModuleCode(String)}
 */
public class ModuleCode {

    // TODO rewrite constraints for module code
    public static final String MESSAGE_CONSTRAINTS = "Emails should be of the format [faculty][code][variant] "
            + "and adhere to the following constraints:\n"
            + "1. The faculty should only contain alphabetical characters.\n"
            + "2. This is followed by a reference code, and optionally a variant tag.\n"
            + "    - The reference code must be 4 digits long.\n"
            + "    - The variant tag is an optional alphabetical character.\n";
//    private static final String FACULTY_REGEX = "^[A-Za-z]+$";
//    private static final String REFERENCE_CODE = "\\d{4,}";
//    private static final String VARIANT_TAG = "^[A-Za-z]$";
//    public static final String VALIDATION_REGEX = FACULTY_REGEX + REFERENCE_CODE + VARIANT_TAG;
    // alphanumeric and special characters
    private static final String SPECIAL_CHARACTERS = "!#$%&'*+/=?`{|}~^.-";
    private static final String LOCAL_PART_REGEX = "^[\\w" + SPECIAL_CHARACTERS + "]+";
    private static final String DOMAIN_FIRST_CHARACTER_REGEX = "[^\\W_]"; // alphanumeric characters except underscore
    private static final String DOMAIN_MIDDLE_REGEX = "[a-zA-Z0-9.-]*"; // alphanumeric, period and hyphen
    private static final String DOMAIN_LAST_CHARACTER_REGEX = "[^\\W_]$";
    public static final String VALIDATION_REGEX = LOCAL_PART_REGEX + "@"
            + DOMAIN_FIRST_CHARACTER_REGEX + DOMAIN_MIDDLE_REGEX + DOMAIN_LAST_CHARACTER_REGEX;

    public final String value;

    /**
     * Constructs an {@code ModuleCode}.
     *
     * @param moduleCode code A valid module code address.
     */
    public ModuleCode(String moduleCode) {
        requireNonNull(moduleCode);
        checkArgument(isValidModuleCode(moduleCode), MESSAGE_CONSTRAINTS);
        value = moduleCode;
    }

    /**
     * Returns if a given string is a valid module code.
     */
    public static boolean isValidModuleCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ModuleCode // instanceof handles nulls
                && value.equals(((ModuleCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
