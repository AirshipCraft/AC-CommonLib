package tk.airshipcraft.commonlib.configuration;

import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Abstract base class for player preferences.
 * This class provides a skeletal implementation of the IPlayerPreference interface
 * to minimize the effort required to implement this interface.
 * <p>
 * /**
 * Represents the preferences for a player in the MyPlugin system.
 * This class stores the settings that can be configured by the player within the plugin.
 * <p>
 * Usage example in another plugin:
 * <pre>{@code
 * public class MyPluginPlayerPreferences implements IPlayerPreference {
 *
 *     @PlayerPref(defaultValue = "defaultTown")
 *     private String townName;
 *
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
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public abstract class PlayerPreference implements IPlayerPreference {

    protected UUID playerUuid;

    /**
     * Constructor that initializes the PlayerPreference with the player's UUID.
     *
     * @param player The player whose preferences are managed by this class.
     */
    public PlayerPreference(Player player) {
        this.playerUuid = player.getUniqueId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void load(Player player);

    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void save(Player player);

    /**
     * Provides a default implementation for resetting player preferences.
     * Subclasses can override this method to provide custom reset logic.
     *
     * @param player The player whose preferences are to be reset.
     */
    @Override
    public void reset(Player player) {
        // Default implementation: subclasses should override this with custom logic.
    }

    /**
     * {@inheritDoc}
     * Concatenates the descriptions of all player preferences.
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
     * This should be overridden by subclasses to add their specific description.
     *
     * @return A string part of the player's preferences description.
     */
    protected abstract String describePart();
}