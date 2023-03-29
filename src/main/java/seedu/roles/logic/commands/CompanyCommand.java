package seedu.roles.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.roles.commons.core.Messages;
import seedu.roles.model.Model;
import seedu.roles.model.job.CompanyContainsKeywordsPredicate;

/**
 * Finds and lists all Companies in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class CompanyCommand extends Command {

    public static final String COMMAND_WORD = "company";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all companies whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n \n"
            + "Example: " + COMMAND_WORD + " Google";

    private final CompanyContainsKeywordsPredicate predicate;
    public CompanyCommand(CompanyContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult<String> execute(Model model) {
        requireNonNull(model);
        model.updateFilteredRoleList(predicate);
        return new CommandResult<>(
                String.format(Messages.MESSAGE_ROLES_LISTED_OVERVIEW, model.getFilteredRoleList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CompanyCommand // instanceof handles nulls
                && predicate.equals(((CompanyCommand) other).predicate)); // state check
    }
}