package tk.airshipcraft.commonlib.configuration;

import org.bukkit.Color;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import tk.airshipcraft.commonlib.CommonLib;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages the configuration for plugins that extend CommonLib.
 * This class handles loading, saving, and repairing configuration settings.
 * It uses reflection to automatically initialize configuration values based on annotated fields in a given class.
 * Additionally, it maintains a default configuration file to ensure robustness in configuration management.
 *
 * @author notzune
 * @version 2.0.0
 * @since 2024-1-4
 */
public class ConfigHelper {

    private final CommonLib plugin;
    private final Class<?> configClass;
    private final FileConfiguration defaultConfig;
    private final File configFile;
    private final FileConfiguration config;
    private final Map<String, Field> configFields;

    /**
     * Constructs a ConfigHelper for the given plugin and configuration class.
     * This will initialize the default configuration by either creating it or loading it if it already exists.
     *
     * @param plugin      The plugin instance. This is used to access plugin-specific configuration and data.
     * @param configClass The class containing configuration fields. Fields in this class should be annotated with {@link ConfigOption}.
     */
    public ConfigHelper(CommonLib plugin, Class<?> configClass) {
        this.plugin = plugin;
        this.configClass = configClass;
        this.defaultConfig = createOrLoadDefaultConfig();
        this.configFile = new File(plugin.getDataFolder(), "config.yml");
        this.config = YamlConfiguration.loadConfiguration(configFile);
        this.configFields = new HashMap<>();
        cacheConfigFields();
    }

    private void cacheConfigFields() {
        for (Field field : configClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(ConfigOption.class)) {
                ConfigOption option = field.getAnnotation(ConfigOption.class);
                configFields.put(option.key(), field);
            }
        }
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
        FileConfiguration defaultConfig = new YamlConfiguration();

        if (!defaultConfigFile.exists()) {
            populateDefaultConfig(defaultConfig);
            try {
                defaultConfig.save(defaultConfigFile);
            } catch (IOException e) {
                plugin.getLogger().severe("Could not save default configuration: " + e.getMessage());
            }
        } else {
            try {
                defaultConfig.load(defaultConfigFile);
            } catch (IOException | InvalidConfigurationException e) {
                plugin.getLogger().severe("Could not load default configuration: " + e.getMessage());
            }
        }
        return defaultConfig;
    }

    /**
     * Saves the current configuration to the config.yml file.
     * This method iterates through all fields in the provided configClass
     * and saves the corresponding values to the config.yml file.
     */
    public void saveConfig() {
        try {
            for (Map.Entry<String, Field> entry : configFields.entrySet()) {
                Field field = entry.getValue();
                field.setAccessible(true);
                config.set(entry.getKey(), field.get(null));
            }
            config.save(configFile);
        } catch (IllegalAccessException | IOException e) {
            plugin.getLogger().severe("Error saving configuration: " + e.getMessage());
        }
    }

    /**
     * Loads the configuration from the config.yml file.
     * This method iterates through all fields in the provided configClass
     * and loads the corresponding values from the config.yml file.
     */
    public void loadConfig() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs(); // Ensure the directory exists
                configFile.createNewFile();
                // Apply default values
                applyDefaultValues();
            } else {
                for (Map.Entry<String, Field> entry : configFields.entrySet()) {
                    Field field = entry.getValue();
                    if (config.contains(entry.getKey())) {
                        field.setAccessible(true);
                        field.set(null, config.get(entry.getKey()));
                    }
                }
            }
        } catch (IllegalAccessException | IOException e) {
            plugin.getLogger().severe("Error loading configuration: " + e.getMessage());
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
     * Applies default values to the configuration file.
     * This method iterates through all fields in the provided configClass
     * and sets default values in the provided FileConfiguration object.
     */
    private void applyDefaultValues() {
        for (Map.Entry<String, Field> entry : configFields.entrySet()) {
            ConfigOption option = entry.getValue().getAnnotation(ConfigOption.class);
            config.set(entry.getKey(), option.defaultValue());
        }
        saveConfig();
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

    /**
     * Gets the value of a configuration option as a String.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a String.
     */
    public String getString(String key) {
        return config.contains(key) ? config.getString(key) : defaultConfig.getString(key);
    }

    /**
     * Gets the value of a configuration option as an int.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as an int.
     */
    public int getInt(String key) {
        return config.contains(key) ? config.getInt(key) : defaultConfig.getInt(key);
    }

    /**
     * Gets the value of a configuration option as a short.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a short.
     */
    public boolean getBoolean(String key) {
        return config.contains(key) ? config.getBoolean(key) : defaultConfig.getBoolean(key);
    }

    /**
     * Gets the value of a configuration option as a byte.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a byte.
     */
    public long getLong(String key) {
        return config.contains(key) ? config.getLong(key) : defaultConfig.getLong(key);
    }

    /**
     * Gets the value of a configuration option as a double.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a double.
     */
    public double getDouble(String key) {
        return config.contains(key) ? config.getDouble(key) : defaultConfig.getDouble(key);
    }

    /**
     * Gets the value of a configuration option as a List of Floats.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Floats.
     */
    public List<Float> getFloatList(String key) {
        return config.contains(key) ? config.getFloatList(key) : defaultConfig.getFloatList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Doubles.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Doubles.
     */
    public List<Double> getDoubleList(String key) {
        return config.contains(key) ? config.getDoubleList(key) : defaultConfig.getDoubleList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Strings.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Strings.
     */
    public List<String> getStringList(String key) {
        return config.contains(key) ? config.getStringList(key) : defaultConfig.getStringList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Integers.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Integers.
     */
    public List<Integer> getIntegerList(String key) {
        return config.contains(key) ? config.getIntegerList(key) : defaultConfig.getIntegerList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Longs.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Longs.
     */
    public List<Long> getLongList(String key) {
        return config.contains(key) ? config.getLongList(key) : defaultConfig.getLongList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Bytes.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Bytes.
     */
    public List<Byte> getByteList(String key) {
        return config.contains(key) ? config.getByteList(key) : defaultConfig.getByteList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Characters.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Characters.
     */
    public List<Character> getCharacterList(String key) {
        return config.contains(key) ? config.getCharacterList(key) : defaultConfig.getCharacterList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Booleans.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Booleans.
     */
    public List<Boolean> getBooleanList(String key) {
        return config.contains(key) ? config.getBooleanList(key) : defaultConfig.getBooleanList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Shorts.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Shorts.
     */
    public List<Short> getShortList(String key) {
        return config.contains(key) ? config.getShortList(key) : defaultConfig.getShortList(key);
    }

    /**
     * Gets the value of a configuration option as a List of Maps.
     * If the option is not present in the config.yml file, the default value is returned.
     * Each map in the list represents a section of the configuration file.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a List of Maps.
     */
    public List<Map<?, ?>> getMapList(String key) {
        return config.contains(key) ? config.getMapList(key) : defaultConfig.getMapList(key);
    }

    /**
     * Gets the value of an ItemStack configuration option.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as an ItemStack.
     */
    public ItemStack getItemStack(String key) {
        return config.contains(key) ? config.getItemStack(key) : defaultConfig.getItemStack(key);
    }

    /**
     * Gets the value of a Color configuration option.
     * If the option is not present in the config.yml file, the default value is returned.
     *
     * @param key The key of the configuration option.
     * @return The value of the configuration option as a Color.
     */
    public Color getColor(String key) {
        return config.contains(key) ? config.getColor(key) : defaultConfig.getColor(key);
    }
}
