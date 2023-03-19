package seedu.address.logic.commands;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.job.Order;
import static java.util.Objects.requireNonNull;

/**
 * Sort a role based on the salary in asc or desc order.
 */
public class SalaryCommand extends Command {

    public static final String COMMAND_WORD = "salary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the salary in asc/desc order.\n"
            + "Parameters: ORDER (must be either asc OR desc)\n"
            + "Example: " + COMMAND_WORD + " desc";
    private Order order;

    public static final String MESSAGE_SUCCESS = "Salaries sorted in %1$s";

    public SalaryCommand(Order order) {
        this.order = order;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.displaySortedRoleList(this.order);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.order.toString()));
    }
}
