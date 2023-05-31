package tk.airshipcraft.commonlib;

import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.ACRPlugin;

/**
 * Main class file, plugins should extend this.
 */
public class CommonLib extends JavaPlugin {
    public static CommonLib mainInstance;
    private ACRPlugin acr;
    @Override
    public final void onEnable() {
        mainInstance = this;
        this.acr = new ACRPlugin() {
            @Override
            public void onPluginEnable() {
            }

            @Override
            public void onPluginDisable() {
            }
        };
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new HologramClickListener(), this);
        acr.enableSubclasses();
    }


    @Override
    public final void onDisable() {
        acr.disableSubclasses();
    }
}

