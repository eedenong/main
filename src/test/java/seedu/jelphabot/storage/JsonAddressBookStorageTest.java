package seedu.jelphabot.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import seedu.jelphabot.commons.exceptions.DataConversionException;
import seedu.jelphabot.model.JelphaBot;
import seedu.jelphabot.model.ReadOnlyJelphaBot;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.jelphabot.testutil.Assert.assertThrows;
import static seedu.jelphabot.testutil.TypicalPersons.*;

public class JsonJelphaBotStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonJelphaBotStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readJelphaBot_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readJelphaBot(null));
    }

    private java.util.Optional<ReadOnlyJelphaBot> readJelphaBot(String filePath) throws Exception {
        return new JsonJelphaBotStorage(Paths.get(filePath)).readJelphaBot(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readJelphaBot("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readJelphaBot("notJsonFormatJelphaBot.json"));
    }

    @Test
    public void readJelphaBot_invalidPersonJelphaBot_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJelphaBot("invalidPersonJelphaBot.json"));
    }

    @Test
    public void readJelphaBot_invalidAndValidPersonJelphaBot_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readJelphaBot("invalidAndValidPersonJelphaBot.json"));
    }

    @Test
    public void readAndSaveJelphaBot_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempJelphaBot.json");
        JelphaBot original = getTypicalJelphaBot();
        JsonJelphaBotStorage jsonJelphaBotStorage = new JsonJelphaBotStorage(filePath);

        // Save in new file and read back
        jsonJelphaBotStorage.saveJelphaBot(original, filePath);
        ReadOnlyJelphaBot readBack = jsonJelphaBotStorage.readJelphaBot(filePath).get();
        assertEquals(original, new JelphaBot(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonJelphaBotStorage.saveJelphaBot(original, filePath);
        readBack = jsonJelphaBotStorage.readJelphaBot(filePath).get();
        assertEquals(original, new JelphaBot(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonJelphaBotStorage.saveJelphaBot(original); // file path not specified
        readBack = jsonJelphaBotStorage.readJelphaBot().get(); // file path not specified
        assertEquals(original, new JelphaBot(readBack));

    }

    @Test
    public void saveJelphaBot_nullJelphaBot_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJelphaBot(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveJelphaBot(ReadOnlyJelphaBot addressBook, String filePath) {
        try {
            new JsonJelphaBotStorage(Paths.get(filePath))
                    .saveJelphaBot(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveJelphaBot_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveJelphaBot(new JelphaBot(), null));
    }
}
