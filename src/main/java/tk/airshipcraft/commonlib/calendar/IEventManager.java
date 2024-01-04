package tk.airshipcraft.commonlib.calendar;

import tk.airshipcraft.commonlib.calendar.clock.CustomDate;
import tk.airshipcraft.commonlib.calendar.impl.AbstractGameEvent;
import tk.airshipcraft.commonlib.calendar.impl.EventManager;

import java.time.LocalDate;

/**
 * Interface for managing and scheduling in-game events in Minecraft.
 * Provides methods to schedule events based on in-game dates and trigger them accordingly.
 * <p>
 * This interface is implemented by the {@link EventManager}
 * </p>
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
    void scheduleEvent(LocalDate date, AbstractGameEvent event);

    /**
     * Triggers all events scheduled for the current in-game date.
     *
     * @param currentDate The current in-game date.
     */
    void triggerCustomEvents(CustomDate currentDate);

    /**
     * Triggers all events scheduled for the current real life date.
     *
     * @param currentDate The current LocalDate.
     */
    void triggerEvents(LocalDate currentDate);
}
