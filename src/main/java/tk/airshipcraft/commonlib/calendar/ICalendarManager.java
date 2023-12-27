package tk.airshipcraft.commonlib.calendar;

import java.time.LocalDate;

/**
 * Interface for managing the in-game calendar in Minecraft.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
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
    LocalDate getCurrentDate();

    /**
     * Advances the calendar by a specified number of days.
     *
     * @param days The number of days to advance the calendar.
     */
    void advanceDays(int days);

    /**
     * Sets the current in-game date.
     *
     * @param newDate The new in-game date.
     */
    void setCurrentDate(LocalDate newDate);

    /**
     * Returns the number of days until the specified date.
     *
     * @param date The date to calculate the number of days until.
     * @return The number of days until the specified date.
     */
    long daysUntil(LocalDate date);
}
