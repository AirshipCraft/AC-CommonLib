package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.airshipcraft.commonlib.utils.Hologram;

/**
 * Represents an event that is fired when a player clicks on a hologram within the game.
 * This event class provides details about the hologram interaction, including the player who clicked and the specific hologram.
 * It can be used to handle custom actions upon hologram interactions and can be cancelled to prevent default behavior.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-11
 */
public class HologramClickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Hologram hologram;
    private final Player player;
    private boolean cancelled = false;

    /**
     * Constructs a HologramClickEvent with the specified hologram and the player who interacted with it.
     *
     * @param hologram The Hologram object that was clicked on.
     * @param player   The Player who clicked on the hologram.
     */
    public HologramClickEvent(Hologram hologram, Player player) {
        this.hologram = hologram;
        this.player = player;
    }

    /**
     * Returns the HandlerList for this event type.
     * Required for custom event implementation in Bukkit.
     *
     * @return The static HandlerList for HologramClickEvent.
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * Retrieves the hologram that was clicked on by the player.
     *
     * @return The Hologram instance representing the hologram that was interacted with.
     */
    public Hologram getHologram() {
        return hologram;
    }

    /**
     * Gets the player who clicked on the hologram.
     *
     * @return The Player instance representing the player who clicked on the hologram.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Checks if the event has been cancelled.
     * A cancelled event will not proceed with the default interaction behavior.
     *
     * @return True if the event is cancelled, false otherwise.
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets the cancellation state of the event.
     * Cancelling the event can prevent the default interaction behavior associated with the hologram click.
     *
     * @param cancelled True to cancel the event, false to allow it to proceed.
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Returns the handlers associated with this event.
     * Part of the Bukkit event handling mechanism.
     *
     * @return The HandlerList for this event.
     */
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
