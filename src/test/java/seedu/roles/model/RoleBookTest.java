package seedu.roles.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.roles.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.roles.testutil.Assert.assertThrows;
import static seedu.roles.testutil.TypicalRoles.ALICE;
import static seedu.roles.testutil.TypicalRoles.getTypicalRoleBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.roles.model.job.Role;
import seedu.roles.model.job.exceptions.DuplicateRoleException;
import seedu.roles.testutil.RoleBuilder;

public class RoleBookTest {

    private final RoleBook roleBook = new RoleBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), roleBook.getRoleList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyRoleBook_replacesData() {
        RoleBook newData = getTypicalRoleBook();
        roleBook.resetData(newData);
        assertEquals(newData, roleBook);
    }

    @Test
    public void resetData_withDuplicateRoles_throwsDuplicateRoleException() {
        // Two roles with the same identity fields
        Role editedAlice = new RoleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        List<Role> newRoles = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newRoles);

        assertThrows(DuplicateRoleException.class, () -> roleBook.resetData(newData));
    }

    @Test
    public void hasRole_nullRole_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> roleBook.hasRole(null));
    }

    @Test
    public void hasRole_roleNotInAddressBook_returnsFalse() {
        assertFalse(roleBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleInAddressBook_returnsTrue() {
        roleBook.addRole(ALICE);
        assertTrue(roleBook.hasRole(ALICE));
    }

    @Test
    public void hasRole_roleWithSameIdentityFieldsInAddressBook_returnsTrue() {
        roleBook.addRole(ALICE);
        Role editedAlice = new RoleBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(roleBook.hasRole(editedAlice));
    }

    @Test
    public void getRoleList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> roleBook.getRoleList().remove(0));
    }

    /**
     * A stub ReadOnlyRoleBook whose roles list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyRoleBook {
        private final ObservableList<Role> roles = FXCollections.observableArrayList();

        AddressBookStub(Collection<Role> roles) {
            this.roles.setAll(roles);
        }

        @Override
        public ObservableList<Role> getRoleList() {
            return roles;
        }
    }

}