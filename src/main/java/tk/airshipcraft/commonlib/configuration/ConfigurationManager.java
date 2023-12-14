package tk.airshipcraft.commonlib.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.ACRPlugin;
import tk.airshipcraft.commonlib.CommonLib;
import java.lang.reflect.Field;

/**
 * Manages the configuration for plugins that extend CommonLib.
 * This class handles loading, saving, and repairing configuration settings.
 * It uses reflection to automatically initialize configuration values based on annotated fields in a given class.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-13
 */
public class ConfigurationManager {

    private ACRPlugin plugin;
    private Class<?> configClass;

    /**
     * Constructs a ConfigurationManager for the given plugin and configuration class.
     *
     * @param plugin      The plugin instance.
     * @param configClass The class containing configuration fields.
     */
    public ConfigurationManager(ACRPlugin plugin, Class<?> configClass) {
        this.plugin = plugin;
        this.configClass = configClass;
    }

    /**
     * Repairs the configuration file on plugin restart.
     * This method ensures that the config file is correctly formatted and contains all necessary values.
     * Existing values are not overwritten unless they are malformed or missing.
     */
    public void repairConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();
        boolean configUpdated = false;

        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigOption.class)) {
                ConfigOption option = field.getAnnotation(ConfigOption.class);
                String key = option.key();
                Object defaultValue = option.defaultValue();

                if (!config.contains(key) || isMalformed(config, key)) {
                    config.set(key, defaultValue);
                    configUpdated = true;
                    CommonLib.getInstance().logInfo("Repaired missing or malformed field: " + key);
                }
            }
        }

        if (configUpdated) {
            plugin.saveConfig();
            CommonLib.getInstance().logInfo("Configuration repaired and saved.");
        } else {
            CommonLib.getInstance().logInfo("No repair needed for configuration.");
        }
    }

    /**
     * Checks if the configuration value for a given key is malformed.
     * This method can be customized based on what counts as a malformed value.
     *
     * @param config The configuration to check.
     * @param key    The key of the configuration option.
     * @return True if the value is malformed, false otherwise.
     */
    private boolean isMalformed(FileConfiguration config, String key) {
        // Implement logic to determine if the value is malformed.
        // This could be based on expected type, format, or range.
        // Example: return config.getString(key, "").isEmpty();
        // For now, let's just return false, meaning no value is considered malformed.
        return false;
    }

    // Additional methods and logic can be implemented as needed
}
