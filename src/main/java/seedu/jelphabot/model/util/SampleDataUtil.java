package seedu.jelphabot.model.util;

import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.tag.Tag;
import seedu.jelphabot.model.task.*;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Contains utility methods for populating {@code JelphaBot} with sample data.
 */
// TODO update with new sample data
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");

    public static Task[] getSamplePersons() {
        return new Task[] {
            new Task(new Description("Alex Yeoh"), new Status(), new DateTime(), new ModuleCode("alexyeoh@example.com"),
                EMPTY_REMARK, getTagSet("friends")),
            new Task(new Description("Bernice Yu"), new Status(), new DateTime(), new ModuleCode("berniceyu@example.com"),
                EMPTY_REMARK, getTagSet("colleagues", "friends")),
            new Task(new Description("Charlotte Oliveiro"), new Status(), new DateTime(), new ModuleCode("charlotte@example.com"),
                EMPTY_REMARK, getTagSet("neighbours")),
            new Task(new Description("David Li"), new Status(), new DateTime(), new ModuleCode("lidavid@example.com"),
                EMPTY_REMARK, getTagSet("family")),
            new Task(new Description("Irfan Ibrahim"), new Status(), new DateTime(), new ModuleCode("irfan@example.com"),
                EMPTY_REMARK, getTagSet("classmates")),
            new Task(new Description("Roy Balakrishnan"), new Status(), new DateTime(), new ModuleCode("royb@example.com"),
                EMPTY_REMARK, getTagSet("colleagues"))
        };
    }

    public static ReadOnlyJelphaBot getSampleJelphaBot() {
        JelphaBot sampleAb = new JelphaBot();
        for (Task sampleTask : getSamplePersons()) {
            sampleAb.addPerson(sampleTask);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
