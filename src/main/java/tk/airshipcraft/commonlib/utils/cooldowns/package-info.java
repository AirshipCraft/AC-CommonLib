/**
 * <h2>Cooldown Management Tools</h2>
 * <p>Provides a comprehensive set of tools for managing cooldowns within a Minecraft plugin environment.
 * This package includes interfaces and classes that enable the creation and management of cooldowns based on
 * ticks and milliseconds, as well as a key-value cooldown handler for more complex data structures.</p>
 *
 * <p>The package contains the following key components:</p>
 * <ul>
 *     <li><b>{@link tk.airshipcraft.commonlib.utils.cooldowns.ICoolDownHandler}</b>: An interface defining the basic operations for a cooldown handler,
 *     such as putting an object on cooldown, checking if it is on cooldown, and getting the remaining cooldown time.</li>
 *
 *     <li><b>{@link tk.airshipcraft.commonlib.utils.cooldowns.IKVCoolDownHandler}</b>: An extension of the ICoolDownHandler interface that includes
 *     additional functionality to work with key-value pairs, allowing for more detailed cooldown management.</li>
 *
 *     <li><b>{@link tk.airshipcraft.commonlib.utils.cooldowns.KVTickCoolDownHandler}</b>: A concrete implementation of IKVCooldownHandler that tracks cooldowns
 *     based on the game's tick rate. This class is suitable for actions that should be synchronized with the game's internal timing.</li>
 *
 *     <li><b>{@link tk.airshipcraft.commonlib.utils.cooldowns.MilliSecCoolDownHandler}</b>: A cooldown handler that tracks cooldowns in milliseconds, providing precision
 *     for actions that require real-time tracking, independent of the game's tick rate.</li>
 *
 *     <li><b>{@link tk.airshipcraft.commonlib.utils.cooldowns.TickCoolDownHandler}</b>: Similar to KVTickCoolDownHandler but for single objects. It manages cooldowns in ticks,
 *     ideal for game-related actions that require synchronization with the server tick rate.</li>
 * </ul>
 *
 * <h3>Usage Example:</h3>
 * <p>To use a cooldown handler, first instantiate it with the desired cooldown period. Then, when an action occurs
 * (such as a player casting a spell), use {@code putOnCoolDown()} to start the cooldown. Check if an action is available
 * using {@code onCoolDown()} before allowing it to occur.</p>
 *
 * <h3>Implementing a Custom CoolDownHandler:</h3>
 * <p>Create a class that implements ICoolDownHandler. Define the cooldown storage mechanism and implement all interface methods.
 * Optionally, use Bukkit's scheduler to increment a counter for tick-based handlers.</p>
 *
 * <p>Example:</p>
 * <pre>{@code
 * public class MyCustomCoolDownHandler<T> implements ICoolDownHandler<T> {
 *     private Map<T, Long> coolDowns = new HashMap<>();
 *
 *     // Implement methods here
 * }
 * }</pre>
 *
 * <p>Use this handler by creating an instance and then managing cooldowns for various objects like so:</p>
 * <pre>{@code
 * MyCustomCoolDownHandler<Player> playerCooldowns = new MyCustomCoolDownHandler<>();
 * playerCooldowns.putOnCoolDown(player);
 * if (playerCooldowns.onCoolDown(player)) {
 *     // Notify player that the action is on cooldown
 * }
 * }</pre>
 *
 * <p>For a more detailed and synchronized cooldown, use KVTickCoolDownHandler and schedule a repeating task to increment
 * the tick counter, tying it to the server's tick rate.</p>
 *
 * <p>Example:</p>
 * <pre>{@code
 * KVTickCoolDownHandler<String, MyAction> actionCooldowns = new KVTickCoolDownHandler<>(myPlugin, 20L * 60); // 1 minute cooldown
 * actionCooldowns.putOnCoolDown("Fireball", new MyAction(...));
 * }</pre>
 *
 * <p>Remember to handle the cleanup of cooldowns when they're no longer needed to prevent memory leaks.</p>
 */
package tk.airshipcraft.commonlib.utils.cooldowns;
