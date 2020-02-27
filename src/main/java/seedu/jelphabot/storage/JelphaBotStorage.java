package seedu.jelphabot.storage;

import seedu.jelphabot.commons.exceptions.DataConversionException;
import seedu.jelphabot.model.ReadOnlyJelphaBot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

/**
 * Represents a storage for {@link seedu.jelphabot.model.JelphaBot}.
 */
public interface JelphaBotStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getJelphaBotFilePath();

    /**
     * Returns JelphaBot data as a {@link ReadOnlyJelphaBot}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyJelphaBot> readJelphaBot() throws DataConversionException, IOException;

    /**
     * @see #getJelphaBotFilePath()
     */
    Optional<ReadOnlyJelphaBot> readJelphaBot(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyJelphaBot} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveJelphaBot(ReadOnlyJelphaBot addressBook) throws IOException;

    /**
     * @see #saveJelphaBot(ReadOnlyJelphaBot)
     */
    void saveJelphaBot(ReadOnlyJelphaBot addressBook, Path filePath) throws IOException;

}
