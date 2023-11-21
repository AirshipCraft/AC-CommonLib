package tk.airshipcraft.commonlib.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import tk.airshipcraft.commonlib.gui.events.GuiClickEvent;

/**
 * Abstract class for creating GUIs (Graphical User Interfaces) in Minecraft.
 * This class provides a framework for opening GUIs and handling interactions within them.
 * Extend this class to create specific GUIs with custom behavior and event handling.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public abstract class Gui {

    protected Inventory inventory;

    /**
     * Constructor to initialize the GUI with an inventory.
     *
     * @param inventory The inventory associated with the GUI.
     */
    public Gui(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Handles a click event within this GUI.
     *
     * @param event  The InventoryClickEvent triggered when a player clicks within the inventory.
     * @param player The player who clicked in the GUI.
     */
    public void handleClick(InventoryClickEvent event, Player player) {
        // Create and dispatch a custom GuiClickEvent
        GuiClickEvent guiClickEvent = new GuiClickEvent(player, event.getSlot(), event.getCurrentItem(), inventory);
        Bukkit.getServer().getPluginManager().callEvent(guiClickEvent);

        if (!guiClickEvent.isCancelled()) {
            // Handle the event if it's not cancelled
            handleCustomClick(guiClickEvent);
        }
    }

    /**
     * Abstract method to handle custom GuiClickEvent.
     * This should be implemented by subclasses to define custom behavior for the event.
     *
     * @param event The custom GuiClickEvent.
     */
    public abstract void handleCustomClick(GuiClickEvent event);

    /**
     * Opens the GUI for a specific player.
     * This method should be called to display the GUI to a player.
     *
     * @param player The player to whom the GUI should be shown.
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Gets the inventory associated with this GUI.
     *
     * @return The Inventory object representing this GUI.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
