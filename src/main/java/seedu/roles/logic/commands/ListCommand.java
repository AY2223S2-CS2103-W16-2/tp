package seedu.roles.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.roles.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.roles.model.Model;

/**
 * Lists all roles in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all roles";


    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoleList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult<>(MESSAGE_SUCCESS);
    }
}