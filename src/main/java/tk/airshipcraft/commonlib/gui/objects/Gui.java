package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import tk.airshipcraft.commonlib.gui.events.GuiClickEvent;

/**
 * Abstract base class for creating custom Graphical User Interfaces (GUIs) in Minecraft.
 * This class provides a foundational framework for GUI creation, including inventory initialization, event handling,
 * and interaction management. It is designed to be extended by subclasses to create specific types of GUIs with
 * custom behavior and event handling tailored to the needs of different plugins or functionalities.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public abstract class Gui {

    protected Inventory inventory;

    /**
     * Constructs a new Gui instance with the provided inventory.
     *
     * @param inventory The Inventory object associated with this GUI, representing the interface layout and contents.
     */
    public Gui(Inventory inventory) {
        this.inventory = inventory;
    }

    /**
     * Handles a click event within the GUI.
     * This method is responsible for creating and dispatching a custom GuiClickEvent upon player interaction with the GUI.
     * It also provides a point of extension for handling the event after its creation and dispatch.
     *
     * @param event  The InventoryClickEvent triggered when a player clicks an item within the inventory.
     * @param player The player who clicked in the GUI.
     */
    public void handleClick(InventoryClickEvent event, Player player) {
        GuiClickEvent guiClickEvent = new GuiClickEvent(player, event.getSlot(), event.getCurrentItem(), inventory);
        Bukkit.getServer().getPluginManager().callEvent(guiClickEvent);

        if (!guiClickEvent.isCancelled()) {
            handleCustomClick(guiClickEvent);  // Handle custom behavior if the event is not cancelled.
        }
    }

    /**
     * Abstract method for handling custom GuiClickEvent.
     * Subclasses should override this method to define specific behaviors for GUI interactions.
     *
     * @param event The custom GuiClickEvent to be handled.
     */
    public abstract void handleCustomClick(GuiClickEvent event);

    /**
     * Opens the GUI for a specified player.
     * This method should be invoked to display the GUI, making it visible and interactive for the player.
     *
     * @param player The player for whom the GUI should be opened.
     */
    public void open(Player player) {
        player.openInventory(inventory);
    }

    /**
     * Retrieves the Inventory object associated with this GUI.
     * This inventory represents the layout and content of the GUI.
     *
     * @return The Inventory object representing this GUI.
     */
    public Inventory getInventory() {
        return inventory;
    }
}
