package seedu.roles.ui.displays;

import org.junit.jupiter.api.Test;

import static seedu.roles.testutil.Assert.assertThrows;

public class StringDisplayTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> StringDisplay.of(null));
    }

}
