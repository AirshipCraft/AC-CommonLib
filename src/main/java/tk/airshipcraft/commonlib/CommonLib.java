package tk.airshipcraft.commonlib;

import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.ACRPlugin;

/**
 * Main class of the CommonLib library.
 * This class initializes the library and manages the lifecycle of the plugins that extend it.
 * It uses a Singleton pattern to ensure that only one instance of CommonLib exists.
 */
public class CommonLib extends JavaPlugin {
    private static CommonLib instance;

    /**
     * Returns the single instance of CommonLib.
     *
     * @return The singleton instance of CommonLib.
     */
    public static CommonLib getInstance() {
        return instance;
    }

    /**
     * Initializes the library and registers event listeners.
     * This method is called by the Bukkit framework when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new HologramClickListener(), this);
        ACRPlugin.enableSubclasses();
    }

    /**
     * Cleans up resources and disables the plugins that extend this library.
     * This method is called by the Bukkit framework when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        ACRPlugin.disableSubclasses();
    }
}
