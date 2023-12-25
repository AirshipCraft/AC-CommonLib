/**
 * Provides classes and interfaces for managing configurations and player preferences within a Bukkit plugin environment.
 * This package includes tools for handling both general plugin configuration and player-specific settings,
 * facilitating a robust, flexible approach to managing data within Minecraft plugins.
 *
 * <h2>Key Components:</h2>
 * <ul>
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.ConfigHelper}</b>: Handles loading, saving,
 *   and repairing configurations for plugins that extend CommonLib. It uses reflection to initialize configuration
 *   values and maintains a default configuration file for robustness.</li>
 *
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.ConfigOption}</b>: An annotation used to mark fields
 *   in a class as configuration options, specifying a key and a default value. It's designed to work in conjunction
 *   with {@code ConfigurationManager} for automated configuration management.</li>
 *
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.IPlayerPreference}</b>: An interface outlining methods
 *   for handling player preferences, including loading, saving, resetting, and describing player-specific settings.</li>
 *
 *   <li><b>{@link tk.airshipcraft.commonlib.configuration.PlayerPref}</b>: An annotation indicating that a field
 *   should be considered a player preference, facilitating automatic persistence and retrieval by the preference system.</li>
 * </ul>
 *
 * <h2>Usage:</h2>
 * <p>For general configuration management, create classes with fields annotated with {@link tk.airshipcraft.commonlib.configuration.ConfigOption}
 * and manage them through a {@code ConfigurationManager} instance. For player preferences, implement the
 * {@link tk.airshipcraft.commonlib.configuration.IPlayerPreference} interface in classes, annotating fields with {@link tk.airshipcraft.commonlib.configuration.PlayerPref}.</p>
 *
 * <h2>Example:</h2>
 * <pre>{@code
 * // for creating config fields and their default values in side the classes
 * public class SomePluginConfig {
 *     @ConfigOption(key = "feature.enabled", defaultValue = "true")
 *     private static boolean featureEnabled;
 * }
 *
 * // example for implementing player preferences inside of a class
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
 * <p>This package allows for a comprehensive and modular approach to both global plugin configurations
 * and individual player preferences, supporting diverse requirements and facilitating easy integration into Bukkit plugins.</p>
 *
 * @author notzune
 * @version 1.0.0
 * @see tk.airshipcraft.commonlib.configuration.impl.PlayerPreference
 * @since 2023-11-20
 */
package tk.airshipcraft.commonlib.configuration;
