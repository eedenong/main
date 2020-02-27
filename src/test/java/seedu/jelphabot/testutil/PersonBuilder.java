package seedu.jelphabot.testutil;

import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;
import seedu.jelphabot.model.util.SampleDataUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * A utility class to help with building Person objects.
 */
// TODO rewrite class
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_MODULE_CODE = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "kek";

    private Description description;
    private ModuleCode moduleCode;
    private Remark remark;
    private Set<Tag> tags;

    public PersonBuilder() {
        description = new Description(DEFAULT_NAME);
        moduleCode = new ModuleCode(DEFAULT_MODULE_CODE);
        tags = new HashSet<>();
        remark = new Remark(DEFAULT_REMARK);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Task taskToCopy) {
        description = taskToCopy.getDescription();
        moduleCode = taskToCopy.getModuleCode();
        tags = new HashSet<>(taskToCopy.getTags());
        remark = taskToCopy.getRemark();
    }

    /**
     * Sets the {@code Description} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.description = new Description(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

//    /**
//     * Sets the {@code Phone} of the {@code Person} that we are building.
//     */
//    public PersonBuilder withPhone(String phone) {
//        this.phone = new Phone(phone);
//        return this;
//    }

    /**
     * Sets the {@code ModuleCode} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String moduleCode) {
        this.moduleCode = new ModuleCode(moduleCode);
        return this;
    }

    public PersonBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    public Task build() {
        return new Task(description, new Status(), new DateTime(), moduleCode, remark, tags);
    }

}
