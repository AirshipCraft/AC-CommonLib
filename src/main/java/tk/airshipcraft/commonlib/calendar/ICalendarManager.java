package tk.airshipcraft.commonlib.calendar;

import tk.airshipcraft.commonlib.calendar.clock.CustomDate;

/**
 * Interface for managing the in-game calendar in Minecraft.
 *
 * @author notzune
 * @version 2.0.0
 * @since 2024-01-04
 */
public interface ICalendarManager {

    /**
     * Advances the calendar by one day.
     */
    void newMinecraftDay();

    /**
     * Gets the current in-game date.
     *
     * @return The current in-game date.
     */
    CustomDate getCurrentDate();

    /**
     * Sets the current in-game date.
     *
     * @param newDate The new in-game date.
     */
    void setCurrentDate(CustomDate newDate);

    /**
     * Advances the calendar by a specified number of days.
     *
     * @param days The number of days to advance the calendar.
     */
    void advanceDays(int days);

    /**
     * Returns the number of days until the specified date.
     *
     * @param date The date to calculate the number of days until.
     * @return The number of days until the specified date.
     */
    int daysUntil(CustomDate date);
}
