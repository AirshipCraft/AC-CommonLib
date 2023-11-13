package tk.airshipcraft.commonlib.utils.cooldowns;

/**
 * Interface defining the contract for cooldown handlers.
 *
 * @param <E> Object that cooldowns are assigned to
 * @param <K> Key identifying the type of cooldown
 * @author Maxopoly, notzune
 */
public interface IKVCoolDownHandler<E, K> {

    /**
     * Puts the specified object on cooldown for a specific action.
     *
     * @param e Object to set cooldown for
     * @param k Key identifying the type of cooldown
     */
    void putOnCoolDown(E e, K k);

    /**
     * Checks whether the given object is currently on cooldown in this instance for a specific action.
     *
     * @param e Object to check
     * @param k Key identifying the type of cooldown
     * @return True if the object is on cooldown, false if not
     */
    boolean onCoolDown(E e, K k);

    /**
     * Gets the remaining cooldown of the given item for a specific action. The actual meaning of this number depends on the implementation,
     * but may never be less than 0 and never higher than the predefined maximum cooldown.
     *
     * @param e Object to get cooldown for
     * @param k Key identifying the type of cooldown
     * @return The cooldown left for the given object or 0 if none exists
     */
    long getRemainingCoolDown(E e, K k);

    /**
     * Gives the total cooldown set for this instance. Again the actual meaning of this value depends on the
     * implementation.
     *
     * @return Maximum cooldown in this instance
     */
    long getTotalCoolDown();

    /**
     * Removed the cooldown for the given object for a specific action.
     *
     * @param e Object to check
     * @param k Key identifying the type of cooldown
     */
    void removeCooldown(E e, K k);

    /**
     * Gets a specific cooldown duration based on the key for a given object.
     *
     * @param e The object to get the cooldown for.
     * @param k The key identifying the cooldown.
     * @return The specific cooldown duration in ticks, or 0 if the object is not on cooldown for the specified key.
     */
    long getSpecificCoolDown(E e, K k);
}
