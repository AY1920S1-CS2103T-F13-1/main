package seedu.address.model.borrower;

import java.util.Objects;

import seedu.address.model.loan.Loan;
import seedu.address.model.loan.LoanList;

/**
 * Represents a Borrower.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Borrower {

    private final Name name;
    private final Phone phone;
    private final Email email;
    private final BorrowerId borrowerId;
    private final LoanList currentLoanList;
    private final LoanList returnedLoanList;

    public Borrower(Name name, Phone phone, Email email) {
        this(name, phone, email, BorrowerIdGenerator.generateBorrowerId());
    }

    /**
     * only for test
     * @param name name of borrower
     * @param phone phone of borrower
     * @param email email of borrower
     * @param borrowerId is manually input for testing purpose.
     */
    public Borrower(Name name, Phone phone, Email email, BorrowerId borrowerId) {
        this(name, phone, email, borrowerId, new LoanList(), new LoanList());
    }

    public Borrower(Name name, Phone phone, Email email, BorrowerId borrowerId,
                    LoanList currentLoanList, LoanList returnedLoanList) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.borrowerId = borrowerId;
        this.currentLoanList = currentLoanList;
        this.returnedLoanList = returnedLoanList;
    }

    public Name getName() {
        return name;
    }

    public BorrowerId getBorrowerId() {
        return borrowerId;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public LoanList getCurrentLoanList() {
        return currentLoanList;
    }

    public LoanList getReturnedLoanList() {
        return returnedLoanList;
    }

    public LoanList getAddedCurrentLoanList(Loan newLoan) {
        return currentLoanList.addToNewCopy(newLoan);
    }

    public LoanList getRemovedCurrentLoanList(Loan returnedLoan) {
        return currentLoanList.removeFromNewCopy(returnedLoan);
    }

    public LoanList getAddedReturnedLoanList(Loan returnedLoan) {
        return returnedLoanList.addToNewCopy(returnedLoan);
    }

    /**
     * Adds a new Loan object representing a book loaned by the Borrower.
     */
//    public void addNewLoan(Loan loan) {
//        currentLoanList.addToNewCopy(loan);
//    }

    /**
     * Returns true if Borrower currently loans a Book represented by the given Loan object.
     */
    public boolean hasCurrentLoan(Loan loan) {
        return currentLoanList.contains(loan);
    }

    /**
     * Returns a Book that is borrowed by the Borrower.
     *
     * @param loan Loan object that is being returned.
     */
//    public void returnLoan(Loan loan) {
//        assert hasCurrentLoan(loan) : "Borrower does not have the loan to be returned.";
//
//        currentLoanList.removeFromNewCopy(loan);
//        returnedLoanList.addToNewCopy(loan);
//    }

    /**
     * Returns true if both borrowers have the same borrower_id.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameBorrower(Borrower otherBorrower) {
        if (otherBorrower == this) {
            return true;
        }

        return otherBorrower != null
                && otherBorrower.getBorrowerId().equals(getBorrowerId());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Borrower)) {
            return false;
        }

        Borrower otherBorrower = (Borrower) other;
        return otherBorrower.getName().equals(getName())
                && otherBorrower.getPhone().equals(getPhone())
                && otherBorrower.getEmail().equals(getEmail())
                && otherBorrower.getBorrowerId().equals(getBorrowerId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, borrowerId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Borrower Id: ")
                .append(getBorrowerId());
        return builder.toString();
    }

}
