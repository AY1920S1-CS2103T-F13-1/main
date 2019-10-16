package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BORROWER_ID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BOOK_1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BORROWER_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_BOOK;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteByIndexCommand;
import seedu.address.logic.commands.DeleteBySerialNumberCommand;
import seedu.address.logic.commands.DoneCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.LoanCommand;
import seedu.address.logic.commands.RegisterCommand;
import seedu.address.logic.commands.ServeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.BorrowerRecords;
import seedu.address.model.Catalog;
import seedu.address.model.book.Book;
import seedu.address.model.book.BookPredicate;
import seedu.address.model.book.SerialNumber;
import seedu.address.model.book.SerialNumberGenerator;
import seedu.address.model.borrower.BorrowerIdGenerator;
import seedu.address.testutil.BookBuilder;
import seedu.address.testutil.BookUtil;
import seedu.address.testutil.EditBookDescriptorBuilder;

public class CatalogParserTest {

    private final CatalogParser parser = new CatalogParser();

    @Test
    public void parseCommand_add() throws Exception {
        SerialNumberGenerator.setCatalog(new Catalog());
        Book book = new BookBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(BookUtil.getAddCommand(book));
        assertEquals(new AddCommand(book), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteByIndex() throws Exception {
        DeleteByIndexCommand command = (DeleteByIndexCommand) parser.parseCommand(
                DeleteByIndexCommand.COMMAND_WORD + " " + INDEX_FIRST_BOOK.getOneBased());
        assertEquals(new DeleteByIndexCommand(INDEX_FIRST_BOOK), command);
    }

    @Test
    public void parseCommand_deleteBySerialNumber() throws Exception {
        DeleteBySerialNumberCommand command = (DeleteBySerialNumberCommand) parser.parseCommand(
                DeleteBySerialNumberCommand.COMMAND_WORD
                        + " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_BOOK_1);
        assertEquals(new DeleteBySerialNumberCommand(new SerialNumber(VALID_SERIAL_NUMBER_BOOK_1)), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Book book = new BookBuilder().build();
        EditCommand.EditBookDescriptor descriptor = new EditBookDescriptorBuilder(book).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_BOOK.getOneBased() + " " + BookUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_BOOK, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        SerialNumberGenerator.setCatalog(new Catalog());
        Book book = new BookBuilder().build();
        FindCommand command = (FindCommand) parser.parseCommand(BookUtil.getFindCommand(book));
        assertEquals(new FindCommand(new BookPredicate()
            .addTitle(book.getTitle().value)
            .addAuthor(book.getAuthor().value)
            .addSerialNumber(book.getSerialNumber().value)
            .addGenres(book.getGenres())), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_loan() throws Exception {
        assertTrue(parser.parseCommand(
                LoanCommand.COMMAND_WORD + " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_BOOK_1)
                instanceof LoanCommand);
    }

    @Test
    public void parseCommand_register() throws Exception {
        BorrowerIdGenerator.setBorrowers(new BorrowerRecords());
        assertTrue(parser.parseCommand(
                RegisterCommand.COMMAND_WORD + " " + PREFIX_NAME + VALID_NAME_AMY + " "
                        + PREFIX_PHONE + VALID_PHONE_AMY + " " + PREFIX_EMAIL + VALID_EMAIL_AMY)
                instanceof RegisterCommand);
    }

    @Test
    public void parseCommand_serve() throws Exception {
        assertTrue(parser.parseCommand(
                ServeCommand.COMMAND_WORD + " " + PREFIX_BORROWER_ID + VALID_BORROWER_ID)
                instanceof ServeCommand);
    }

    @Test
    public void parseCommand_done() throws Exception {
        assertTrue(parser.parseCommand(DoneCommand.COMMAND_WORD) instanceof DoneCommand);
        assertTrue(parser.parseCommand(DoneCommand.COMMAND_WORD + " 3") instanceof DoneCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
