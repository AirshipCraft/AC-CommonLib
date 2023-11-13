package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import tk.airshipcraft.commonlib.gui.objects.Ui;
import tk.airshipcraft.commonlib.utils.UiDesigner;

/**
 * @author Locutusque
 * @version 1.0.0
 * @since 2023-04-11
 */
public class InventoryClickListener implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (Ui.isUi(event.getClickedInventory())) {
            Bukkit.getServer().getPluginManager().callEvent(new GuiClickEvent((Player) event.getWhoClicked(), event.getSlot(), event.getCurrentItem(), event.getClickedInventory()));
            UiDesigner.callClickAction(event.getInventory(), event.getSlot());
        }
    }
}
