package tk.airshipcraft.commonlib.calendar;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The CalendarManager class is responsible for managing the in-game calendar in Minecraft.
 * It provides functionalities to get the current in-game date, advance the date, and handle
 * date-related operations. This class is a part of the commonlib calendar system and serves
 * as a core component for date and time management.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class CalendarManager {

    /**
     * The current in-game date. It is represented as a {@link LocalDate} object.
     */
    private LocalDate currentDate;

    /**
     * Initializes a new CalendarManager with the current real-world date.
     */
    public CalendarManager() {
        this.currentDate = LocalDate.now(); // You can change this to a specific starting date
    }

    /**
     * Advances the in-game date by a specified number of days.
     *
     * @param days The number of days to advance. Must be a non-negative integer.
     */
    public void advanceDays(int days) {
        if (days < 0) {
            throw new IllegalArgumentException("Days to advance must be non-negative.");
        }
        currentDate = currentDate.plusDays(days);
    }

    /**
     * Gets the current in-game date.
     *
     * @return The current in-game date as a {@link LocalDate}.
     */
    public LocalDate getCurrentDate() {
        return currentDate;
    }

    /**
     * Sets the in-game date to a specific date.
     *
     * @param newDate The new date to set as the current in-game date.
     * @throws IllegalArgumentException if the newDate is before the current in-game date.
     */
    public void setCurrentDate(LocalDate newDate) {
        if (newDate.isBefore(currentDate)) {
            throw new IllegalArgumentException("New date must be after the current in-game date.");
        }
        currentDate = newDate;
    }

    /**
     * Calculates the number of days between the current in-game date and a specified date.
     *
     * @param date The date to compare with the current in-game date.
     * @return The number of days between the current in-game date and the specified date.
     */
    public long daysUntil(LocalDate date) {
        return ChronoUnit.DAYS.between(currentDate, date);
    }
}
