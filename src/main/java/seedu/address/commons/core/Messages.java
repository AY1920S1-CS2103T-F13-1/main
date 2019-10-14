package seedu.address.commons.core;

/**
 * Container for user visible messages.
 */
public class Messages {

    //Command messages
    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";

    //Book messages
    public static final String MESSAGE_DUPLICATE_BOOK = "Serial number provided is already in use!";
    public static final String MESSAGE_INVALID_BOOK_DISPLAYED_INDEX = "The book index provided is invalid";
    public static final String MESSAGE_BOOKS_LISTED_OVERVIEW = "%1$d books listed!";
    public static final String MESSAGE_NO_SUCH_BOOK = "No such book!";

    //Serial Number messages
    public static final String MESSAGE_INVALID_SERIAL_NUMBER = "Invalid Serial Number! \n%1$s";

    //Borrower messages
    public static final String MESSAGE_DUPLICATE_BORROWER = "Phone/Email is already in used! ";
    public static final String MESSAGE_NO_SUCH_BORROWER_ID = "No such borrower ID!";
    public static final String MESSAGE_NOT_IN_SERVE_MODE = "Not in Serve mode! Enter Serve mode to use this command!";

    //Loan messages
    public static final String MESSAGE_BOOK_ON_LOAN = "%1$s is already on loan!";

}
