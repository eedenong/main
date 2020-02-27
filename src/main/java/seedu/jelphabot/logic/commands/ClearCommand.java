package seedu.jelphabot.logic.commands;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.Model;

import static java.util.Objects.requireNonNull;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "JelphaBot has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setJelphaBot(new JelphaBot());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
