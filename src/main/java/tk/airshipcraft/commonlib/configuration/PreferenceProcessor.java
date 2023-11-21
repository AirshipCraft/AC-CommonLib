package tk.airshipcraft.commonlib.configuration;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;
import tk.airshipcraft.commonlib.configuration.impl.PlayerPref;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Processes player preferences by using reflection to load and save annotated fields.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class PreferenceProcessor {

    /**
     * Loads player preferences from the data store into the annotated fields.
     *
     * @param player      The player whose preferences should be loaded.
     * @param preference  The preference object containing the fields to be loaded.
     * @param config      The configuration file from which to load the preferences.
     */
    public static void load(Player player, IPlayerPreference preference, FileConfiguration config) {
        UUID playerUuid = player.getUniqueId();
        Class<?> clazz = preference.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlayerPref.class)) {
                try {
                    field.setAccessible(true);
                    Object value = config.get(playerUuid + "." + field.getName());
                    field.set(preference, value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }
            }
        }
    }

    /**
     * Saves player preferences from the annotated fields into the data store.
     *
     * @param player      The player whose preferences should be saved.
     * @param preference  The preference object containing the fields to be saved.
     * @param config      The configuration file to which to save the preferences.
     */
    public static void save(Player player, IPlayerPreference preference, FileConfiguration config) {
        UUID playerUuid = player.getUniqueId();
        Class<?> clazz = preference.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlayerPref.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(preference);
                    config.set(playerUuid + "." + field.getName(), value);
                } catch (IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }
            }
        }
    }

    /**
     * Loads player preferences from a MySQL database into the annotated fields.
     *
     * @param player      The player whose preferences should be loaded.
     * @param preference  The preference object containing the fields to be loaded.
     * @param connection  The database connection.
     */
    public static void loadFromDatabase(Player player, IPlayerPreference preference, Connection connection) {
        UUID playerUuid = player.getUniqueId();
        Class<?> clazz = preference.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlayerPref.class)) {
                try {
                    field.setAccessible(true);
                    String query = "SELECT value FROM player_preferences WHERE player_uuid = ? AND key = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, playerUuid.toString());
                        stmt.setString(2, field.getName());
                        ResultSet rs = stmt.executeQuery();
                        if (rs.next()) {
                            // Assuming all values are stored as strings for simplicity
                            String value = rs.getString("value");
                            // Convert the string to the appropriate field type as needed
                            field.set(preference, convertStringToFieldType(value, field.getType()));
                        }
                    }
                } catch (SQLException | IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }
            }
        }
    }

    /**
     * Saves player preferences from the annotated fields into a MySQL database.
     *
     * @param player      The player whose preferences should be saved.
     * @param preference  The preference object containing the fields to be saved.
     * @param connection  The database connection.
     */
    public static void saveToDatabase(Player player, IPlayerPreference preference, Connection connection) {
        UUID playerUuid = player.getUniqueId();
        Class<?> clazz = preference.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(PlayerPref.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(preference);
                    String query = "INSERT INTO player_preferences (player_uuid, key, value) VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE value = ?";
                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, playerUuid.toString());
                        stmt.setString(2, field.getName());
                        stmt.setString(3, value.toString());
                        stmt.setString(4, value.toString());
                        stmt.executeUpdate();
                    }
                } catch (SQLException | IllegalAccessException e) {
                    e.printStackTrace(); // Handle exceptions appropriately
                }
            }
        }
    }

    /**
     * Converts a string value to the specified field type.
     * This is a utility method used in loading preferences from the database.
     *
     * @param value The string value to convert.
     * @param type  The type to convert the value to.
     * @return The converted object.
     */
    public static Object convertStringToFieldType(String value, Class<?> type) {
        // Conversion logic goes here (e.g., convert to int, boolean, etc.)
        // This is a simplified example; you may need to handle more types.
        if (type.equals(int.class) || type.equals(Integer.class)) {
            return Integer.parseInt(value);
        } else if (type.equals(boolean.class) || type.equals(Boolean.class)) {
            return Boolean.parseBoolean(value);
        } else {
            return value; // Default to string
        }
    }
}
