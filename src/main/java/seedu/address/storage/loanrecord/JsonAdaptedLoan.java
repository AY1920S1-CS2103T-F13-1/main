package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.loan.Loan;

/**
 * Jackson-friendly version of {@link Loan}.
 */
class JsonAdaptedLoan {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String userName;
    private final String serialNumber;
    private final String loanDateTime;
    private final String dueDateTime;
    private final String returnedDateTime;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedLoan(@JsonProperty("userName") String userName, @JsonProperty("serialNumber") String serialNumber,
                           @JsonProperty("loanDateTime") String loanDateTime, @JsonProperty("dueDateTime") String dueDateTime,
                           @JsonProperty("returnedDateTime") String returnedDateTime) {
        this.userName = userName;
        this.serialNumber = serialNumber;
        this.loanDateTime = loanDateTime;
        this.dueDateTime = dueDateTime;
        this.returnedDateTime = returnedDateTime;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedLoan(Loan source) {

        userName = source.getUserName().fullName;
        serialNumber = source.getSerialNumber().value;
        loanDateTime = source.getLoanDateTime().value;
        dueDateTime = source.getDueDateTime().value;
        returnedDateTime = source.getReturnedDateTime().value;
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Loan toModelType() throws IllegalValueException {

        // TODO match up to the actual loan methods
        /*
        if (userName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, userName.class.getSimpleuserName()));
        }
        if (!userName.isValidUserName(userName)) {
            throw new IllegalValueException(userName.MESSAGE_CONSTRAINTS);
        }
        final userName modelUserName = new userName(userName);

        if (serialNumber == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, serialNumber.class.getSimpleuserName()));
        }
        if (!serialNumber.isValidSerialNumber(serialNumber)) {
            throw new IllegalValueException(serialNumber.MESSAGE_CONSTRAINTS);
        }
        final serialNumber modelSerialNumber = new serialNumber(serialNumber);

        if (loanDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, loanDateTime.class.getSimpleuserName()));
        }
        if (!loanDateTime.isValidLoanDateTime(loanDateTime)) {
            throw new IllegalValueException(loanDateTime.MESSAGE_CONSTRAINTS);
        }
        final loanDateTime modelLoanDateTime = new loanDateTime(loanDateTime);

        if (dueDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, dueDateTime.class.getSimpleuserName()));
        }
        if (!dueDateTime.isValidDueDateTime(dueDateTime)) {
            throw new IllegalValueException(dueDateTime.MESSAGE_CONSTRAINTS);
        }
        final dueDateTime modelDueDateTime = new dueDateTime(dueDateTime);

        if (returnedDateTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, returnedDateTime.class.getSimpleuserName()));
        }
        if (!returnedDateTime.isValidReturnedDateTime(returnedDateTime)) {
            throw new IllegalValueException(returnedDateTime.MESSAGE_CONSTRAINTS);
        }
        final ReturnedDateTime modelReturnedDateTime = new ReturnedDateTime(returnedDateTime);

         */
//        return new Loan(modelUserName, modelSerialNumber, modelLoanDateTime, modelDueDateTime, modelReturnedDateTime);
        return new Loan();
    }

}
