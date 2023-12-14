package tk.airshipcraft.commonlib.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import tk.airshipcraft.commonlib.ACRPlugin;
import tk.airshipcraft.commonlib.CommonLib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Manages the configuration for plugins that extend CommonLib.
 * This class handles loading, saving, and repairing configuration settings.
 * It uses reflection to automatically initialize configuration values based on annotated fields in a given class.
 * Additionally, it maintains a default configuration file to ensure robustness in configuration management.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-12-13
 */
public class ConfigurationManager {

    private ACRPlugin plugin;
    private Class<?> configClass;
    private FileConfiguration defaultConfig; // Cached default configuration

    /**
     * Constructs a ConfigurationManager for the given plugin and configuration class.
     * This will initialize the default configuration by either creating it or loading it if it already exists.
     *
     * @param plugin      The plugin instance. This is used to access plugin-specific configuration and data.
     * @param configClass The class containing configuration fields. Fields in this class should be annotated with {@link ConfigOption}.
     */
    public ConfigurationManager(ACRPlugin plugin, Class<?> configClass) {
        this.plugin = plugin;
        this.configClass = configClass;
        this.defaultConfig = createOrLoadDefaultConfig();
    }

    /**
     * Creates or loads the default configuration file.
     * If the file doesn't exist, it's created using the default values defined in the configuration class.
     * If it exists, the existing file is loaded.
     *
     * @return The default FileConfiguration object.
     */
    private FileConfiguration createOrLoadDefaultConfig() {
        File defaultConfigFile = new File(plugin.getDataFolder(), "default-config.yml");
        if (!defaultConfigFile.exists()) {
            FileConfiguration defaultConfig = new YamlConfiguration();
            populateDefaultConfig(defaultConfig);
            try {
                defaultConfig.save(defaultConfigFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save default configuration: " + e.getMessage());
            }
            return defaultConfig;
        } else {
            try {
                FileConfiguration loadedConfig = new YamlConfiguration();
                loadedConfig.load(defaultConfigFile);
                return loadedConfig;
            } catch (IOException | InvalidConfigurationException e) {
                plugin.getLogger().severe("Could not load default configuration: " + e.getMessage());
                return new YamlConfiguration(); // Return empty config to avoid nulls
            }
        }
    }

    /**
     * Populates a FileConfiguration object with default values.
     * This method iterates through all fields in the provided configClass
     * and sets default values in the provided FileConfiguration object.
     *
     * @param config The FileConfiguration object to populate.
     */
    private void populateDefaultConfig(FileConfiguration config) {
        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigOption.class)) {
                ConfigOption option = field.getAnnotation(ConfigOption.class);
                config.set(option.key(), option.defaultValue());
            }
        }
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
     * The current implementation checks if the value is null or if its type doesn't match the default type.
     *
     * @param config The configuration to check.
     * @param key    The key of the configuration option.
     * @return True if the value is malformed, false otherwise.
     */
    private boolean isMalformed(FileConfiguration config, String key) {
        Object currentValue = config.get(key);
        Object defaultValue = defaultConfig.get(key);

        return currentValue == null || !currentValue.getClass().equals(defaultValue.getClass());
    }

    // Additional methods and logic can be implemented as needed
}
