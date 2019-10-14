package seedu.address.storage.catalog;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.LoanRecords;
import seedu.address.model.ReadOnlyLoanRecords;
import seedu.address.model.book.Author;
import seedu.address.model.book.Book;
import seedu.address.model.book.SerialNumber;
import seedu.address.model.book.Title;
import seedu.address.model.genre.Genre;
import seedu.address.model.loan.Loan;
import seedu.address.model.loan.LoanId;

/**
 * Jackson-friendly version of {@link Book}.
 */
public class JsonAdaptedBook {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Book's %s field is missing!";

    private final String title;
    private final String serialNumber;
    private final String author;
    private final String loan;
    private final List<JsonAdaptedTag> genres = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedBook} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedBook(@JsonProperty("title") String title,
                           @JsonProperty("serialNumber") String serialNumber,
                           @JsonProperty("author") String author,
                           @JsonProperty("loan") String loan,
                           @JsonProperty("genres") List<JsonAdaptedTag> genres) {
        this.title = title;
        this.serialNumber = serialNumber;
        this.author = author;
        if (genres != null) {
            this.genres.addAll(genres);
        }
        this.loan = loan;
    }

    /**
     * Converts a given {@code Book} into this class for Jackson use.
     */
    public JsonAdaptedBook(Book source) {
        title = source.getTitle().value;
        serialNumber = source.getSerialNumber().value;
        author = source.getAuthor().value;

        Optional<Loan> optionalLoan = source.getLoan();
        if (optionalLoan.isPresent()) {
            loan = optionalLoan.get().getLoanId().toString();
        } else {
            loan = null;
        }

        genres.addAll(source.getGenres().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     * Uses an empty LoanRecords.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType() throws IllegalValueException {
        return toModelType(new LoanRecords());
    }

    /**
     * Converts this Jackson-friendly adapted book object into the model's {@code Book} object.
     * Loan objects of the Book is taken from the initialLoanRecords.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted book.
     */
    public Book toModelType(ReadOnlyLoanRecords initialLoanRecords) throws IllegalValueException {
        final List<Genre> personGenres = new ArrayList<>();
        for (JsonAdaptedTag tag : genres) {
            personGenres.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (serialNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    SerialNumber.class.getSimpleName()));
        }
        if (!SerialNumber.isValidSerialNumber(serialNumber)) {
            throw new IllegalValueException(SerialNumber.MESSAGE_CONSTRAINTS);
        }
        final SerialNumber modelSerialNumber = new SerialNumber(serialNumber);

        if (author == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName()));
        }
        final Author modelAuthor = new Author(author);

        final Loan modelLoan;
        if (loan == null) {
            modelLoan = null;
        } else if (!LoanId.isValidLoanId(loan)) {
            throw new IllegalValueException(LoanId.MESSAGE_CONSTRAINTS);
        } else {
            modelLoan = initialLoanRecords.getLoansMap().get(new LoanId(loan));
        }

        final Set<Genre> modelGenres = new HashSet<>(personGenres);
        return new Book(modelTitle, modelSerialNumber, modelAuthor, modelLoan, modelGenres);
    }

}
