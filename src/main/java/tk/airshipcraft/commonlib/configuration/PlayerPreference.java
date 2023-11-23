package tk.airshipcraft.commonlib.configuration;

import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * <p>Provides a skeletal implementation of the IPlayerPreference interface to facilitate the creation of player preference classes.
 * This abstract class is designed to minimize the effort required in implementing the IPlayerPreference interface by providing
 * basic implementations and structure for handling player preferences.</p>
 *
 * <p>Subclasses are expected to provide concrete implementations of the load, save, and describePart methods. The reset method
 * can be overridden if custom reset behavior is desired. Each subclass can represent a different set of preferences for players,
 * allowing for modular and flexible preference management within the plugin.</p>
 *
 * <p>Example Usage:
 * <pre>{@code
 * public class MyPluginPlayerPreferences extends PlayerPreference {
 *     @PlayerPref(defaultValue = "defaultTown")
 *     private String townName;
 *     @PlayerPref(defaultValue = "defaultNation")
 *     private String nationName;
 *
 *     // Constructor, getters and setters, and other methods...
 *
 *     @Override
 *     public void load(Player player) {
 *         // Logic to load preferences (potentially using reflection to read annotated fields)
 *     }
 *
 *     @Override
 *     public void save(Player player) {
 *         // Logic to save preferences (potentially using reflection to write annotated fields)
 *     }
 *
 *     @Override
 *     public void reset(Player player) {
 *         // Logic to reset preferences to defaults
 *     }
 *
 *     @Override
 *     public String describe(Player player) {
 *         // Return a description of all the player's preferences
 *         return "Town: " + townName + ", Nation: " + nationName;
 *     }
 * }
 * }</pre>
 *
 * <p>This example shows how to create a `PlayerPreference` class that manages preferences for a player's town and nation./p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public abstract class PlayerPreference implements IPlayerPreference {

    protected UUID playerUuid;

    /**
     * Constructs a PlayerPreference instance associated with the given player.
     * The player's UUID is used internally for preference management.
     *
     * @param player The player whose preferences are managed by this class.
     */
    public PlayerPreference(Player player) {
        this.playerUuid = player.getUniqueId();
    }

    /**
     * {@inheritDoc}
     * Subclasses should provide an implementation that loads preferences specific to the player.
     */
    @Override
    public abstract void load(Player player);

    /**
     * {@inheritDoc}
     * Subclasses should provide an implementation that saves the current state of preferences for the player.
     */
    @Override
    public abstract void save(Player player);

    /**
     * Provides a default implementation for resetting player preferences.
     * This method can be overridden by subclasses to implement custom reset behavior.
     *
     * @param player The player whose preferences are to be reset.
     */
    @Override
    public void reset(Player player) {
        // Default implementation: subclasses should override this with custom logic.
    }

    /**
     * {@inheritDoc}
     * This implementation combines descriptions from various parts (handled by describePart method)
     * to create a full description of the player's preferences.
     *
     * @param player The player whose preference summary is to be retrieved.
     * @return A concatenated string representation of the player's preferences.
     */
    @Override
    public String describe(Player player) {
        List<String> descriptions = new ArrayList<>();
        // Assume each concrete class has a describePart method
        descriptions.add(describePart());
        // Concatenate all the descriptions into one string
        return String.join(", ", descriptions);
    }

    /**
     * Provides a part of the description of player preferences specific to the subclass.
     * Subclasses should override this method to contribute to the overall description of the player's preferences.
     *
     * @return A string representing a part of the player's preference description.
     */
    protected abstract String describePart();
}
