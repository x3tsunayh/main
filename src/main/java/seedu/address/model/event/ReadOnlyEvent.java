package seedu.address.model.event;

import javafx.beans.property.ObjectProperty;

//@@author x3tsunayh

/**
 * Interface for Events related to Calendar View feature.
 */
public interface CalendarEvent {
    ObjectProperty<String> titleProperty();

    String getTitle();

    ObjectProperty<String> descriptionProperty();

    String getDescription();

    ObjectProperty<String> locationProperty();

    String getLocation();

    ObjectProperty<Datetime> datetimeProperty();

    Datetime getDatetime();

    /**
     * Checks if @param other is of the same state as this.
     */
    default boolean isSameStateAs(CalendarEvent other) {
        return other == this // short circuit if same object
                || (other != null
                && other.getTitle().equals(this.getTitle())
                && other.getDescription().equals(this.getDescription())
                && other.getLocation().equals(this.getLocation())
                && other.getDatetime().equals(this.getDatetime()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Description: ")
                .append(getDescription())
                .append(" Location: ")
                .append(getLocation())
                .append(" Datetime: ")
                .append(getDatetime());
        return builder.toString();
    }
}
