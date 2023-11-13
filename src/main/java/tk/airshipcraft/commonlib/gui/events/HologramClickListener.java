package tk.airshipcraft.commonlib.gui.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import tk.airshipcraft.commonlib.utils.Hologram;

import static tk.airshipcraft.commonlib.utils.Hologram.callClickAction;
import static tk.airshipcraft.commonlib.utils.Hologram.hologramInstances;

public class HologramClickListener implements Listener {
    @EventHandler
    public void executeClickEvents(HologramClickEvent event) {
        for (Hologram subclassHologram : hologramInstances) {
            callClickAction(subclassHologram);
        }
    }
}