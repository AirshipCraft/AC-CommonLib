package tk.airshipcraft.commonlib.calendar.cycle;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.calendar.impl.CalendarManager;

/**
 * This class handles converting real-world time to in-game time and vice versa.
 * It also handles the progression of in-game time and modifies the passage of in-game time to be slower
 * so that one hour of real-world time is equivalent to one Minecraft day.
 */
public class WorldClock {

    private long realSecondsPerMinecraftDay; // Configurable via file
    private final double tickRateModifier; // Modifier to adjust the in-game tick rate
    private CalendarManager calendarManager;
    private CommonLib plugin; // Reference to your plugin

    public WorldClock(CommonLib plugin, CalendarManager calendarManager) {
        this.plugin = plugin;
        this.calendarManager = calendarManager;
        loadConfig();

        // Calculate the modifier based on the loaded config
        tickRateModifier = 24000.0 / (20 * 60) / (realSecondsPerMinecraftDay / 20.0);
    }

    public void start() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (World world : Bukkit.getServer().getWorlds()) {
                    long time = world.getTime();
                    time += tickRateModifier; // Adjust time based on the modifier
                    world.setTime(time);
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    private void loadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        // Default to 1 hour if not specified in the config
        realSecondsPerMinecraftDay = config.getLong("realSecondsPerMinecraftDay", 3600);
    }

    public void saveState() {
        // Logic to save the current state for persistence
    }

    public void loadState() {
        // Logic to load the state
    }
}
