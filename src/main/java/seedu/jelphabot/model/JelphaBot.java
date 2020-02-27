package seedu.jelphabot.model;

import javafx.collections.ObservableList;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.model.task.UniqueTaskList;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSameTask comparison)
 */
public class JelphaBot implements ReadOnlyJelphaBot {

    private final UniqueTaskList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueTaskList();
    }

    public JelphaBot() {}

    /**
     * Creates an JelphaBot using the Persons in the {@code toBeCopied}
     */
    public JelphaBot(ReadOnlyJelphaBot toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Task> tasks) {
        this.persons.setPersons(tasks);
    }

    /**
     * Resets the existing data of this {@code JelphaBot} with {@code newData}.
     */
    public void resetData(ReadOnlyJelphaBot newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Task task) {
        requireNonNull(task);
        return persons.contains(task);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Task p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Task target, Task editedTask) {
        requireNonNull(editedTask);

        persons.setPerson(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code JelphaBot}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Task key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof JelphaBot // instanceof handles nulls
                && persons.equals(((JelphaBot) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
