package tk.airshipcraft.commonlib.calendar;

import java.time.LocalDate;

/**
 * Interface for managing and scheduling in-game events in Minecraft.
 * Provides methods to schedule events based on in-game dates and trigger them accordingly.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public interface IEventManager {

    /**
     * Schedules an event to occur on a specific in-game date.
     *
     * @param date  The date on which the event should occur.
     * @param event The event to be scheduled.
     */
    void scheduleEvent(LocalDate date, GameEvent event);

    /**
     * Triggers all events scheduled for the current in-game date.
     *
     * @param currentDate The current in-game date.
     */
    void triggerEvents(LocalDate currentDate);

    /**
     * Represents a generic game event with a trigger method.
     * This is an inner class or interface that should be implemented by specific event types.
     */
    abstract class GameEvent {
        /**
         * Triggers the event action.
         */
        public abstract void trigger();
    }
}
