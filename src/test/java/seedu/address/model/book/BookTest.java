package seedu.address.model.book;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AUTHOR_BOOK_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENRE_ACTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BOOK_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SERIAL_NUMBER_BOOK_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_BOOK_2;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalBooks.BOOK_1;
import static seedu.address.testutil.TypicalBooks.BOOK_2;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.BookBuilder;

public class BookTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Book book = new BookBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> book.getGenres().remove(0));
    }

    @Test
    public void toString_haveTitleDisplayed() {
        Book book = new BookBuilder(BOOK_1).build();
        boolean containsTag = book.toString().contains(book.getTitle().toString());
        assertTrue(containsTag);
    }

    @Test
    public void toString_haveAuthorDisplayed() {
        Book book = new BookBuilder(BOOK_1).build();
        boolean containsTag = book.toString().contains(book.getAuthor().toString());
        assertTrue(containsTag);
    }

    @Test
    public void isSameBook() {
        // will return true if book is same, regardless of serial number
        // same object -> returns true
        assertTrue(BOOK_1.isSameBook(BOOK_1));

        // null -> returns false
        assertFalse(BOOK_1.isSameBook(null));

        // different serial number and author -> returns false
        Book editedBook1 = new BookBuilder(BOOK_1).withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2)
                .withAuthor(VALID_AUTHOR_BOOK_2).build();
        assertFalse(BOOK_1.isSameBook(editedBook1));

        // different title -> returns false
        editedBook1 = new BookBuilder(BOOK_1).withTitle(VALID_TITLE_BOOK_2)
                .withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertFalse(BOOK_1.isSameBook(editedBook1));

        // same attributes, different serial number -> returns true
        editedBook1 = new BookBuilder(BOOK_1).withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertTrue(BOOK_1.isSameBook(editedBook1));

        // same title, different serial number, different genre -> isSameBook returns false
        editedBook1 = new BookBuilder(BOOK_1).withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2)
                .withGenres(VALID_GENRE_ACTION).build();
        assertFalse(BOOK_1.isSameBook(editedBook1));

        // same attributes, different genres -> returns false
        editedBook1 = new BookBuilder(BOOK_1).withGenres(VALID_GENRE_ACTION).build();
        assertFalse(BOOK_1.equals(editedBook1));
    }

    @Test
    public void equals() {
        // compares all attributes
        // same values -> returns true
        Book aliceCopy = new BookBuilder(BOOK_1).build();
        assertTrue(BOOK_1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(BOOK_1.equals(BOOK_1));

        // null -> returns false
        assertFalse(BOOK_1.equals(null));

        // different type -> returns false
        assertFalse(BOOK_1.equals(5));

        // different person -> returns false
        assertFalse(BOOK_1.equals(BOOK_2));

        // different name and serial number -> returns false
        Book editedA = new BookBuilder(BOOK_1).withTitle(VALID_TITLE_BOOK_2)
                .withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertFalse(BOOK_1.equals(editedA));

        // different serial number -> returns false
        editedA = new BookBuilder(BOOK_1).withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertFalse(BOOK_1.equals(editedA));

        // different author -> returns false
        editedA = new BookBuilder(BOOK_1).withAuthor(VALID_AUTHOR_BOOK_2)
                .withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertFalse(BOOK_1.equals(editedA));

        // different genres -> returns false
        editedA = new BookBuilder(BOOK_1).withGenres(VALID_GENRE_ACTION)
                .withSerialNumber(VALID_SERIAL_NUMBER_BOOK_2).build();
        assertFalse(BOOK_1.equals(editedA));
    }

    @Test
    public void hashCode_sameBookSameHashCode_assertTrue() {
        Book book1 = new BookBuilder(BOOK_1).build();
        Book book2 = new BookBuilder(BOOK_1).build();
        assertEquals(book1.hashCode(), book2.hashCode());
    }

    @Test
    public void getSerialNumber_sameBook_assertTrue() {
        Book book1 = new BookBuilder(BOOK_1).build();
        assertTrue(book1.getSerialNumber().equals(new SerialNumber(VALID_SERIAL_NUMBER_BOOK_1)));
    }

    @Test
    public void toString_book() {
        Book book1 = new BookBuilder(BOOK_1).build();
        StringBuilder genres = new StringBuilder();
        book1.getGenres().forEach(genre -> genres.append(genre + " "));
        String stringRep = "["
                + book1.getSerialNumber().toString()
                + "] \""
                + book1.getTitle().toString()
                + "\" by "
                + book1.getAuthor().toString();

        assertTrue(book1.toString().equals(stringRep));
    }

}
