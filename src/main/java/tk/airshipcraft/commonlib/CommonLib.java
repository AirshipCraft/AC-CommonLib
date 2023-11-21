package tk.airshipcraft.commonlib;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.configuration.PreferencesManager;
import tk.airshipcraft.commonlib.gui.events.HologramClickListener;
import tk.airshipcraft.commonlib.gui.events.InventoryClickListener;
import tk.airshipcraft.commonlib.gui.objects.TeamManager;

import java.util.logging.Level;

/**
 * Main class of the CommonLib library.
 * This class initializes the library and manages the lifecycle of the plugins that extend it.
 * It uses a Singleton pattern to ensure that only one instance of CommonLib exists.
 * It also provides enhanced logging capabilities.
 *
 * @author notzune, Locotusque
 * @since 2023-03-30
 * @version 1.0.0
 */
public class CommonLib extends JavaPlugin implements Listener {

    private static CommonLib instance;
    private boolean debugEnabled = false; // Flag to control debug logging
    private TeamManager teamManager = new TeamManager();
    private PreferencesManager preferencesManager = new PreferencesManager();

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
        setupLogging(); // Set up logging
        registerEvents();
        ACRPlugin.enableSubclasses();
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new InventoryClickListener(), this);
        pm.registerEvents(new HologramClickListener(), this);
        pm.registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        // Load preferences when player joins
        preferencesManager.loadPreferences(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        // Save preferences when player quits
        preferencesManager.savePreferences(event.getPlayer());
    }

    // Method to get PreferencesManager
    public PreferencesManager getPreferencesManager() {
        return preferencesManager;
    }

    /**
     * Returns a new instance of the teamManager.
     * @return TeamManager instance
     */
    public TeamManager getTeamManager() {
        return teamManager;
    }

    /**
     * Sets up the logging mechanism.
     * This method can be used to configure logging and read the debug setting.
     */
    private void setupLogging() {
        // Read the debug setting from a config file or set it programmatically
        // Uncomment the following line to enable debug logging:
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

    /**
     * Logs a warning message.
     *
     * @param message The warning message to log.
     */
    public void logWarning(String message) {
        getLogger().warning(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The error message to log.
     */
    public void logError(String message) {
        getLogger().severe(message);
    }

    /**
     * Logs a severe message, indicating a critical problem.
     *
     * @param message The severe message to log.
     */
    public void logSevere(String message) {
        getLogger().log(Level.SEVERE, message);
    }

    /**
     * Logs an exception stack trace at the SEVERE level.
     *
     * @param e The exception to log.
     */
    public void logException(Exception e) {
        getLogger().log(Level.SEVERE, "Exception occurred: ", e);
    }

    // Additional logging methods like logConfig, logFine, etc., can be added similarly.
    // todo: add them when im not lazy
}
