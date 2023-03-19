package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.commons.core.Messages.MESSAGE_DEADLINE_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalRoles.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Order;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class DeadlineCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {

        Order order1 = new Order("asc");
        Order order2 = new Order("desc");

        DeadlineCommand deadlineFirstCommand = new DeadlineCommand(order1);
        DeadlineCommand deadlineSecondCommand = new DeadlineCommand(order2);

        // different types -> returns false
        assertFalse(deadlineFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deadlineFirstCommand.equals(null));

        // different sorted list -> returns false
        assertFalse(deadlineFirstCommand.equals(deadlineSecondCommand));
    }

    @Test
    public void execute_asecOrder() {
        String expectedMessage = String.format(MESSAGE_DEADLINE_COMMAND_FORMAT, "asc");
        DeadlineCommand command = new DeadlineCommand(new Order("asc"));
        expectedModel.displaySortedDeadlineList(new Order("asc"));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }
}
