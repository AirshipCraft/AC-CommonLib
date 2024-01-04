package tk.airshipcraft.commonlib.calendar.clock;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.calendar.impl.CalendarManager;
import tk.airshipcraft.commonlib.calendar.impl.EventManager;
import tk.airshipcraft.commonlib.configuration.ConfigHelper;
import tk.airshipcraft.commonlib.configuration.ConfigOption;

import java.io.File;
import java.io.IOException;

/**
 * This class handles converting real-world time to in-game time and vice versa.
 * It also handles the progression of in-game time and modifies the passage of in-game time to be slower
 * so that one hour of real-world time is equivalent to one Minecraft day.
 *
 * @author notzune
 * @version 1.5.0
 * @since 2024-1-4
 */
public class WorldClock {

    @ConfigOption(key = "realSecondsPerMinecraftDay", defaultValue = "3600")
    private static long realSecondsPerMinecraftDay; // Configurable via annotation
    private long lastUpdateTick; // Internal state for tracking time
    private double tickRateModifier; // Modifier to adjust the in-game tick rate
    private CalendarManager calendarManager;
    private EventManager eventManager;
    private CommonLib plugin; // Reference to your plugin
    private ConfigHelper configHelper;
    private File clockStateFile;
    private FileConfiguration clockStateConfig;

    /**
     * Initializes a new WorldClock.
     *
     * @param plugin            The plugin instance.
     * @param calendarManager   The CalendarManager instance.
     * @param eventManager      The EventManager instance.
     */
    public WorldClock(CommonLib plugin, CalendarManager calendarManager, EventManager eventManager) {
        this.plugin = plugin;
        this.calendarManager = calendarManager;
        this.eventManager = eventManager;

        // Initialize the ConfigHelper for the configurable options
        configHelper = new ConfigHelper(plugin, this.getClass());
        configHelper.loadConfig();

        this.realSecondsPerMinecraftDay  = configHelper.getLong("realSecondsPerMinecraftDay");

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

    /**
     * Starts the clock.
     */
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
                        eventManager.triggerEvents(calendarManager.getCurrentDate().toLocalDate());
                        eventManager.triggerCustomEvents(calendarManager.getCurrentDate());
                        lastUpdateTick = time;
                        saveState(); // Save the internal state whenever a new day starts
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 1L);
    }

    /**
     * Saves the state to the file.
     */
    public void saveState() {
        clockStateConfig.set("lastUpdateTick", lastUpdateTick);
        try {
            clockStateConfig.save(clockStateFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Could not save WorldClock state: " + e.getMessage());
        }
    }

    /**
     * Loads the state from the file.
     */
    public void loadState() {
        if (clockStateFile.exists()) {
            lastUpdateTick = clockStateConfig.getLong("lastUpdateTick", 0);
        } else {
            lastUpdateTick = 0;
        }
    }
}
