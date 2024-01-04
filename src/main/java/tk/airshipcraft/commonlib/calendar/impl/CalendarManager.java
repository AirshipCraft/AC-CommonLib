package tk.airshipcraft.commonlib.calendar.impl;

import tk.airshipcraft.commonlib.calendar.ICalendarManager;
import tk.airshipcraft.commonlib.calendar.clock.CustomDate;

/**
 * The CalendarManager class is responsible for managing the in-game calendar in Minecraft.
 * It provides functionalities to get the current in-game date, advance the date, and handle
 * date-related operations. This class is a part of the commonlib calendar system and serves
 * as a core component for date and time management.
 *
 * @author notzune
 * @version 2.0.0
 * @since 2024-1-4
 */
public class CalendarManager implements ICalendarManager {

    private CustomDate currentDate;

    /**
     * Initializes a new CalendarManager with a specified starting date or default date.
     */
    public CalendarManager(int startYear, int startMonth, int startDay) {
        this.currentDate = new CustomDate(startYear, startMonth, startDay);
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
        currentDate.addDays(days);
    }

    /**
     * Advances the calendar by one day. This method is intended to be called when a new Minecraft day starts.
     */
    public void newMinecraftDay() {
        advanceDays(1); // Advance the calendar by one day
    }

    /**
     * Gets the current in-game date in the custom calendar format.
     *
     * @return The current in-game date as a CustomDate.
     */
    public CustomDate getCurrentDate() {
        return currentDate;
    }

    /**
     * Sets the in-game date to a specific date.
     *
     * @param newDate The new date to set as the current in-game date.
     */
    public void setCurrentDate(CustomDate newDate) {
        // Add logic to ensure the new date is not before the current date
        this.currentDate = newDate;
    }

    /**
     * Calculates the number of days until a specified date.
     *
     * @param targetDate The date to calculate the days until.
     * @return The number of days until the target date.
     */
    public int daysUntil(CustomDate targetDate) {
        return currentDate.daysUntil(targetDate);
    }
}
