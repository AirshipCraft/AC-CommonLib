package tk.airshipcraft.commonlib.configuration.impl;

import org.bukkit.entity.Player;

/**
 * Interface for player preference handling.
 * Classes that implement this interface are responsible for managing the loading,
 * saving, and resetting of player-specific preferences.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public interface IPlayerPreference {

    /**
     * Loads the player's preferences.
     * Implementations should retrieve and apply preference values from a persistent store.
     *
     * @param player The player whose preferences are to be loaded.
     */
    void load(Player player);

    /**
     * Saves the player's preferences.
     * Implementations should persist the current state of preferences to a store.
     *
     * @param player The player whose preferences are to be saved.
     */
    void save(Player player);

    /**
     * Resets the player's preferences to default values.
     * Implementations should revert preferences back to a default state.
     *
     * @param player The player whose preferences are to be reset.
     */
    void reset(Player player);

    /**
     * Gets a summary or detailed description of the player's preferences.
     * This can be used for informative purposes or for debugging.
     *
     * @param player The player whose preference summary is to be retrieved.
     * @return A string representation of the player's preferences.
     */
    String describe(Player player);
}
