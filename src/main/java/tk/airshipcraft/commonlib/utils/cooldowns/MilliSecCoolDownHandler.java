package tk.airshipcraft.commonlib.utils.cooldowns;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages cooldowns for generic objects with millisecond precision.
 * This handler stores and checks cooldowns based on system time stamps, allowing you to limit actions within a specified cooldown period.
 *
 * @param <E> The type of object that cooldowns are being tracked for.
 * @author Maxopoly, notzune
 * @since 2023-04-02
 * @version 1.0.0
 */
public class MilliSecCoolDownHandler<E> implements ICoolDownHandler<E> {

    private Map<E, Long> cds;
    private long cooldown;

    /**
     * Initializes a cooldown handler with a specified duration.
     *
     * @param cooldown The duration of the cooldown in milliseconds.
     */
    public MilliSecCoolDownHandler(long cooldown) {
        this.cooldown = cooldown;
        cds = new HashMap<>();
    }

    /**
     * Puts an object on cooldown, starting from the current system time.
     *
     * @param e The object to put on cooldown.
     */
    @Override
    public void putOnCoolDown(E e) {
        cds.put(e, System.currentTimeMillis());
    }

    /**
     * Checks if an object is currently on cooldown.
     *
     * @param e The object to check for cooldown.
     * @return true if the object is on cooldown, false otherwise.
     */
    @Override
    public boolean onCoolDown(E e) {
        Long lastUsed = cds.get(e);
        return lastUsed != null && (System.currentTimeMillis() - lastUsed) <= cooldown;
    }

    /**
     * Gets the remaining cooldown time for an object.
     *
     * @param e The object to check the cooldown for.
     * @return The remaining cooldown time in milliseconds, or 0 if the object is not on cooldown or the cooldown has expired.
     */
    @Override
    public long getRemainingCoolDown(E e) {
        Long lastUsed = cds.get(e);
        if (lastUsed == null) {
            return 0L;
        }
        long elapsedTime = System.currentTimeMillis() - lastUsed;
        return elapsedTime < cooldown ? cooldown - elapsedTime : 0L;
    }

    /**
     * Retrieves the total cooldown duration set for this handler.
     *
     * @return The cooldown duration in milliseconds.
     */
    @Override
    public long getTotalCoolDown() {
        return cooldown;
    }

    /**
     * Removes an object from the cooldown tracking, effectively resetting its cooldown status.
     *
     * @param e The object to remove from cooldown tracking.
     */
    @Override
    public void removeCooldown(E e) {
        cds.remove(e);
    }
}
