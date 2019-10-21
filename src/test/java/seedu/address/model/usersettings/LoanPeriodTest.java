package seedu.address.model.usersettings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LoanPeriodTest {
    private static final int TEST_LOAN_PERIOD = 14;

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LoanPeriod(null));
    }

    @Test
    public void constructor_invalidLoanPeriod_throwsIllegalArgumentException() {
        String invalidLoanPeriod = "";
        assertThrows(IllegalArgumentException.class, () -> new LoanPeriod(invalidLoanPeriod));
    }

    @Test
    public void isValidLoanPeriod() {
        // null loanPeriod
        assertThrows(NullPointerException.class, () -> LoanPeriod.isValidLoanPeriod(null));

        // invalid loanPeriod
        assertFalse(LoanPeriod.isValidLoanPeriod("")); // empty string
        assertFalse(LoanPeriod.isValidLoanPeriod(" ")); // spaces only
        assertFalse(LoanPeriod.isValidLoanPeriod("^")); // only non-alphanumeric characters
        assertFalse(LoanPeriod.isValidLoanPeriod("hello*")); // contains non-alphanumeric characters
        assertFalse(LoanPeriod.isValidLoanPeriod("hello world")); // alphabets only
        assertFalse(LoanPeriod.isValidLoanPeriod("-1")); // negative integer

        // valid loanPeriod
        assertTrue(LoanPeriod.isValidLoanPeriod("12345")); // numbers only
    }

    @Test
    public void toString_success() {
        LoanPeriod lp = new LoanPeriod(TEST_LOAN_PERIOD);
        assertEquals(lp.toString(), String.format("%d", TEST_LOAN_PERIOD));
    }

    @Test
    public void equals() {
        LoanPeriod lp1 = new LoanPeriod(TEST_LOAN_PERIOD);
        LoanPeriod lp2 = new LoanPeriod(TEST_LOAN_PERIOD);
        LoanPeriod lp3 = new LoanPeriod(TEST_LOAN_PERIOD + 1);
        assertEquals(lp1, lp2);
        assertNotEquals(lp1, lp3);
    }
}
