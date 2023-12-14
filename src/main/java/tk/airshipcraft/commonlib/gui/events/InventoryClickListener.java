package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.airshipcraft.commonlib.gui.UiDesigner;
import tk.airshipcraft.commonlib.gui.objects.Ui;

/**
 * Listener class for inventory click events in Bukkit.
 * This class detects clicks in custom UIs and triggers appropriate custom GUI click events.
 * It acts as a mediator between Bukkit's inventory system and the custom UI framework, allowing for integrated event handling.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class InventoryClickListener implements Listener {

    /**
     * Processes InventoryClickEvents, checking if they occur within a custom UI.
     * If the event occurs in a custom UI, a GuiClickEvent is fired, and associated actions are performed.
     * This method is crucial for integrating Bukkit's inventory interactions with the custom UI system.
     *
     * @param event The InventoryClickEvent triggered by a player's interaction with an inventory.
     */
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return; // Ensures that the event is triggered by a player

        // Check if the inventory clicked is a custom UI
        if (Ui.isUi(event.getClickedInventory())) {
            // Create and trigger a custom GUI click event
            Bukkit.getServer().getPluginManager().callEvent(new GuiClickEvent(
                    (Player) event.getWhoClicked(),
                    event.getSlot(),
                    event.getCurrentItem(),
                    event.getClickedInventory()
            ));

            // Execute actions defined for the clicked UI element
            UiDesigner.callClickAction(event.getInventory(), event.getSlot());
        }
    }

    /**
     * A utility method to handle the casting and processing of InventoryClickEvents.
     * This method encapsulates the logic for checking if an event is associated with a custom UI and handling it accordingly.
     * It returns a boolean indicating whether the event was processed as part of a custom UI interaction.
     *
     * @param event The InventoryClickEvent to be processed.
     * @return True if the event corresponds to a custom UI interaction and was handled, false otherwise.
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
