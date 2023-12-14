package tk.airshipcraft.commonlib.utils.cooldowns;

/**
 * Interface for managing cooldowns of objects. This interface allows tracking and managing cooldowns for various objects,
 * typically used for players or items identified by unique identifiers like UUIDs.
 *
 * @param <E> The type of object that the cooldowns are assigned to. This could be any object type, commonly a player or an item.
 * @author Maxopoly, notzune
 * @version 1.0.0
 * @since 2023-04-02
 */
public interface ICoolDownHandler<E> {

    /**
     * Sets the cooldown to its maximum duration for the specified object.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * ICoolDownHandler<Player> playerCooldownHandler = ...;
     * playerCooldownHandler.putOnCoolDown(player);
     * }
     * </pre>
     *
     * @param e The object to set the cooldown for.
     */
    void putOnCoolDown(E e);

    /**
     * Checks if the specified object is currently on cooldown.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * if (playerCooldownHandler.onCoolDown(player)) {
     *     System.out.println("Player is on cooldown!");
     * }
     * }
     * </pre>
     *
     * @param e The object to check.
     * @return True if the object is on cooldown, false otherwise.
     */
    boolean onCoolDown(E e);

    /**
     * Retrieves the remaining cooldown time for the specified object.
     * The returned value is context-dependent but is always between 0 and the maximum cooldown duration.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * long remainingTime = playerCooldownHandler.getRemainingCoolDown(player);
     * System.out.println("Remaining cooldown time: " + remainingTime);
     * }
     * </pre>
     *
     * @param e The object to get the cooldown for.
     * @return The remaining cooldown time for the object, or 0 if there's no active cooldown.
     */
    long getRemainingCoolDown(E e);

    /**
     * Returns the total maximum cooldown duration set for this handler.
     * The meaning of this duration is implementation-specific.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * long totalCooldown = playerCooldownHandler.getTotalCoolDown();
     * System.out.println("Total cooldown duration: " + totalCooldown);
     * }
     * </pre>
     *
     * @return The maximum cooldown duration for this handler.
     */
    long getTotalCoolDown();

    /**
     * Removes the cooldown for the specified object, effectively resetting its cooldown status.
     * <p>
     * Usage Example:
     * <pre>
     * {@code
     * playerCooldownHandler.removeCooldown(player);
     * }
     * </pre>
     *
     * @param e The object for which to remove the cooldown.
     */
    void removeCooldown(E e);

}
