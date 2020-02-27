package seedu.jelphabot.testutil;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static seedu.jelphabot.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Task ALICE = new PersonBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withTags("friends").build();
    public static final Task BENSON = new PersonBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com")
            .withTags("owesMoney", "friends").build();
    public static final Task CARL = new PersonBuilder().withName("Carl Kurz")
            .withEmail("heinz@example.com").build();
    public static final Task DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Task ELLE = new PersonBuilder().withName("Elle Meyer")
            .withEmail("werner@example.com").build();
    public static final Task FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withEmail("lydia@example.com").build();
    public static final Task GEORGE = new PersonBuilder().withName("George Best")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Task HOON = new PersonBuilder().withName("Hoon Meier")
            .withEmail("stefan@example.com").build();
    public static final Task IDA = new PersonBuilder().withName("Ida Mueller")
            .withEmail("hans@example.com").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Task AMY = new PersonBuilder().withName(VALID_NAME_AMY)
            .withEmail(VALID_MODULE_CODE_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Task BOB = new PersonBuilder().withName(VALID_NAME_BOB)
            .withEmail(VALID_MODULE_CODE_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code JelphaBot} with all the typical persons.
     */
    public static JelphaBot getTypicalJelphaBot() {
        JelphaBot ab = new JelphaBot();
        for (Task task : getTypicalPersons()) {
            ab.addPerson(task);
        }
        return ab;
    }

    public static List<Task> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
