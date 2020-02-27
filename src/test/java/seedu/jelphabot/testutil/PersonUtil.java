package seedu.jelphabot.testutil;

import seedu.jelphabot.logic.commands.AddCommand;
import seedu.jelphabot.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Task;

import java.util.Set;

import static seedu.jelphabot.logic.parser.CliSyntax.*;

/**
 * A utility class for Person.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Task task) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(task);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getPersonDetails(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + task.getDescription().fullDescription + " ");
        sb.append(PREFIX_PHONE + task.getPhone().value + " ");
        sb.append(PREFIX_MODULE_CODE + task.getModuleCode().value + " ");
        task.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getDescription().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullDescription).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getModuleCode().ifPresent(moduleCode -> sb.append(PREFIX_MODULE_CODE).append(moduleCode.value).append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
