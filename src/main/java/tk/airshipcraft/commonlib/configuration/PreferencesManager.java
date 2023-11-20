package tk.airshipcraft.commonlib.configuration;

package tk.airshipcraft.commonlib.preferences;

import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Manages the preferences for all players.
 * This class handles the registration of player preferences and provides methods
 * to load, save, and reset preferences for individual players.
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

    // Additional utility methods as needed...
}
