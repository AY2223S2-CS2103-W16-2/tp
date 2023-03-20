package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.CompanyCommand;
import seedu.address.logic.commands.exceptions.exceptions.ParseException;
import seedu.address.model.job.CompanyContainsKeywordsPredicate;


import java.util.Arrays;

/**
 * Parses input arguments and creates a new SalaryCommand object
 */
public class CompanyCommandParser implements Parser<CompanyCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the Deadlineommand
     * and returns a DeadlineCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public CompanyCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, CompanyCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new CompanyCommand(new CompanyContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}