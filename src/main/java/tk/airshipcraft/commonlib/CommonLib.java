package tk.airshipcraft.commonlib;

import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.Events.HologramClickListener;
import tk.airshipcraft.commonlib.Events.InventoryClickListener;
import tk.airshipcraft.commonlib.utils.ACRPlugin;

import java.util.logging.Level;

/**
 * Main class of the CommonLib library.
 * This class initializes the library and manages the lifecycle of the plugins that extend it.
 * It uses a Singleton pattern to ensure that only one instance of CommonLib exists.
 * It also provides logging capabilities.
 */
public class CommonLib extends JavaPlugin {

    private static CommonLib instance;
    private boolean debugEnabled = false; // Add a flag to control debug logging

    /**
     * Returns the single instance of CommonLib.
     *
     * @return The singleton instance of CommonLib.
     */
    public static CommonLib getInstance() {
        return instance;
    }

    /**
     * Initializes the library, sets up logging, and registers event listeners.
     * This method is called by the Bukkit framework when the plugin is enabled.
     */
    @Override
    public void onEnable() {
        instance = this;
        setupLogging(); // Setup logging
        getServer().getPluginManager().registerEvents(new InventoryClickListener(), this);
        getServer().getPluginManager().registerEvents(new HologramClickListener(), this);
        ACRPlugin.enableSubclasses();
    }

    /**
     * Sets up the logging mechanism.
     * This method can be used to configure logging and read the debug setting.
     */
    private void setupLogging() {
        // You can read the debug setting from a config file or set it programmatically
        // For example, you can uncomment the following line to enable debug logging:
        // debugEnabled = true;

        logInfo("CommonLib is enabled");
        if (debugEnabled) {
            logDebug("Debug mode is enabled");
        }
    }

    /**
     * Cleans up resources and disables the plugins that extend this library.
     * This method is called by the Bukkit framework when the plugin is disabled.
     */
    @Override
    public void onDisable() {
        logInfo("CommonLib is being disabled");
        ACRPlugin.disableSubclasses();
    }

    /**
     * Logs an informational message.
     *
     * @param message The message to log.
     */
    public void logInfo(String message) {
        getLogger().info(message);
    }

    /**
     * Logs a debug message. Debug messages are only logged if debug mode is enabled.
     *
     * @param message The debug message to log.
     */
    public void logDebug(String message) {
        if (debugEnabled) {
            getLogger().log(Level.FINE, message);
        }
    }

    // Additional logging methods like logWarning, logError, etc., can be added similarly.
}
