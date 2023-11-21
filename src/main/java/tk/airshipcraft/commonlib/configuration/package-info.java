/**
 * Provides the classes necessary for managing player preferences within a Bukkit plugin.
 * The preference system allows for persistent storage and retrieval of player-specific settings,
 * which can be used across different plugins.
 *
 * Classes:
 * {@link tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference} - An interface defining
 * the necessary methods for a player preference class, including load, save, and describe methods.
 *
 * {@link tk.airshipcraft.commonlib.configuration.impl.PlayerPref} - An annotation used to mark
 * fields in IPlayerPreference implementations that should be persisted and loaded from the data store.
 *
 * {@link tk.airshipcraft.commonlib.configuration.PlayerPreference} - An abstract class that
 * provides basic implementations of IPlayerPreference methods and handles the reflection logic
 * for annotated fields.
 *
 * {@link tk.airshipcraft.commonlib.configuration.PreferenceProcessor} - A utility class that
 * contains static methods to load and save annotated fields from and to both a file configuration
 * and a MySQL database using reflection.
 *
 * {@link tk.airshipcraft.commonlib.configuration.PreferencesManager} - Manages all player preferences,
 * providing methods to register, load, save, and reset preferences, as well as to describe them for
 * individual players.
 *
 * Usage:
 * To use the preference system, a plugin developer must create a class that implements the
 * {@link tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference} interface and use the
 * {@link tk.airshipcraft.commonlib.configuration.impl.PlayerPref} annotation to mark which fields
 * should be managed. The {@link tk.airshipcraft.commonlib.configuration.PreferencesManager} is then
 * used to handle these preferences.
 *
 * Example:
 * <pre>{@code
 * public class MyPluginPreference implements IPlayerPreference {
 *     @PlayerPref
 *     private String exampleSetting;
 *
 *     public void load(Player player) {
 *         // Load logic here, potentially using PreferenceProcessor
 *     }
 *
 *     public void save(Player player) {
 *         // Save logic here, potentially using PreferenceProcessor
 *     }
 *
 *     public String describe(Player player) {
 *         return "Example Setting: " + exampleSetting;
 *     }
 * }
 *
 * // In your plugin's onEnable method:
 * PreferencesManager prefManager = new PreferencesManager();
 * prefManager.registerPreference(player.getUniqueId(), new MyPluginPreference());
 * }</pre>
 *
 * The {@link tk.airshipcraft.commonlib.commands.PreferenceCommand} can be used as a template for
 * creating commands that interact with the player preferences, allowing players to set and view
 * their preferences in-game.
 *
 * Please note that the reflection-based operations require careful exception handling and validation
 * to ensure data integrity and plugin stability.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
package tk.airshipcraft.commonlib.configuration;
