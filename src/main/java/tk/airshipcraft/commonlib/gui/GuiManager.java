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
 * Manages GUI interactions and dispatches events to the corresponding GUIs.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class GuiManager implements Listener {

    private final Map<Player, Gui> activeGuis = new HashMap<>();

    /**
     * Registers a GUI for a player.
     *
     * @param player The player for whom the GUI is registered.
     * @param gui    The GUI to register.
     */
    public void registerGui(Player player, Gui gui) {
        activeGuis.put(player, gui);
    }

    /**
     * Unregisters the GUI for a player.
     *
     * @param player The player whose GUI should be unregistered.
     */
    public void unregisterGui(Player player) {
        activeGuis.remove(player);
    }

    /**
     * Handles inventory click events and dispatches them to the appropriate GUI.
     *
     * @param event The InventoryClickEvent.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Gui) {
            event.setCancelled(true);
            Gui gui = (Gui) holder;
            gui.handleClick(event, (Player) event.getWhoClicked());
        } else if (event.getWhoClicked() instanceof Player) {
            Player player = (Player) event.getWhoClicked();
            Gui gui = activeGuis.get(player);
            if (gui != null) {
                event.setCancelled(true);
                gui.handleClick(event, player);
            }
        }
    }
}
