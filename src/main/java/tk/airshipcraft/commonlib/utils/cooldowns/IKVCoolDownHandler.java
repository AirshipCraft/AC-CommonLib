package tk.airshipcraft.commonlib.utils.cooldowns;

/**
 * Interface defining the contract for cooldown handlers with key-value pairs.
 * This interface allows managing cooldowns for various actions or types, each identified by a unique key.
 *
 * @param <E> The type of object that the cooldowns are assigned to. This could be any object type, like a player or an item.
 * @param <K> The type of the key identifying different types of cooldowns, like an action or event type.
 * @author Maxopoly, notzune
 * @version 1.0.0
 * @since 2023-11-13
 */
public interface IKVCoolDownHandler<E, K> {

    /**
     * Puts the specified object on cooldown for a specific action identified by the key.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * IKVCoolDownHandler<Player, String> playerActionCooldown = ...;
     * playerActionCooldown.putOnCoolDown(player, "jump");
     * }
     * </pre>
     *
     * @param e Object to set cooldown for.
     * @param k Key identifying the type of cooldown.
     */
    void putOnCoolDown(E e, K k);

    /**
     * Checks whether the given object is currently on cooldown for a specific action identified by the key.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * if (playerActionCooldown.onCoolDown(player, "jump")) {
     *     System.out.println("Player is on cooldown for jumping!");
     * }
     * }
     * </pre>
     *
     * @param e Object to check.
     * @param k Key identifying the type of cooldown.
     * @return True if the object is on cooldown for the specified key, false otherwise.
     */
    boolean onCoolDown(E e, K k);

    /**
     * Gets the remaining cooldown for the specified object and action. The meaning and scale (like ticks or seconds) of the returned value
     * are dependent on the implementation. It should not be less than 0 or exceed the maximum cooldown.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * long remainingCooldown = playerActionCooldown.getRemainingCoolDown(player, "jump");
     * System.out.println("Remaining cooldown for jumping: " + remainingCooldown);
     * }
     * </pre>
     *
     * @param e Object to get cooldown for.
     * @param k Key identifying the type of cooldown.
     * @return Remaining cooldown time for the specified action, or 0 if not on cooldown.
     */
    long getRemainingCoolDown(E e, K k);

    /**
     * Returns the total maximum cooldown duration for any action in this handler. The meaning of this duration is specific to the implementation.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * long totalCooldown = playerActionCooldown.getTotalCoolDown();
     * System.out.println("Total cooldown duration for any action: " + totalCooldown);
     * }
     * </pre>
     *
     * @return The maximum cooldown duration in this handler.
     */
    long getTotalCoolDown();

    /**
     * Removes the cooldown for the specified object and action.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * playerActionCooldown.removeCooldown(player, "jump");
     * }
     * </pre>
     *
     * @param e Object to remove cooldown for.
     * @param k Key identifying the type of cooldown.
     */
    void removeCooldown(E e, K k);

    /**
     * Gets the specific cooldown duration for a given object and action. This method is useful for retrieving
     * individual cooldown times when an object has multiple cooldowns for different actions.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * long actionCooldown = playerActionCooldown.getSpecificCoolDown(player, "jump");
     * System.out.println("Specific cooldown for jumping: " + actionCooldown);
     * }
     * </pre>
     *
     * @param e The object to get the cooldown for.
     * @param k The key identifying the cooldown.
     * @return The specific cooldown duration for the given action, or 0 if not on cooldown.
     */
    long getSpecificCoolDown(E e, K k);
}
