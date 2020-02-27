package seedu.jelphabot.testutil;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.task.Task;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code JelphaBot ab = new JelphaBotBuilder().withPerson("John", "Doe").build();}
 */
public class JelphaBotBuilder {

    private JelphaBot addressBook;

    public JelphaBotBuilder() {
        addressBook = new JelphaBot();
    }

    public JelphaBotBuilder(JelphaBot addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code JelphaBot} that we are building.
     */
    public JelphaBotBuilder withPerson(Task task) {
        addressBook.addPerson(task);
        return this;
    }

    public JelphaBot build() {
        return addressBook;
    }
}
