package tk.airshipcraft.commonlib.configuration;

import org.bukkit.entity.Player;

/**
 * IPlayerPreference is an interface that defines the contract for managing player-specific preferences in a Minecraft server environment.
 * Implementations of this interface should handle the loading, saving, and resetting of preferences for individual players.
 * This interface is essential for ensuring consistent behavior across different implementations of player preference management.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public interface IPlayerPreference {

    /**
     * Loads the player's preferences from a persistent data store.
     * This method should be implemented to retrieve the stored preferences of a player and apply them.
     * It's typically called when a player joins the server or a reload of preferences is needed.
     *
     * @param player The player whose preferences are to be loaded.
     */
    void load(Player player);

    /**
     * Saves the current state of the player's preferences to a persistent data store.
     * Implementations should ensure that any changes made to the preferences are persisted.
     * This method is usually called when a player leaves the server or when preferences are changed.
     *
     * @param player The player whose preferences are to be saved.
     */
    void save(Player player);

    /**
     * Resets the player's preferences to their default values.
     * This method is useful for situations where a player's preferences need to be cleared or reverted.
     * Implementations should ensure that all preferences are set to their initial state.
     *
     * @param player The player whose preferences are to be reset.
     */
    void reset(Player player);

    /**
     * Provides a string representation of the player's current preferences.
     * This can be used to display the preferences to the player, for logging, or for administrative purposes.
     * The format and detail level of the description can vary depending on the implementation.
     *
     * @param player The player whose preference summary is to be retrieved.
     * @return A string describing the player's current preferences. The format of this string is implementation-specific.
     */
    String describe(Player player);
}
