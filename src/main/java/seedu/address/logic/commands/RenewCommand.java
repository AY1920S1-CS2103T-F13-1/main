package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_BOOK_CANNOT_BE_RENEWED_ANYMORE;
import static seedu.address.commons.core.Messages.MESSAGE_BOOK_IS_OVERDUE;
import static seedu.address.commons.core.Messages.MESSAGE_BOOK_NOT_ON_LOAN;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_BOOK_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_IN_SERVE_MODE;
import static seedu.address.commons.core.Messages.MESSAGE_NOT_LOANED_BY_BORROWER;
import static seedu.address.commons.core.UserSettings.DEFAULT_RENEW_COUNT;

import java.time.LocalDate;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.DateUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.book.Book;
import seedu.address.model.borrower.Borrower;
import seedu.address.model.loan.Loan;

/**
 * Renews a Book with the given Index.
 */
public class RenewCommand extends Command {
    public static final String COMMAND_WORD = "renew";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Renews a book borrowed by a borrower.\n"
            + "Command can only be used in Serve mode.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_SUCCESS = "Book: %1$s\nrenewed by\nBorrower: %2$s\nDue date: %3$s";

    private final Index index;

    /**
     * Creates an RenewCommand to renew the currently served Borrower's {@code Book}.
     *
     * @param index Index of book to be renewed.
     */
    public RenewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    /**
     * Executes the RenewCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return Feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!model.isServeMode()) {
            throw new CommandException(MESSAGE_NOT_IN_SERVE_MODE);
        }

        List<Book> lastShownList = model.getFilteredBookList();
        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_BOOK_DISPLAYED_INDEX);
        }

        Book bookToBeRenewed = lastShownList.get(index.getZeroBased()); // TODO change to second list index
        if (!bookToBeRenewed.isCurrentlyLoanedOut()) {
            throw new CommandException(String.format(MESSAGE_BOOK_NOT_ON_LOAN, bookToBeRenewed));
        }

        Loan loanToBeRenewed = bookToBeRenewed.getLoan().get();
        Borrower servingBorrower = model.getServingBorrower();
        if (!servingBorrower.hasCurrentLoan(loanToBeRenewed)) {
            throw new CommandException(String.format(MESSAGE_NOT_LOANED_BY_BORROWER, servingBorrower, bookToBeRenewed));
        }

        if (loanToBeRenewed.getRenewCount() > DEFAULT_RENEW_COUNT) { // TODO take from userSettings in model instead
            throw new CommandException(String.format(MESSAGE_BOOK_CANNOT_BE_RENEWED_ANYMORE, bookToBeRenewed));
        }

        if (DateUtil.isDateBeforeToday(loanToBeRenewed.getDueDate())) {
            throw new CommandException(String.format(MESSAGE_BOOK_IS_OVERDUE, bookToBeRenewed));
        }

        LocalDate extendedDueDate = DateUtil.extendDate(loanToBeRenewed.getDueDate(),
                model.getUserSettings().getRenewPeriod());
        Loan renewedLoan = new Loan(loanToBeRenewed.getLoanId(), loanToBeRenewed.getBookSerialNumber(),
                loanToBeRenewed.getBorrowerId(), loanToBeRenewed.getStartDate(), extendedDueDate,
                loanToBeRenewed.getReturnDate(),loanToBeRenewed.getRenewCount() + 1,
                loanToBeRenewed.getRemainingFineAmount(), loanToBeRenewed.getPaidFineAmount());

        Book renewedBook = new Book(bookToBeRenewed.getTitle(), bookToBeRenewed.getSerialNumber(),
                bookToBeRenewed.getAuthor(), renewedLoan, bookToBeRenewed.getGenres());

        // update Book in model to have Loan due date extended
        model.setBook(bookToBeRenewed, renewedBook);

        // update Loan in Borrower's currentLoanList
        model.servingBorrowerRenewLoan(loanToBeRenewed, renewedLoan);

        // update Loan in LoanRecords with extended due date
        model.updateLoan(loanToBeRenewed, renewedLoan);

        return new CommandResult(String.format(MESSAGE_SUCCESS, renewedBook, servingBorrower, extendedDueDate));
    }
}
