package tk.airshipcraft.commonlib.calendar;

/**
 * Represents a generic interface for in-game events within the AirshipCraft calendar system.
 * Implementations of this interface should define the specific actions to be performed
 * when an event is triggered.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2024-01-04
 */
public interface IGameEvent {

    /**
     * Triggers the specific action or series of actions associated with this game event.
     * Implementing classes should define the behavior of this method to execute event-specific logic.
     */
    void trigger();
}
