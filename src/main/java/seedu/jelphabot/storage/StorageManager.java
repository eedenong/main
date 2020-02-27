package seedu.jelphabot.storage;

import seedu.jelphabot.commons.core.LogsCenter;
import seedu.jelphabot.commons.exceptions.DataConversionException;
import seedu.jelphabot.model.ReadOnlyJelphaBot;
import seedu.jelphabot.model.ReadOnlyUserPrefs;
import seedu.jelphabot.model.UserPrefs;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Manages storage of JelphaBot data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private JelphaBotStorage addressBookStorage;
    private UserPrefsStorage userPrefsStorage;


    public StorageManager(JelphaBotStorage addressBookStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.addressBookStorage = addressBookStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ JelphaBot methods ==============================

    @Override
    public Path getJelphaBotFilePath() {
        return addressBookStorage.getJelphaBotFilePath();
    }

    @Override
    public Optional<ReadOnlyJelphaBot> readJelphaBot() throws DataConversionException, IOException {
        return readJelphaBot(addressBookStorage.getJelphaBotFilePath());
    }

    @Override
    public Optional<ReadOnlyJelphaBot> readJelphaBot(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return addressBookStorage.readJelphaBot(filePath);
    }

    @Override
    public void saveJelphaBot(ReadOnlyJelphaBot addressBook) throws IOException {
        saveJelphaBot(addressBook, addressBookStorage.getJelphaBotFilePath());
    }

    @Override
    public void saveJelphaBot(ReadOnlyJelphaBot addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        addressBookStorage.saveJelphaBot(addressBook, filePath);
    }

}
