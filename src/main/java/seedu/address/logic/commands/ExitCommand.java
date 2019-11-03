package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Terminates the program.
 */
public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the application. \n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Catalog as requested ...";

    @Override
    public CommandResult execute(Model model) {
        return CommandResult.commandResultExit(MESSAGE_EXIT_ACKNOWLEDGEMENT);
    }

}
