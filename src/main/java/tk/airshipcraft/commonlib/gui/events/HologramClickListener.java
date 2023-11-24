package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tk.airshipcraft.commonlib.gui.Hologram;

import static tk.airshipcraft.commonlib.gui.Hologram.callClickAction;
import static tk.airshipcraft.commonlib.gui.Hologram.hologramInstances;

/**
 * Listener class for handling HologramClickEvents.
 * This class listens for any HologramClickEvent fired within the game and processes them.
 * Specifically, it iterates through all instances of Holograms and invokes their respective click actions.
 *
 * The class demonstrates how to handle custom events within a Bukkit plugin context, especially those related to hologram interactions.
 *
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-11
 */
public class HologramClickListener implements Listener {

    /**
     * Handles the HologramClickEvent when it is fired.
     * Iterates through all registered Hologram instances and executes their defined click actions.
     * This method is invoked automatically by the Bukkit event system whenever a HologramClickEvent occurs.
     *
     * @param event The HologramClickEvent that was fired.
     */
    @EventHandler
    public void executeClickEvents(HologramClickEvent event) {
        for (Hologram subclassHologram : hologramInstances) {
            callClickAction(subclassHologram);
        }
    }
}
