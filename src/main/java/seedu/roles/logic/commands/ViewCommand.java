package seedu.roles.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.roles.commons.core.Messages;
import seedu.roles.commons.core.index.Index;
import seedu.roles.logic.commands.exceptions.CommandException;
import seedu.roles.model.Model;
import seedu.roles.model.job.Role;


/**
 * Edits the details of an existing role in the company book.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": View more details of the role "
            + "by the index number used in the displayed role list. \n \n"
            + "Parameters: INDEX (must be a positive integer) \n \n"
            + "Example: " + COMMAND_WORD + " 1 ";
    private final Index index;

    /**
     * @param index              of the role in the filtered role list to edit
     */
    public ViewCommand(Index index) {
        requireNonNull(index);

        this.index = index;
    }

    @Override
    public CommandResult<Role> execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Role> lastShownList = model.getFilteredRoleList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Role roleToView = lastShownList.get(index.getZeroBased());

        return new CommandResult<>(roleToView);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof ViewCommand
                && index.equals(((ViewCommand) other).index));
    }
}