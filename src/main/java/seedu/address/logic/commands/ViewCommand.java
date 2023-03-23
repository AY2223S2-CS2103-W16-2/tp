package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Role;


/**
 * Edits the details of an existing role in the company book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View more details of the role "
            + "by the index number used in the displayed role list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_ROLE_SUCCESS = "Displayed details for: %1$s";
    private final Index index;

    /**
     * @param index              of the role in the filtered role list to edit
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Role> lastShownList = model.getFilteredRoleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Role roleToView = lastShownList.get(index.getZeroBased());

        return new CommandResult(String.format(MESSAGE_VIEW_ROLE_SUCCESS, roleToView));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewCommand
                && index.equals(((ViewCommand) other).index));
    }
}
