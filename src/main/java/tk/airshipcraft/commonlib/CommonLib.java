package tk.airshipcraft.commonlib;

import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class file, plugins should extend this.
 */
public final class CommonLib extends JavaPlugin {
    public static CommonLib mainInstance;

    @Override
    public void onEnable() {
        mainInstance = this;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
