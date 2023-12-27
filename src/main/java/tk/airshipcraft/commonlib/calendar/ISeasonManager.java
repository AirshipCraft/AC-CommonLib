package tk.airshipcraft.commonlib.calendar;

import tk.airshipcraft.commonlib.calendar.impl.SeasonManager;

/**
 * Interface for managing seasonal changes in the Minecraft world.
 * Provides methods to determine and query the current season based on the in-game date.
 * <p>
 * This interface is implemented by {@link SeasonManager}
 * <p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27 */
public interface ISeasonManager {

    /**
     * Enumeration of seasons.
     */
    enum Season {
        SPRING,
        SUMMER,
        AUTUMN,
        WINTER
    }

    /**
     * Determines the current season based on the current in-game date.
     *
     * @return The current season as a {@link Season} enum value.
     */
    Season getCurrentSeason();
}
