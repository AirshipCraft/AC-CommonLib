package tk.airshipcraft.commonlib.configuration.impl;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.IPlayerPreference;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>Manages the preferences of all players on a Minecraft server.</p>
 * <p>This class acts as a central point for registering, loading, saving, and resetting player preferences.</p>
 * <p>It uses a thread-safe map to handle concurrent operations, ensuring that player preference data is managed safely in a multi-threaded environment.</p>
 *
 * <p>Example implementation would be checking if a player has preferences, updating specific preference fields, and saving all preferences during server shutdown.</p>
 * <p>Example usage:</p>
 * <pre>{@code
 * // To check if a player has preferences:
 * boolean hasPrefs = preferencesManager.hasPreferences(player);
 *
 * // To update a specific preference field:
 * preferencesManager.updatePreferenceField(player, "townName", "NewTown");
 *
 * // To save all preferences on server shutdown:
 * preferencesManager.saveAllPreferences();
 * }</pre>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class PreferencesManager {
    private final Map<UUID, IPlayerPreference> preferencesMap = new ConcurrentHashMap<>();

    /**
     * Registers or updates the preference object for a specific player.
     * If a preference object already exists for the player, it will be replaced with the new one.
     *
     * @param playerUuid The UUID of the player.
     * @param preference The player's preference object to be registered or updated.
     */
    public void registerPreference(UUID playerUuid, IPlayerPreference preference) {
        preferencesMap.put(playerUuid, preference);
    }

    /**
     * Loads the preferences for a specific player.
     * Should be called when a player logs in or when preferences need to be refreshed.
     *
     * @param player The player whose preferences are to be loaded.
     */
    public void loadPreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.load(player);
        }
    }

    /**
     * Saves the preferences for a specific player.
     * Should be called when a player logs out or when preferences need to be persisted.
     *
     * @param player The player whose preferences are to be saved.
     */
    public void savePreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.save(player);
        }
    }

    /**
     * Resets the preferences for a specific player to their default values.
     * This can be useful when preferences need to be reverted to a known state.
     *
     * @param player The player whose preferences are to be reset.
     */
    public void resetPreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.reset(player);
        }
    }

    /**
     * Provides a descriptive string of a player's preferences.
     * Useful for displaying preference information to the player or for administrative purposes.
     *
     * @param player The player whose preferences are to be described.
     * @return A string description of the player's preferences, or a message indicating no preferences are registered.
     */
    public String describePreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        return (preference != null) ? preference.describe(player) : "No preferences registered.";
    }

    /**
     * Retrieves a set of all registered player preferences.
     * This can be useful for administrative or debugging purposes.
     *
     * @return An unmodifiable set containing all registered player preference objects.
     */
    public Set<IPlayerPreference> getAllPreferences() {
        return Collections.unmodifiableSet(new HashSet<>(preferencesMap.values()));
    }

    /**
     * Checks whether a specific player has preferences registered.
     *
     * @param player The player to check.
     * @return True if the player has preferences registered, false otherwise.
     */
    public boolean hasPreferences(Player player) {
        return preferencesMap.containsKey(player.getUniqueId());
    }

    /**
     * Retrieves the preference object associated with a specific player.
     * This allows direct access to and manipulation of the player's preferences.
     *
     * @param player The player whose preference object is to be retrieved.
     * @return The IPlayerPreference object for the player, or null if none is registered.
     */
    public IPlayerPreference getPreference(Player player) {
        return preferencesMap.get(player.getUniqueId());
    }

    /**
     * Updates a specific field within a player's preferences.
     * This method can utilize reflection to identify and update fields annotated with {@code @PlayerPref}.
     *
     * @param player        The player whose preference is to be updated.
     * @param preferenceKey The key identifying the preference field to update.
     * @param newValue      The new value to assign to the preference field.
     */
    public void updatePreferenceField(Player player, String preferenceKey, Object newValue) {
        IPlayerPreference preferences = preferencesMap.get(player.getUniqueId());
        if (preferences != null) {
            // Reflection logic to find the field with the matching preferenceKey and update it
        }
    }

    /**
     * Saves all registered player preferences.
     * This method should be invoked during server shutdown to ensure all preferences are persisted.
     */
    public void saveAllPreferences() {
        preferencesMap.forEach((uuid, preference) -> {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null && player.isOnline()) {
                preference.save(player);
            }
        });
    }
}
