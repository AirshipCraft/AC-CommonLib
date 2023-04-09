package tk.airshipcraft.commonlib;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class file, plugins should extend this.
 */
public abstract class CommonLib extends JavaPlugin {
    private static final List<CommonLib> plugins = new ArrayList<>();
    public static CommonLib mainInstance;

    static {
        for (Plugin plugin : JavaPlugin.getProvidingPlugin(CommonLib.class).getServer().getPluginManager().getPlugins()) {
            if (plugin instanceof CommonLib) {
                plugins.add((CommonLib) plugin);
            }
        }
    }

    public static List<CommonLib> getPlugins() {
        return plugins;
    }

    @Override
    public final void onEnable() {
        mainInstance = this;
        onPluginEnable();

        for (CommonLib plugin : plugins) {
            if (plugin.isEnabled() && !plugin.equals(this)) {
                plugin.onPluginEnable();
            }
        }
    }

    @Override
    public final void onDisable() {
        onPluginDisable();

        for (CommonLib plugin : plugins) {
            if (plugin.isEnabled() && !plugin.equals(this)) {
                plugin.onPluginDisable();
            }
        }
    }

    public abstract void onPluginEnable();

    public abstract void onPluginDisable();
}

