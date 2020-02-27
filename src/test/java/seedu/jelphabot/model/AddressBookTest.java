package seedu.jelphabot.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.exceptions.DuplicatePersonException;
import seedu.jelphabot.testutil.PersonBuilder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.jelphabot.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalPersons.ALICE;
import static seedu.jelphabot.testutil.TypicalPersons.getTypicalJelphaBot;

public class JelphaBotTest {

    private final JelphaBot addressBook = new JelphaBot();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyJelphaBot_replacesData() {
        JelphaBot newData = getTypicalJelphaBot();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Task editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Task> newTasks = Arrays.asList(ALICE, editedAlice);
        JelphaBotStub newData = new JelphaBotStub(newTasks);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInJelphaBot_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInJelphaBot_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInJelphaBot_returnsTrue() {
        addressBook.addPerson(ALICE);
        Task editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    /**
     * A stub ReadOnlyJelphaBot whose persons list can violate interface constraints.
     */
    private static class JelphaBotStub implements ReadOnlyJelphaBot {
        private final ObservableList<Task> tasks = FXCollections.observableArrayList();

        JelphaBotStub(Collection<Task> tasks) {
            this.tasks.setAll(tasks);
        }

        @Override
        public ObservableList<Task> getPersonList() {
            return tasks;
        }
    }

}
