package tk.airshipcraft.commonlib.calendar.impl;

import tk.airshipcraft.commonlib.calendar.IEventManager;
import tk.airshipcraft.commonlib.calendar.clock.CustomDate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * The EventManager class is responsible for managing and scheduling in-game events in the Minecraft world.
 * It allows for the creation, scheduling, and triggering of events based on in-game dates, providing a dynamic
 * gameplay experience that changes with the calendar.
 * </p>
 * <p>
 * The GameEvent abstract class provides a structure for creating different types of in-game events.
 * Subclasses of GameEvent can define specific behaviors in their trigger method
 * </p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-27
 */
public class EventManager implements IEventManager {

    /**
     * A map to hold scheduled events, keyed by the date on which they occur.
     */
    private Map<LocalDate, List<GameEvent>> scheduledEvents;

    /**
     * Initializes a new EventManager. Creates a new empty map to hold scheduled events.
     */
    public EventManager() {
        scheduledEvents = new HashMap<>();
    }

    /**
     * Schedules an event to occur on a specific in-game date.
     *
     * @param date  The date on which the event should occur.
     * @param event The event to be scheduled.
     */
    @Override
    public void scheduleEvent(LocalDate date, GameEvent event) {
        scheduledEvents.computeIfAbsent(date, k -> new ArrayList<>()).add(event);
    }

    /**
     * Triggers all events scheduled for the current in-game date.
     *
     * @param currentDate The current in-game date.
     */
    @Override
    public void triggerCustomEvents(CustomDate currentDate) {
        List<GameEvent> eventsToday = scheduledEvents.getOrDefault(currentDate, new ArrayList<>());
        for (GameEvent event : eventsToday) {
            event.trigger();
        }
    }

    /**
     * Triggers all events scheduled for the current in-game date.
     *
     * @param currentDate The current in-game date.
     */
    @Override
    public void triggerEvents(LocalDate currentDate) {
        List<GameEvent> eventsToday = scheduledEvents.getOrDefault(currentDate, new ArrayList<>());
        for (GameEvent event : eventsToday) {
            event.trigger();
        }
    }

    /**
     * Represents a generic game event with a trigger method.
     */
    public static abstract class GameEvent {
        /**
         * Triggers the event action.
         */
        public abstract void trigger();
    }
}
