package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

public class GuiClickEvent extends Event implements Cancellable, Listener {

    private static final HandlerList HANDLERS = new HandlerList();
    private final Player player;
    private final int slot;
    private final ItemStack item;
    private final Inventory inventory;
    private boolean cancelled;

    /**
     * @param player    the player who has the GUI open. Must be a player
     * @param slot      the clicked slot
     * @param item      the clicked item
     * @param inventory the inventory that is hosting the GUI
     */
    public GuiClickEvent(Player player, int slot, ItemStack item, Inventory inventory) {
        this.player = player;
        this.slot = slot;
        this.item = item;
        this.inventory = inventory;
        this.cancelled = false;

    }

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    /**
     * @return the player that is currently using the GUI
     */
    public InventoryHolder getOwner() {
        return player;
    }

    /**
     * @return the clicked slot
     */

    public int getSlot() {
        return slot;
    }

    /**
     * @return the item or button that the player clicked
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @return the slot of the inventory that has been clicked
     */
    public int getClickedSlot() {
        return this.slot;
    }

    /**
     * @return the inventory that hosts the GUI
     */
    public Inventory getUiInventory() {
        return this.inventory;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    /**
     * @return the player that clicked in the GUI
     */
    public Player getWhoClicked() {
        return player;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}
