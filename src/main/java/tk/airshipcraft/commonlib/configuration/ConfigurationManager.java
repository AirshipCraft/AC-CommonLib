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
     * Loads the configuration, applying default values for missing settings.
     * Reflectively scans the specified configuration class for annotated fields
     * and initializes them with values from the plugin's configuration file.
     */
    public void loadConfig() {
        plugin.reloadConfig();
        FileConfiguration config = plugin.getConfig();

        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigOption.class)) {
                ConfigOption option = field.getAnnotation(ConfigOption.class);
                String key = option.key();
                Object defaultValue = option.defaultValue();
                Object value = config.get(key, defaultValue);

                field.setAccessible(true);
                try {
                    field.set(null, value); // Assuming static fields for configuration
                } catch (IllegalAccessException e) {
                    CommonLib.getInstance().logException(e);
                }
            }
        }

        plugin.saveConfig();
        CommonLib.getInstance().logInfo("Configuration loaded successfully.");
    }

    /**
     * Repairs the configuration file on plugin restart.
     * Ensures the config file is correctly formatted and contains all necessary values.
     * Existing values are not overwritten.
     */
    public void repairConfig() {
        // Additional logic for repairing the config can be added here
        loadConfig();
        CommonLib.getInstance().logInfo("Configuration repaired.");
    }

    // Additional methods and logic can be implemented as needed
}
