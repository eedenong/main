package seedu.jelphabot.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.ModelManager;
import seedu.jelphabot.model.UserPrefs;
import seedu.jelphabot.model.task.Task;
import seedu.jelphabot.testutil.PersonBuilder;

import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.jelphabot.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.jelphabot.testutil.TypicalPersons.getTypicalJelphaBot;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalJelphaBot(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Task validTask = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getJelphaBot(), new UserPrefs());
        expectedModel.addPerson(validTask);

        assertCommandSuccess(new AddCommand(validTask), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validTask), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Task taskInList = model.getJelphaBot().getPersonList().get(0);
        assertCommandFailure(new AddCommand(taskInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
