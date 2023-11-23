package tk.airshipcraft.commonlib.gui;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import tk.airshipcraft.commonlib.gui.objects.Gui;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides management for GUI interactions within the server. It keeps track of active GUI instances
 * associated with players and dispatches inventory click events to the appropriate custom GUI handlers.
 * As a Listener, it is responsible for intercepting events from the Bukkit event system and processing
 * them as needed for custom GUI functionality.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class GuiManager implements Listener {

    private final Map<Player, Gui> activeGuis = new HashMap<>();

    /**
     * Associates a GUI instance with a player for event handling.
     * This method registers the GUI to ensure that click events within it are processed correctly.
     *
     * @param player The player for whom the GUI is to be registered.
     * @param gui    The Gui instance to associate with the player.
     */
    public void registerGui(Player player, Gui gui) {
        activeGuis.put(player, gui);
    }

    /**
     * Removes the association between a player and a GUI.
     * This is typically called when a GUI is no longer active or when the player closes it.
     *
     * @param player The player whose association with a GUI is to be removed.
     */
    public void unregisterGui(Player player) {
        activeGuis.remove(player);
    }

    /**
     * Event handler that intercepts inventory click events within the server.
     * It checks if the event corresponds to an active custom GUI and, if so, cancels the default event behavior
     * and passes the event to the custom GUI's own click handling logic.
     *
     * @param event The InventoryClickEvent triggered by the player's interaction with an inventory.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Gui) {
            event.setCancelled(true);  // Prevent default inventory click behavior
            Gui gui = (Gui) holder;    // Cast to Gui and handle the click
            gui.handleClick(event, (Player) event.getWhoClicked());
        } else if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Gui gui = activeGuis.get(player);
            if (gui != null) {
                event.setCancelled(true);  // Prevent default inventory click behavior
                gui.handleClick(event, player);  // Handle the click within the custom GUI logic
            }
        }
    }
}
