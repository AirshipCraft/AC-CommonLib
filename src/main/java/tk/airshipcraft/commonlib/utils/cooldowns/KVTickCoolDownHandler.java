package tk.airshipcraft.commonlib.utils.cooldowns;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Cooldown handler implementation using Minecraft ticks as the time unit.
 * Each object can have its own cooldown timer for different actions,
 * which are identified by unique keys.
 *
 * @param <T> The type of objects that cooldowns are assigned to.
 * @param <K> The type of key identifying the cooldown.
 * @author Maxopoly, notzune
 * @version 1.0.1
 * @since 2023-10-11
 */
public class KVTickCoolDownHandler<T, K> implements IKVCoolDownHandler<T, K> {

    /**
     * A synchronized map holding cooldown data for each object.
     */
    private final Map<T, Map<K, Long>> cooldownData = Collections.synchronizedMap(new HashMap<>());

    /**
     * The total duration of the cooldown in ticks.
     */
    private final long cooldown;

    /**
     * A counter that increments with each tick, used for tracking cooldowns.
     */
    private long tickCounter;

    /**
     * Constructs a new TickCoolDownHandler instance.
     *
     * @param executingPlugin The plugin instance executing this handler.
     * @param cooldown        The total duration of the cooldown in ticks.
     */
    public KVTickCoolDownHandler(JavaPlugin executingPlugin, long cooldown) {
        this.cooldown = cooldown;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(executingPlugin, () -> {
            tickCounter++; // Increment every tick
        }, 1L, 1L);
    }

    /**
     * Puts the specified object on cooldown for a specific action.
     *
     * @param t The object to put on cooldown.
     * @param k The key identifying the cooldown.
     */
    @Override
    public void putOnCoolDown(T t, K k) {
        cooldownData.computeIfAbsent(t, key -> Collections.synchronizedMap(new HashMap<>()))
                .put(k, tickCounter);
    }

    /**
     * Gets a specific cooldown duration based on the key for a given object.
     *
     * @param t The object to get the cooldown for.
     * @param k The key identifying the cooldown.
     * @return The specific cooldown duration in ticks, or 0 if the object is not on cooldown for the specified key.
     */
    public long getSpecificCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        if (cooldownMap != null) {
            Long lastUsed = cooldownMap.get(k);
            if (lastUsed != null) {
                long leftOver = tickCounter - lastUsed;
                return Math.max(0L, cooldown - leftOver);
            }
        }
        return 0L;  // Return 0 if the object is not on cooldown for the specified key
    }

    /**
     * Checks whether the specified object is on cooldown for any action.
     *
     * @param t The object to check.
     * @return True if the object is on cooldown for any action, false otherwise.
     */
    @Override
    public boolean onCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        if (cooldownMap != null) {
            for (Long lastUsed : cooldownMap.values()) {
                if ((tickCounter - lastUsed) <= cooldown) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Gets the remaining cooldown duration for the specified object for any action.
     * This method returns the remaining cooldown for the first action found to be on cooldown.
     *
     * @param t The object to check.
     * @return The remaining cooldown duration in ticks, or 0 if the object is not on cooldown.
     */
    @Override
    public long getRemainingCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        if (cooldownMap != null) {
            for (Long lastUsed : cooldownMap.values()) {
                long leftOver = tickCounter - lastUsed;
                long remaining = Math.max(0L, cooldown - leftOver);
                if (remaining > 0) {
                    return remaining;
                }
            }
        }
        return 0L;
    }

    /**
     * Gets the total cooldown duration for this handler.
     *
     * @return The total cooldown duration in ticks.
     */
    @Override
    public long getTotalCoolDown() {
        return cooldown;
    }

    /**
     * Removes the cooldown for the specified object and key.
     *
     * @param t The object to remove the cooldown for.
     * @param k The key identifying the cooldown.
     */
    @Override
    public void removeCooldown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        if (cooldownMap != null) {
            cooldownMap.remove(k);
            if (cooldownMap.isEmpty()) {
                cooldownData.remove(t);
            }
        }
    }

    /**
     * Cleans up expired cooldown entries from the cooldown map.
     * This can be called periodically to ensure stale data is removed.
     */
    public void cleanupExpiredEntries() {
        long currentTick = tickCounter;
        cooldownData.forEach((t, map) -> {
            map.entrySet().removeIf(entry -> (currentTick - entry.getValue()) > cooldown);
            if (map.isEmpty()) {
                cooldownData.remove(t);
            }
        });
    }
}
