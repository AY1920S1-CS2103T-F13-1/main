package seedu.address.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.book.Book;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Book> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    // ================================================================ Catalog

    public Path getCatalogFilePath();

    /**
     * Returns the user prefs' catalog file path.
     */
    public void setCatalogFilePath(Path catalogFilePath);

    /** Returns the Catalog */
    ReadOnlyCatalog getCatalog();

    /**
     * Returns the user prefs' loan records file path.
     */
    void setCatalog(ReadOnlyCatalog addressBook);

    /**
     * Returns true if a book with the same identity as {@code book} exists in the catalog.
     */
    boolean hasBook(Book book);
    /**
     * Returns the user prefs' catalog file path.
     */
    void deleteBook(Book target);

    void addBook(Book book);

    void setBook(Book target, Book editedBook);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Book> getFilteredBookList();

    void updateFilteredBookList(Predicate<Book> predicate);

    // ================================================================ LoanRecords

    Path getLoanRecordsFilePath();

    void setLoanRecordsFilePath(Path loanRecordsFilePath);

    /** Returns the LoanRecords*/
    ReadOnlyLoanRecords getLoanRecords();

    // ================================================================ BorrowerRecords

    /**
     * Returns the user prefs' borrower records file path.
     */
    Path getBorrowerRecordsFilePath();

    /**
     * Sets the user prefs' address borrower records path.
     */
    void setBorrowerRecordsFilePath(Path borrowerRecordsFilePath);

    /** Returns the BorrowerRecords*/
    ReadOnlyBorrowerRecords getBorrowerRecords();
}
