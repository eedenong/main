package seedu.jelphabot.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.task.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An Immutable JelphaBot that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableJelphaBot {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableJelphaBot} with the given tasks.
     */
    @JsonCreator
    public JsonSerializableJelphaBot(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyJelphaBot} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableJelphaBot}.
     */
    public JsonSerializableJelphaBot(ReadOnlyJelphaBot source) {
        tasks.addAll(source.getPersonList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code JelphaBot} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public JelphaBot toModelType() throws IllegalValueException {
        JelphaBot jelphaBot = new JelphaBot();
        for (JsonAdaptedTask jsonAdaptedPerson : tasks) {
            Task task = jsonAdaptedPerson.toModelType();
            if (jelphaBot.hasPerson(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            jelphaBot.addPerson(task);
        }
        return jelphaBot;
    }

}
