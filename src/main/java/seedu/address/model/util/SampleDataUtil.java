package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

<<<<<<< HEAD
import seedu.address.model.Catalog;
import seedu.address.model.ReadOnlyCatalog;
import seedu.address.model.SerialNumberGenerator;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Title;
import seedu.address.model.genre.Genre;
import seedu.address.model.AddressBook;
=======
>>>>>>> 76edc3518025011d8a382c0c40e511828d7408d5
import seedu.address.model.BorrowerRecords;
import seedu.address.model.Catalog;
import seedu.address.model.LoanRecords;
import seedu.address.model.ReadOnlyBorrowerRecords;
import seedu.address.model.ReadOnlyCatalog;
import seedu.address.model.ReadOnlyLoanRecords;
import seedu.address.model.SerialNumberGenerator;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.Title;
import seedu.address.model.genre.Genre;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Book[] getSampleBooks() {
        SerialNumberGenerator.setCatalog(new Catalog());
        return new Book[] {
            new Book(new Title("Harry Botter"), SerialNumberGenerator.generateSerialNumber(),
                    new Author("J K Rowling"), getTagSet("Fiction")),
            new Book(new Title("Legend of the Condor Heroes"), SerialNumberGenerator.generateSerialNumber(),
                    new Author("Jin Yong"), getTagSet("Fiction", "History")),
            new Book(new Title("Animal the Farm"), SerialNumberGenerator.generateSerialNumber(),
                    new Author("George Orwell"), getTagSet("Fiction")),
            new Book(new Title("Harry Botter and the Full Blood Prince"), SerialNumberGenerator.generateSerialNumber(),
                    new Author("J K Rowling"), getTagSet("Fiction", "Action")),
        };
    }

    public static ReadOnlyCatalog getSampleCatalog() {
        Catalog sampleAb = new Catalog();
        for (Book sampleBook : getSampleBooks()) {
            sampleAb.addBook(sampleBook);
        }
        return sampleAb;
    }

    /**
     * Returns a genre set containing the list of strings given.
     */
    public static Set<Genre> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Genre::new)
                .collect(Collectors.toSet());
    }

    public static ReadOnlyLoanRecords getSampleLoanRecords() {
        LoanRecords loanRecords = new LoanRecords();
        return loanRecords;
    }

    public static ReadOnlyBorrowerRecords getSampleBorrowerRecords() {
        BorrowerRecords borrowerRecords = new BorrowerRecords();
        return borrowerRecords;
    }
}
