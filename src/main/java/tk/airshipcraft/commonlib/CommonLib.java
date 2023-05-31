package tk.airshipcraft.commonlib;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.ACRPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class file, plugins should extend this.
 */
public class CommonLib extends JavaPlugin {
    public static CommonLib mainInstance;
    @Override
    public final void onEnable() {
        mainInstance = this;
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new HologramClickListener(), this);
        ACRPlugin.enableSubclasses();
    }


    @Override
    public final void onDisable() {
        ACRPlugin.disableSubclasses();
    }

}


