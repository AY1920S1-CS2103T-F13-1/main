package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_BORROWER_ID;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.borrower.Borrower;
import seedu.address.model.borrower.BorrowerId;

/**
 * Opens a serving session for a borrower and allows the borrower to start borrower book
 */
public class ServeCommand extends Command {
    public static final String MESSAGE_SUCCESS = "Currently serving borrower: %1$s";
    private BorrowerId borrowerId;

    public ServeCommand (BorrowerId borrowerId) {
        requireNonNull(borrowerId);
        this.borrowerId = borrowerId;
    }
    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasBorrowerId(borrowerId)) {
            throw new CommandException(MESSAGE_NO_SUCH_BORROWER_ID);
        }

        model.setServingBorrower(borrowerId);
        Borrower borrower = model.getServingBorrower();
        return new CommandResult(String.format(MESSAGE_SUCCESS, borrower));
    }
}