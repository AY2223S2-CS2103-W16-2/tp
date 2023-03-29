package seedu.roles.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.roles.testutil.Assert.assertThrows;
import static seedu.roles.testutil.TypicalRoles.ALICE;
import static seedu.roles.testutil.TypicalRoles.HOON;
import static seedu.roles.testutil.TypicalRoles.IDA;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.roles.commons.exceptions.DataConversionException;
import seedu.roles.model.ReadOnlyRoleBook;
import seedu.roles.model.RoleBook;

public class JsonRoleBookStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonRoleBookStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readRoleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readRoleBook(null));
    }

    private java.util.Optional<ReadOnlyRoleBook> readRoleBook(String filePath) throws Exception {
        return new JsonRoleBookStorage(Paths.get(filePath)).readRoleBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readRoleBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readRoleBook("notJsonFormatRoleBook.json"));
    }

    @Test
    public void readRoleBook_invalidRoleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoleBook("invalidRoleBook.json"));
    }

    @Test
    public void readRoleBook_invalidAndValidRoleBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readRoleBook("invalidAndValidRoleBook.json"));
    }

    @Test
    public void readAndsaveRoleBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempRoleBook.json");
        RoleBook original = getTypicalRoleBook();
        JsonRoleBookStorage JsonRoleBookStorage = new JsonRoleBookStorage(filePath);

        // Save in new file and read back
        JsonRoleBookStorage.saveRoleBook(original, filePath);
        ReadOnlyRoleBook readBack = JsonRoleBookStorage.readRoleBook(filePath).get();
        assertEquals(original, new RoleBook(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addRole(HOON);
        original.removeRole(ALICE);
        JsonRoleBookStorage.saveRoleBook(original, filePath);
        readBack = JsonRoleBookStorage.readRoleBook(filePath).get();
        assertEquals(original, new RoleBook(readBack));

        // Save and read without specifying file path
        original.addRole(IDA);
        JsonRoleBookStorage.saveRoleBook(original); // file path not specified
        readBack = JsonRoleBookStorage.readRoleBook().get(); // file path not specified
        assertEquals(original, new RoleBook(readBack));

    }

    @Test
    public void saveRoleBook_nullRoleBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoleBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveRoleBook(ReadOnlyRoleBook addressBook, String filePath) {
        try {
            new JsonRoleBookStorage(Paths.get(filePath))
                    .saveRoleBook(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveRoleBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveRoleBook(new RoleBook(), null));
    }
}