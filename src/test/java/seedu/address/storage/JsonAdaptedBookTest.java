package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedBook.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK_1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.book.Author;
import seedu.address.model.book.Title;
import seedu.address.model.book.SerialNumber;

public class JsonAdaptedBookTest {
    private static final String INVALID_TITLE = "R@chel";
    private static final String INVALID_SERIAL_NUMBER = "+651234a";
    private static final String INVALID_AUTHOR = "example.com*";
    private static final String INVALID_GENRE = "#friend";

    private static final String VALID_TITLE = BOOK_1.getTitle().toString();
    private static final String VALID_SERIAL_NUMBER = BOOK_1.getSerialNumber().toString();
    private static final String VALID_AUTHOR = BOOK_1.getAuthor().toString();
    private static final List<JsonAdaptedTag> VALID_GENRES = BOOK_1.getGenres().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validBookDetails_returnsBook() throws Exception {
        JsonAdaptedBook book = new JsonAdaptedBook(BOOK_1);
        assertEquals(BOOK_1, book.toModelType());
    }

    @Test
    public void toModelType_invalidTitle_throwsIllegalValueException() {
        JsonAdaptedBook book =
                new JsonAdaptedBook(INVALID_TITLE, VALID_SERIAL_NUMBER, VALID_AUTHOR, VALID_GENRES);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_nullTitle_throwsIllegalValueException() {
        JsonAdaptedBook book = new JsonAdaptedBook(null, VALID_SERIAL_NUMBER, VALID_AUTHOR,
                VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, book::toModelType);
    }

    @Test
    public void toModelType_invalidSerialNumber_throwsIllegalValueException() {
        JsonAdaptedBook person =
                new JsonAdaptedBook(VALID_TITLE, INVALID_SERIAL_NUMBER, VALID_AUTHOR, VALID_GENRES);
        String expectedMessage = SerialNumber.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullSerialNumber_throwsIllegalValueException() {
        JsonAdaptedBook person = new JsonAdaptedBook(VALID_TITLE, null, VALID_AUTHOR,
                VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, SerialNumber.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAuthor_throwsIllegalValueException() {
        JsonAdaptedBook person = new JsonAdaptedBook(VALID_TITLE, VALID_SERIAL_NUMBER, null,
                VALID_GENRES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Author.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidGenres_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_GENRES);
        invalidTags.add(new JsonAdaptedTag(INVALID_GENRE));
        JsonAdaptedBook person =
                new JsonAdaptedBook(VALID_TITLE, VALID_SERIAL_NUMBER, VALID_AUTHOR, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
