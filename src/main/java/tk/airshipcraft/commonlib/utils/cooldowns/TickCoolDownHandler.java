package tk.airshipcraft.commonlib.utils.cooldowns;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages cooldowns for objects based on the game's tick system.
 * Each game tick, the internal counter is incremented, which is used to track cooldowns.
 * This implementation is useful for cooldowns that need to be in sync with the game's tick rate, such as abilities or actions that
 * are used within the game world.
 *
 * @param <E> The type of object that cooldowns are being tracked for.
 * @author Maxopoly, notzune
 * @since 2023-04-02
 * @version 1.0.0
 */
public class TickCoolDownHandler<E> implements ICoolDownHandler<E> {

    private Map<E, Long> cds;
    private long cooldown;
    private long tickCounter;

    /**
     * Initializes a cooldown handler for objects based on ticks, using an internal counter.
     *
     * @param executingPlugin The JavaPlugin instance that this cooldown handler belongs to.
     * @param cooldown        The duration of the cooldown in ticks.
     */
    public TickCoolDownHandler(JavaPlugin executingPlugin, long cooldown) {
        this.cooldown = cooldown;
        cds = new HashMap<>();
        // Schedule a repeating task to increment the tick counter every game tick
        Bukkit.getScheduler().scheduleSyncRepeatingTask(executingPlugin, () -> tickCounter++, 1L, 1L);
    }

    @Override
    public void putOnCoolDown(E e) {
        cds.put(e, tickCounter);
    }

    @Override
    public boolean onCoolDown(E e) {
        Long lastUsed = cds.get(e);
        return lastUsed != null && (tickCounter - lastUsed) <= cooldown;
    }

    @Override
    public long getRemainingCoolDown(E e) {
        Long lastUsed = cds.get(e);
        if (lastUsed == null) {
            return 0L;
        }
        long elapsedTicks = tickCounter - lastUsed;
        return elapsedTicks < cooldown ? cooldown - elapsedTicks : 0L;
    }

    @Override
    public long getTotalCoolDown() {
        return cooldown;
    }

    @Override
    public void removeCooldown(E e) {
        cds.remove(e);
    }
}
