package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.book.Author;
import seedu.address.model.book.SerialNumber;
import seedu.address.model.book.Title;
import seedu.address.model.borrower.BorrowerId;
import seedu.address.model.borrower.Email;
import seedu.address.model.borrower.Name;
import seedu.address.model.borrower.Phone;
import seedu.address.model.genre.Genre;
import seedu.address.model.usersettings.FineIncrement;
import seedu.address.model.usersettings.LoanPeriod;
import seedu.address.model.usersettings.MaxRenews;
import seedu.address.model.usersettings.RenewPeriod;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_DOLLAR_AMOUNT =
            "Dollar amount should be a non-zero number with at most 2 decimal places.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code dollarAmount} into a {@code double} representing the amount in dollars and returns it.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the specified index is invalid (not a non-zero number with at most 2 decimal places).
     */
    public static double parseDollar(String dollarAmount) throws ParseException {
        String trimmedAmount = dollarAmount.trim();
        if (!StringUtil.isValidDollarAmount(trimmedAmount)) {
            throw new ParseException(MESSAGE_INVALID_DOLLAR_AMOUNT);
        }
        return Double.parseDouble(trimmedAmount);
    }

    /**
     * Parses a {@code String title} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code title} is invalid.
     */
    public static Title parseTitle(String title) throws ParseException {
        requireNonNull(title);
        String trimmedName = title.trim();
        if (!Title.isValidTitle(trimmedName)) {
            throw new ParseException(Title.MESSAGE_CONSTRAINTS);
        }
        return new Title(trimmedName);
    }

    /**
     * Parses a {@code String serialNumber} into a {@code SerialNumber}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code serialNumber} is invalid.
     */
    public static SerialNumber parseSerialNumber(String serialNumber) throws ParseException {
        requireNonNull(serialNumber);
        String trimmedSerialNumber = serialNumber.trim();
        if (!SerialNumber.isValidSerialNumber(trimmedSerialNumber)) {
            throw new ParseException(SerialNumber.MESSAGE_CONSTRAINTS);
        }
        return new SerialNumber(trimmedSerialNumber);
    }

    /**
     * Parses a {@code String author} into an {@code Author}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @return Author object from remark string
     */
    public static Author parseAuthor(String author) throws ParseException {
        requireNonNull(author);
        String trimmedAuthor = author.trim();
        if (!Author.isValidAuthor(trimmedAuthor)) {
            throw new ParseException(Author.MESSAGE_CONSTRAINTS);
        }
        return new Author(trimmedAuthor);
    }

    /**
     * Parses a {@code String genre} into a {@code Genre}.
     * Leading and trailing whitespaces will be trimmed, and genre will be converted to UPPERCASE
     *
     * @throws ParseException if the given {@code genre} is invalid.
     */
    public static Genre parseGenre(String genre) throws ParseException {
        requireNonNull(genre);
        String trimmedGenre = genre.trim();
        String uppercaseGenre = trimmedGenre.toUpperCase();
        if (!Genre.isValidGenreName(uppercaseGenre)) {
            throw new ParseException(Genre.MESSAGE_CONSTRAINTS);
        }
        return new Genre(uppercaseGenre);
    }

    /**
     * Parses {@code Collection<String> genres} into a {@code Set<Genre>}.
     */
    public static Set<Genre> parseGenres(Collection<String> genres) throws ParseException {
        requireNonNull(genres);
        final Set<Genre> genreSet = new HashSet<>();
        for (String genreName : genres) {
            genreSet.add(parseGenre(genreName));
        }
        return genreSet;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String Borrower ID} into an {@code Borrower ID}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code borrower ID} is invalid.
     */
    public static BorrowerId parseBorrowerId(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!BorrowerId.isValidBorrowerId(trimmedId)) {
            throw new ParseException(BorrowerId.MESSAGE_CONSTRAINTS);
        }
        return new BorrowerId(trimmedId);
    }

    /**
     * Parses {@code loanPeriod} into an {@code LoanPeriod} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified loanPeriod is invalid (not non-zero unsigned integer).
     */
    public static LoanPeriod parseLoanPeriod(String loanPeriod) throws ParseException {
        requireNonNull(loanPeriod);
        String trimmedLoanPeriod = loanPeriod.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedLoanPeriod)) {
            throw new ParseException(LoanPeriod.MESSAGE_CONSTRAINTS);
        }
        return new LoanPeriod(trimmedLoanPeriod);
    }

    /**
     * Parses {@code renewPeriod} into an {@code renewPeriod} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified renewPeriod is invalid (not non-zero unsigned integer).
     */
    public static RenewPeriod parseRenewPeriod(String renewPeriod) throws ParseException {
        requireNonNull(renewPeriod);
        String trimmedRenewPeriod = renewPeriod.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedRenewPeriod)) {
            throw new ParseException(RenewPeriod.MESSAGE_CONSTRAINTS);
        }
        return new RenewPeriod(trimmedRenewPeriod);
    }

    /**
     * Parses {@code fineIncrement} into an {@code FineIncrement} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if the specified fineIncrement is invalid (not non-zero unsigned integer).
     */
    public static FineIncrement parseFineIncrement(String fineIncrement) throws ParseException {
        requireNonNull(fineIncrement);
        String trimmedFineIncrement = fineIncrement.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedFineIncrement)) {
            throw new ParseException(FineIncrement.MESSAGE_CONSTRAINTS);
        }
        return new FineIncrement(trimmedFineIncrement);
    }

    /**
     * Parses {@code maxRenews} into an {@code RenewCount} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     * @throws ParseException if the specified maxRenews is invalid (not non-zero unsigned integer).
     */
    public static MaxRenews parseMaxRenews(String maxRenews) throws ParseException {
        requireNonNull(maxRenews);
        String trimmedMaxRenews = maxRenews.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedMaxRenews)) {
            throw new ParseException(MaxRenews.MESSAGE_CONSTRAINTS);
        }
        return new MaxRenews(trimmedMaxRenews);
    }

    /**
     * Parses {@code flag} into {@code Flag}.
     * Leading and trailing whitespaces will be trimmed, and flag will be converted to UPPERCASE
     *
     * @throws ParseException if the flag is invalid (case-insensitive)
     */
    public static Flag parseFlag(String flag) throws ParseException {
        requireNonNull(flag);
        String trimmedFlag = flag.trim();
        String uppercaseFlag = trimmedFlag.toUpperCase();
        try {
            return Flag.valueOf(uppercaseFlag);
        } catch (IllegalArgumentException e) {
            throw new ParseException(Flag.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses {@code Collection<String> flags} into a {@code Set<Flag>}.
     */
    public static Set<Flag> parseFlags(Collection<String> flags) throws ParseException {
        requireNonNull(flags);
        final Set<Flag> flagSet = new HashSet<>();
        for (String flagName : flags) {
            flagSet.add(parseFlag(flagName));
        }
        return flagSet;
    }
}
