package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Event triggered when a player interacts with a GUI.
 * This class encapsulates all relevant information about the interaction, such as:
 * the player, the clicked item, and the slot.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-09
 */
public class GuiClickEvent extends Event implements Cancellable {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final int slot;
    private final ItemStack item;
    private final Inventory inventory;
    private boolean cancelled;

    /**
     * Constructs a new GuiClickEvent with the specified details of the interaction.
     *
     * @param player    The player who clicked in the GUI.
     * @param slot      The slot index that was clicked.
     * @param item      The ItemStack at the clicked slot, can be null.
     * @param inventory The inventory associated with the GUI.
     */
    public GuiClickEvent(Player player, int slot, ItemStack item, Inventory inventory) {
        this.player = player;
        this.slot = slot;
        this.item = item;
        this.inventory = inventory;
        this.cancelled = false;
    }

    /**
     * Gets the HandlerList for this event.
     *
     * @return The handler list.
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * Gets the player who clicked in the GUI.
     *
     * @return The player involved in this event.
     */
    public Player getWhoClicked() {
        return player;
    }

    /**
     * Gets the slot index that was clicked in the GUI.
     *
     * @return The clicked slot index.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Gets the ItemStack at the clicked slot in the GUI.
     *
     * @return The clicked item, or null if the slot is empty.
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Gets the inventory associated with the GUI.
     *
     * @return The inventory of the GUI.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Checks if the event is cancelled.
     *
     * @return True if the event is cancelled, false otherwise.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets the cancellation state of this event.
     *
     * @param cancelled True to cancel the event, false otherwise.
     */
    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    /**
     * Gets the handlers for this event.
     *
     * @return The handler list.
     */
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
