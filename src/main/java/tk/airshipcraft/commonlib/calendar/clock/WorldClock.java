package tk.airshipcraft.commonlib.calendar.clock;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import tk.airshipcraft.commonlib.ACRPlugin;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.calendar.impl.CalendarManager;
import tk.airshipcraft.commonlib.calendar.impl.EventManager;
import tk.airshipcraft.commonlib.configuration.ConfigHelper;
import tk.airshipcraft.commonlib.configuration.ConfigOption;

import java.io.File;
import java.io.IOException;

public class WorldClock {

    @ConfigOption(key = "realSecondsPerMinecraftDay", defaultValue = "3600")
    private long realSecondsPerMinecraftDay; // Configurable via annotation
    private long lastUpdateTick; // Internal state for tracking time
    private double tickRateModifier; // Modifier to adjust the in-game tick rate
    private CalendarManager calendarManager;
    private EventManager eventManager;
    private CommonLib plugin; // Reference to your plugin
    private ConfigHelper configHelper;
    private File clockStateFile;
    private FileConfiguration clockStateConfig;

    public WorldClock(CommonLib plugin, CalendarManager calendarManager, EventManager eventManager) {
        this.plugin = plugin;
        this.calendarManager = calendarManager;
        this.eventManager = eventManager;

        // Initialize the ConfigHelper for the configurable options
        configHelper = new ConfigHelper(plugin, this.getClass());
        configHelper.loadConfig();

        // Calculate the modifier based on the loaded config
        updateTickRateModifier();

        // Initialize the file and configuration for saving the state
        clockStateFile = new File(plugin.getDataFolder(), "worldclock.yml");
        clockStateConfig = YamlConfiguration.loadConfiguration(clockStateFile);

        loadState(); // Load the state from the file
    }

    private void updateTickRateModifier() {
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

                    // Check if a Minecraft day has passed
                    if (time - lastUpdateTick >= 24000) {
                        calendarManager.newMinecraftDay(); // Advance the calendar by one day
                        eventManager.triggerEvents(calendarManager.getCurrentDate());
                        lastUpdateTick = time;
                        saveState(); // Save the internal state whenever a new day starts
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    public void saveState() {
        clockStateConfig.set("lastUpdateTick", lastUpdateTick);
        try {
            clockStateConfig.save(clockStateFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save WorldClock state: " + e.getMessage());
        }
    }

    public void loadState() {
        if (clockStateFile.exists()) {
            lastUpdateTick = clockStateConfig.getLong("lastUpdateTick", 0);
        } else {
            lastUpdateTick = 0;
        }
    }
}
