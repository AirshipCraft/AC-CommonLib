package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.airshipcraft.commonlib.gui.objects.Ui;
import tk.airshipcraft.commonlib.utils.UiDesigner;

/**
 * This class listens to inventory click events and determines if the event should
 * trigger a custom GUI click event based on whether the inventory clicked is a custom UI.
 * It serves as a bridge between the Bukkit inventory events and the custom UI system.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class InventoryClickListener implements Listener {

    /**
     * Handles the InventoryClickEvent by checking if it was triggered from a custom UI.
     * If so, it fires a GuiClickEvent and performs any defined click actions.
     *
     * @param event The InventoryClickEvent provided by the Bukkit API.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return; // Ensure a player triggered the event

        // Check if the inventory clicked is a custom UI
        if (Ui.isUi(event.getClickedInventory())) {
            // Create and call a custom GUI click event
            Bukkit.getServer().getPluginManager().callEvent(new GuiClickEvent(
                    (Player) event.getWhoClicked(),
                    event.getSlot(),
                    event.getCurrentItem(),
                    event.getClickedInventory()
            ));

            // Execute any defined actions for the clicked UI element
            UiDesigner.callClickAction(event.getInventory(), event.getSlot());
        }
    }

    /**
     * A utility method to safely cast and handle the inventory click event.
     * This encapsulates type-checking and casting within a single method to avoid redundant checks.
     *
     * @param event The InventoryClickEvent to handle.
     * @return True if the event was successfully handled, false otherwise.
     */
    public boolean handleInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return false;
        }

        Player player = (Player) event.getWhoClicked();
        if (Ui.isUi(event.getClickedInventory())) {
            GuiClickEvent guiClickEvent = new GuiClickEvent(
                    player,
                    event.getSlot(),
                    event.getCurrentItem(),
                    event.getClickedInventory()
            );

            Bukkit.getServer().getPluginManager().callEvent(guiClickEvent);
            if (!guiClickEvent.isCancelled()) {
                UiDesigner.callClickAction(event.getInventory(), event.getSlot());
                return true;
            }
        }

        return false;
    }
}
