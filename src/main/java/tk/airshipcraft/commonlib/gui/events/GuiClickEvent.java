package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * Represents an event triggered when a player interacts with a Graphical User Interface (GUI) in the game.
 * This event class encapsulates all the relevant information about the interaction, including the player involved,
 * the specific item clicked, the slot index where the click occurred, and the inventory involved in the GUI.
 * This event can be cancelled, allowing developers to control the outcome of the interaction.
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
     * Constructs a new GuiClickEvent with the specified details of the player's interaction with the GUI.
     *
     * @param player    The player who clicked in the GUI, not null.
     * @param slot      The slot index in the inventory that was clicked.
     * @param item      The ItemStack present at the clicked slot, can be null if the slot is empty.
     * @param inventory The inventory object associated with the GUI being interacted with, not null.
     */
    public GuiClickEvent(Player player, int slot, ItemStack item, Inventory inventory) {
        this.player = player;
        this.slot = slot;
        this.item = item;
        this.inventory = inventory;
        this.cancelled = false;
    }

    /**
     * Returns the HandlerList for this event type.
     * Required for custom event implementation in Bukkit.
     *
     * @return The static HandlerList for GuiClickEvent.
     */
    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * Gets the player who interacted with the GUI.
     *
     * @return The Player instance representing the player who clicked in the GUI.
     */
    public Player getWhoClicked() {
        return player;
    }

    /**
     * Gets the index of the slot that was clicked in the GUI.
     *
     * @return The zero-based index of the clicked slot.
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Retrieves the ItemStack at the slot that was clicked.
     *
     * @return The ItemStack at the clicked slot, or null if the slot is empty.
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * Gets the Inventory object associated with the GUI that was clicked.
     *
     * @return The Inventory instance representing the GUI's inventory.
     */
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * Checks if the event has been cancelled.
     * A cancelled event will not proceed with the default interaction behavior.
     *
     * @return True if the event is cancelled, false otherwise.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * Sets the cancellation state of the event.
     * Cancelling the event can prevent the default interaction behavior associated with the GUI click.
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
