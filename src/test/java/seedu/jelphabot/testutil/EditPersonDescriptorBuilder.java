package seedu.jelphabot.testutil;

import seedu.jelphabot.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.Description;
import seedu.jelphabot.model.task.ModuleCode;
import seedu.jelphabot.model.task.Task;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
// TODO rewrite required
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Task task) {
        descriptor = new EditPersonDescriptor();
        descriptor.setDescription(task.getDescription());
        descriptor.setModuleCode(task.getModuleCode());
        descriptor.setTags(task.getTags());
    }

    /**
     * Sets the {@code Description} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withDescription(String name) {
        descriptor.setDescription(new Description(name));
        return this;
    }

//    /**
//     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
//     */
//    public EditPersonDescriptorBuilder withPhone(String phone) {
//        descriptor.setPhone(new Phone(phone));
//        return this;
//    }

    /**
     * Sets the {@code ModuleCode} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withModuleCode(String moduleCode) {
        descriptor.setModuleCode(new ModuleCode(moduleCode));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
