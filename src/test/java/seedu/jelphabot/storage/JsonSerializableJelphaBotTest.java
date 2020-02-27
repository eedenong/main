package seedu.jelphabot.storage;

import org.junit.jupiter.api.Test;
import seedu.jelphabot.commons.exceptions.IllegalValueException;
import seedu.jelphabot.commons.util.JsonUtil;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.testutil.TypicalPersons;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.jelphabot.testutil.Assert.assertThrows;

public class JsonSerializableJelphaBotTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableJelphaBotTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsJelphaBot.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonJelphaBot.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonJelphaBot.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableJelphaBot dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableJelphaBot.class).get();
        JelphaBot addressBookFromFile = dataFromFile.toModelType();
        JelphaBot typicalPersonsJelphaBot = TypicalPersons.getTypicalJelphaBot();
        assertEquals(addressBookFromFile, typicalPersonsJelphaBot);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableJelphaBot dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableJelphaBot.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableJelphaBot dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableJelphaBot.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableJelphaBot.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
