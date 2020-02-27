package seedu.jelphabot.logic.commands;

import seedu.jelphabot.commons.core.Messages;
import seedu.jelphabot.commons.core.index.Index;
import seedu.jelphabot.logic.commands.exceptions.CommandException;
import seedu.jelphabot.model.Model;
import seedu.jelphabot.model.task.Remark;
import seedu.jelphabot.model.task.Task;

import java.util.List;

import static seedu.jelphabot.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.jelphabot.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.jelphabot.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Changes the remark of an existing person in the address book.
 */
public class RemarkCommand extends Command {
    public static final String COMMAND_WORD = "remark";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the remark of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing remark will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMARK + "r/ [REMARK]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "r/ Likes to swim.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Remark: %2$s";

    public static final String MESSAGE_ADD_REMARK_SUCCESS = "Added remark to Person: %1$s";
    public static final String MESSAGE_DELETE_REMARK_SUCCESS = "Removed remark from Person: %1$s";

    private final Index index;
    private final Remark remark;

    public RemarkCommand(Index index, Remark remark) {
        requireAllNonNull(index, remark);

        this.index = index;
        this.remark = remark;
   }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Task> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = new Task(taskToEdit.getDescription(), taskToEdit.getStatus(), taskToEdit.getDateTime(), taskToEdit.getModuleCode(), remark, taskToEdit.getTags());

        model.setPerson(taskToEdit, editedTask);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedTask));
    }

    /**
     * Generates a command execution success message based on whether the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Task taskToEdit) {
        String message = !remark.value.isEmpty() ? MESSAGE_ADD_REMARK_SUCCESS : MESSAGE_DELETE_REMARK_SUCCESS;
        return String.format(message, taskToEdit);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // handle null using instanceof
        if (!(other instanceof RemarkCommand)) {
            return false;
        }

        // state check
        RemarkCommand r = (RemarkCommand) other;
        return index.equals(r.index) && remark.equals(r.remark);
    }

}
