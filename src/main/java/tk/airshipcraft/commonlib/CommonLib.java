package tk.airshipcraft.commonlib;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class file, plugins should extend this.
 */
public abstract class CommonLib extends JavaPlugin {
    private static final List<CommonLib> registeredPlugins = new ArrayList<>();
    public static CommonLib mainInstance;

    public static void registerPlugin(CommonLib plugin) {
        registeredPlugins.add(plugin);
    }

    public static void unregisterPlugin(CommonLib plugin) {
        registeredPlugins.remove(plugin);
    }

    @Override
    public final void onEnable() {
        mainInstance = this;
        // Register this plugin
        registerPlugin(this);

        // Call onPluginEnable() for all registered plugins
        for (CommonLib plugin : registeredPlugins) {
            plugin.onPluginEnable();
        }
    }

    @Override
    public final void onDisable() {
        // Call onPluginDisable() for all registered plugins
        for (CommonLib plugin : registeredPlugins) {
            plugin.onPluginDisable();
        }

        // Unregister this plugin
        unregisterPlugin(this);
    }

    /**
     * Called when a plugin extending CommonLib is enabled.
     */
    public abstract void onPluginEnable();

    /**
     * Called when a plugin extending CommonLib is disabled.
     */
    public abstract void onPluginDisable();
}
