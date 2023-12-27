package tk.airshipcraft.commonlib.calendar.impl;

import tk.airshipcraft.commonlib.calendar.impl.CalendarManager;

import java.time.LocalDate;

/**
 * The SeasonManager class is responsible for managing seasonal changes in the Minecraft world.
 * It determines the current season based on the in-game date and provides functionalities to
 * query the current season. This class plays a crucial role in the calendar system by integrating
 * seasonal effects with in-game time.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class SeasonManager {

    /**
     * Enumeration of seasons.
     */
    public enum Season {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }

    private CalendarManager calendarManager;

    /**
     * Constructor for SeasonManager that requires a reference to a CalendarManager.
     *
     * @param calendarManager The CalendarManager instance to use for date calculations.
     */
    public SeasonManager(CalendarManager calendarManager) {
        this.calendarManager = calendarManager;
    }

    /**
     * Determines the current season based on the current in-game date.
     *
     * @return The current season as a {@link Season} enum value.
     */
    public Season getCurrentSeason() {
        LocalDate currentDate = calendarManager.getCurrentDate();
        int month = currentDate.getMonthValue();

        if (month >= 3 && month <= 5) { // March to May
            return Season.SPRING;
        } else if (month >= 6 && month <= 8) { // June to August
            return Season.SUMMER;
        } else if (month >= 9 && month <= 11) { // September to November
            return Season.AUTUMN;
        } else {
            return Season.WINTER; // December to February
        }
    }
}
