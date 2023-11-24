/**
 * <p>Provides abstract interface classes for managing player preferences within a Bukkit plugin environment.
 * These classes facilitate the storage, retrieval, and management of player-specific settings, which
 * can be utilized by other plugins for a persistent, personalized experience.</p>
 *
 * <h2>Classes:</h2>
 * <ul>
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.IPlayerPreference}</b>: An interface outlining the necessary
 *   methods for handling player preferences. It includes methods to load, save, reset, and describe player-specific settings.</li>
 *
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.PlayerPref}</b>: An annotation indicating that a field
 *   should be considered a player preference, making it eligible for automatic persistence and retrieval by the preference system.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <p>To implement the player preference system, developers must create classes that implement the
 * {@link tk.airshipcraft.commonlib.configuration.IPlayerPreference} interface. Fields within these classes
 * should be annotated with {@link tk.airshipcraft.commonlib.configuration.PlayerPref} to indicate they are
 * player preferences. Developers can then interact with these preferences through the provided interface methods.</p>
 *
 * <h2>Example:</h2>
 * <pre>{@code
 * public class ExamplePlayerPreference implements IPlayerPreference {
 *     @PlayerPref
 *     private String someSetting;
 *
 *     public void load(Player player) {
 *         // Implement loading logic here
 *     }
 *
 *     public void save(Player player) {
 *         // Implement saving logic here
 *     }
 *
 *     public void reset(Player player) {
 *         // Implement reset logic here
 *     }
 *
 *     public String describe(Player player) {
 *         // Implement description logic here
 *     }
 * }
 * }</pre>
 *
 * <p>This system allows for a modular approach to player preferences, where each plugin can define its own
 * preferences without worrying about conflicts with others. The use of annotations and interfaces makes the
 * system extensible and easy to integrate into any plugin.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
package tk.airshipcraft.commonlib.configuration;