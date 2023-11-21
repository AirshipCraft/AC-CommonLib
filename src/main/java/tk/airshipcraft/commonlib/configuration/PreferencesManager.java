package tk.airshipcraft.commonlib.configuration;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;

import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the preferences for all players.
 * This class handles the registration of player preferences and provides methods
 * to load, save, and reset preferences for individual players.
 * <p>
 * This class is thread-safe and can be used to manage preferences for multiple players concurrently.
 * </p>
 *<p>
 * Example usage:
 * <pre>{@code
 * // To check if a player has preferences:
 * boolean hasPrefs = preferencesManager.hasPreferences(player);
 *
 * // To update a specific preference field:
 * preferencesManager.updatePreferenceField(player, "townName", "NewTown");
 *
 * // To save all preferences on server shutdown:
 * preferencesManager.saveAllPreferences();
 * }
 *</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class PreferencesManager {

    private final Map<UUID, IPlayerPreference> preferencesMap = new ConcurrentHashMap<>();

    /**
     * Registers a player's preference object.
     * If a preference object already exists for the player, it will be replaced.
     *
     * @param playerUuid The UUID of the player.
     * @param preference The player's preference object.
     */
    public void registerPreference(UUID playerUuid, IPlayerPreference preference) {
        preferencesMap.put(playerUuid, preference);
    }

    /**
     * Loads the preferences for a player.
     * This method should be called when a player logs in or when preferences need to be reloaded.
     *
     * @param player The player whose preferences should be loaded.
     */
    public void loadPreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.load(player);
        }
    }

    /**
     * Saves the preferences for a player.
     * This method should be called when a player logs out or when preferences need to be persisted.
     *
     * @param player The player whose preferences should be saved.
     */
    public void savePreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.save(player);
        }
    }

    /**
     * Resets the preferences for a player to their default values.
     *
     * @param player The player whose preferences should be reset.
     */
    public void resetPreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        if (preference != null) {
            preference.reset(player);
        }
    }

    /**
     * Describes the preferences for a player.
     *
     * @param player The player whose preferences should be described.
     * @return A string description of the player's preferences.
     */
    public String describePreferences(Player player) {
        IPlayerPreference preference = preferencesMap.get(player.getUniqueId());
        return (preference != null) ? preference.describe(player) : "No preferences registered.";
    }

    /**
     * Retrieves all registered preferences.
     * This can be useful for debugging or administrative purposes.
     *
     * @return An unmodifiable set of all registered player preferences.
     */
    public Set<IPlayerPreference> getAllPreferences() {
        return Collections.unmodifiableSet(new HashSet<>(preferencesMap.values()));
    }

    /**
     * Checks if a player has preferences registered.
     *
     * @param player The player to check for registered preferences.
     * @return True if the player has preferences registered, false otherwise.
     */
    public boolean hasPreferences(Player player) {
        return preferencesMap.containsKey(player.getUniqueId());
    }

    /**
     * Retrieves the preference object for a given player.
     * This method can be used to access and manipulate a player's preferences directly.
     *
     * @param player The player whose preference object is to be retrieved.
     * @return The IPlayerPreference object associated with the player, or null if none is registered.
     */
    public IPlayerPreference getPreference(Player player) {
        return preferencesMap.get(player.getUniqueId());
    }

    /**
     * Updates a specific preference field for a player.
     * This method can use reflection to update fields annotated with {@code @PlayerPref}.
     *
     * @param player       The player whose preference is to be updated.
     * @param preferenceKey The key identifying the preference field to update.
     * @param newValue     The new value for the preference field.
     */
    public void updatePreferenceField(Player player, String preferenceKey, Object newValue) {
        IPlayerPreference preferences = preferencesMap.get(player.getUniqueId());
        if (preferences != null) {
            // Reflection logic to find the field with the matching preferenceKey and update it
        }
    }

    /**
     * Saves all player preferences.
     * This method should be called on server shutdown to ensure all data is persisted.
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
