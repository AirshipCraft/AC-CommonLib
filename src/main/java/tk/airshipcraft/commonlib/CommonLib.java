package tk.airshipcraft.commonlib;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.Events.GuiClickEvent;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.ACRPlugin;
import tk.airshipcraft.commonlib.utils.UiDesigner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

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

