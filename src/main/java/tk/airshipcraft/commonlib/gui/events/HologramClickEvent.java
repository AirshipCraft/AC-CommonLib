package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import tk.airshipcraft.commonlib.utils.Hologram;

/**
 * This event is fired whenever a player clicks on a hologram.
 */
public class HologramClickEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private final Hologram hologram;
    private final Player player;
    private boolean cancelled = false;

    /**
     * Constructor for the HologramClickEvent class.
     *
     * @param hologram The hologram that was clicked on.
     * @param player   The player that clicked on the hologram.
     */
    public HologramClickEvent(Hologram hologram, Player player) {
        this.hologram = hologram;
        this.player = player;
    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * Returns the hologram that was clicked on.
     *
     * @return The hologram that was clicked on.
     */
    public Hologram getHologram() {
        return hologram;
    }

    /**
     * Returns the player that clicked on the hologram.
     *
     * @return The player that clicked on the hologram.
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Returns whether the event is cancelled or not.
     *
     * @return Whether the event is cancelled or not.
     */
    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    /**
     * Sets whether the event is cancelled or not.
     *
     * @param cancelled Whether the event is cancelled or not.
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
