package seedu.jelphabot.storage;

import org.junit.jupiter.api.Test;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.model.task.ModuleCode;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jelphabot.storage.JsonAdaptedTask.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalPersons.BENSON;

// TODO rewrite entire test class to replace missing fields
public class JsonAdaptedTaskTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_MODULE_CODE = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getDescription().toString();
//    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_MODULE_CODE = BENSON.getModuleCode().toString();
//    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_REMARK = BENSON.getRemark().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedTask person = new JsonAdaptedTask(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, INVALID_MODULE_CODE, VALID_REMARK, VALID_TAGS);
        String expectedMessage = ModuleCode.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullModuleCode_throwsIllegalValueException() {
        JsonAdaptedTask person = new JsonAdaptedTask(VALID_NAME, null, VALID_REMARK, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModuleCode.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedTask person =
                new JsonAdaptedTask(VALID_NAME, VALID_MODULE_CODE, VALID_REMARK, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
