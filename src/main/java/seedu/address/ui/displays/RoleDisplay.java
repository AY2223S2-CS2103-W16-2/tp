package seedu.address.ui.displays;

import java.time.LocalDate;
import java.util.Comparator;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.address.model.job.Role;

/**
 * A customizable display for Roles. Will be displayed in ResultDisplay.
 */
public final class RoleDisplay {

    private RoleDisplay() {}

    /**
     * Creates a custom RoleDisplay based on the provided Role.
     * @param roleToDisplay The role to be displayed in the UI.
     * @return A VBox Node to be displayed in the UI.
     */
    public static Node of(Role roleToDisplay) {
        Label role = new Label(roleToDisplay.getName().fullName + " @ ");
        role.getStyleClass().add("role-display-headers");

        Label company = new Label(roleToDisplay.getCompany().value);
        company.getStyleClass().add("role-display-headers");

        HBox title = new HBox(role, company);

        FlowPane tags = new FlowPane();
        roleToDisplay.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getStyleClass().add("role-display-tags");

        Label salary = new Label("Salary: $" + roleToDisplay.getSalary().salary);
        salary.getStyleClass().add("role-display-body");

        Label deadlineDesc = new Label("Deadline: ");
        deadlineDesc.getStyleClass().add("role-display-body");
        Label deadline = new Label();
        LocalDate currDeadline = LocalDate.parse(roleToDisplay.getDeadline().deadline);
        if (currDeadline.isBefore(LocalDate.now())) {
            deadline.setText(" EXPIRED ");
            deadline.getStyleClass().add("role-display-body-highlight");
        } else {
            deadline.setText(roleToDisplay.getDeadline().deadline);
            deadline.getStyleClass().add("role-display-body");
        }
        HBox deadlineContainer = new HBox(deadlineDesc, deadline);

        Label experience = new Label("Experience: " + roleToDisplay.getExperience().value);
        experience.getStyleClass().add("role-display-body");

        Label jobDesc = new Label("Job Description: \n" + roleToDisplay.getJobDescription().value);
        jobDesc.getStyleClass().add("role-display-body");

        Label contactHeader = new Label("Contacts:");
        contactHeader.getStyleClass().add("role-display-headers");

        Label email = new Label("Email: " + roleToDisplay.getEmail());
        email.getStyleClass().add("role-display-body");

        Label phone = new Label("Phone No.: " + roleToDisplay.getPhone());
        phone.getStyleClass().add("role-display-body");

        Label website = new Label("Email: " + roleToDisplay.getWebsite());
        website.getStyleClass().add("role-display-body");

        VBox container = new VBox(title, tags, salary, deadlineContainer, experience, jobDesc, contactHeader, email,
                phone, website);
        container.setPadding(new Insets(5, 5, 5, 10));
        container.setSpacing(10);
        return container;
    }
}
