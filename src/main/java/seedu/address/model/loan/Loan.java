package seedu.address.model.loan;

import seedu.address.model.person.Address;
import seedu.address.model.person.Name;

public class Loan {

    private String id = "13245";
    private String name;

    public Loan (String name) {
        this.name = name;
    }

    public Loan() {

    }

    public Address getSerialNumber() {
        return new Address("sn");
    }

    public Address getLoanDateTime() {
        return new Address("ldt");
    }

    public Address getDueDateTime() {
        return new Address("ddt");
    }

    public Address getReturnedDateTime() {
        return new Address("rdt");
    }

    public Name getUserName() {
        return new Name("username");
    }
}
