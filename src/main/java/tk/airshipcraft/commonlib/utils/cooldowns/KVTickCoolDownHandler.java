package tk.airshipcraft.commonlib.utils.cooldowns;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of {@link IKVCoolDownHandler} using Minecraft ticks as the time unit.
 * Allows managing cooldowns for different types of actions or events, identified by unique keys, for various objects.
 *
 * @param <T> The type of objects that cooldowns are assigned to.
 * @param <K> The type of key identifying different cooldowns.
 * @author Maxopoly, notzune
 * @version 1.0.1
 * @since 2023-10-11
 */
public class KVTickCoolDownHandler<T, K> implements IKVCoolDownHandler<T, K> {

    private final Map<T, Map<K, Long>> cooldownData = Collections.synchronizedMap(new HashMap<>());
    private final long cooldown;
    private long tickCounter;

    /**
     * Constructs a new {@code KVTickCoolDownHandler} instance.
     *
     * @param executingPlugin The Bukkit plugin instance executing this handler.
     * @param cooldown        The total duration of the cooldown in ticks.
     */
    public KVTickCoolDownHandler(JavaPlugin executingPlugin, long cooldown) {
        this.cooldown = cooldown;
        Bukkit.getScheduler().scheduleSyncRepeatingTask(executingPlugin, () -> tickCounter++, 1L, 1L);
    }

    @Override
    public void putOnCoolDown(T t, K k) {
        cooldownData.computeIfAbsent(t, key -> Collections.synchronizedMap(new HashMap<>())).put(k, tickCounter);
    }

    public long getSpecificCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        return cooldownMap != null && cooldownMap.get(k) != null ? Math.max(0L, cooldown - (tickCounter - cooldownMap.get(k))) : 0L;
    }

    @Override
    public boolean onCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        return cooldownMap != null && cooldownMap.containsKey(k) && (tickCounter - cooldownMap.get(k)) <= cooldown;
    }

    @Override
    public long getRemainingCoolDown(T t, K k) {
        Map<K, Long> cooldownMap = cooldownData.get(t);
        if (cooldownMap != null && cooldownMap.containsKey(k)) {
            long leftOver = tickCounter - cooldownMap.get(k);
            return Math.max(0L, cooldown - leftOver);
        }
        return 0L;
    }

    @Override
    public long getTotalCoolDown() {
        return cooldown;
    }

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
     * Cleans up expired cooldown entries. This method should be called periodically to prevent memory leaks.
     * It removes any entries that have exceeded their cooldown period.
     */
    public void cleanupExpiredEntries() {
        long currentTick = tickCounter;
        cooldownData.forEach((t, map) -> map.entrySet().removeIf(entry -> (currentTick - entry.getValue()) > cooldown));
    }
}
