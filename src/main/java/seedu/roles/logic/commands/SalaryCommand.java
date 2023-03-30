package seedu.roles.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.roles.logic.commands.exceptions.CommandException;
import seedu.roles.logic.parser.OrderParser;
import seedu.roles.model.Model;


/**
 * Sort a role based on the salary in asc/desc order.
 */
public class SalaryCommand extends Command {

    public static final String COMMAND_WORD = "salary";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the salary in asc/desc order.\n \n"
            + "Parameters: ORDER (must be either asc OR desc)\n \n"
            + "Example: " + COMMAND_WORD + " desc";
    public static final String MESSAGE_SUCCESS = "Salaries sorted in %1$s";
    private OrderParser orderParser;

    public SalaryCommand(OrderParser orderParser) {
        this.orderParser = orderParser;
    }

    @Override
    public CommandResult<String> execute(Model model) throws CommandException {
        requireNonNull(model);
        model.displaySortedSalaryList(this.orderParser);
        return new CommandResult<>(String.format(MESSAGE_SUCCESS, this.orderParser.toString()));
    }
}
