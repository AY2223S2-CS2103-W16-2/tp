package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.job.Role;

import static seedu.address.logic.commands.CommandTestUtil.*;

/**
 * A utility class containing a list of {@code Role} objects to be used in tests.
 */
public class TypicalRoles {

    public static final Role ALICE = new RoleBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withTags("friends").withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role BENSON = new RoleBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withTags("owesMoney", "friends").withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role CARL = new RoleBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role DANIEL = new RoleBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role ELLE = new RoleBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role FIONA = new RoleBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role GEORGE = new RoleBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withSalary("4000").withDeadline("2023-10-20").build();

    // Manually added
    public static final Role HOON = new RoleBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withSalary("4000").withDeadline("2023-10-20").build();
    public static final Role IDA = new RoleBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withSalary("4000").withDeadline("2023-10-20").build();

    // Manually added - Role's details found in {@code CommandTestUtil}
    public static final Role AMY = new RoleBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withSalary(VALID_SALARY_AMY).withDeadline(VALID_DEADLINE_AMY).build();
    public static final Role BOB = new RoleBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withSalary(VALID_SALARY_BOB).withDeadline(VALID_DEADLINE_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalRoles() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical roles.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Role role : getTypicalRoles()) {
            ab.addRole(role);
        }
        return ab;
    }

    public static List<Role> getTypicalRoles() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
