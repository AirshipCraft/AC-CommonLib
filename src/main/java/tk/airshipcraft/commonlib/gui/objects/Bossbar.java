package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.CommonLib;

/**
 * This class encapsulates the BossBar feature from the Bukkit API, providing a simplified
 * interface to create and manipulate boss bars in-game. It includes utility methods
 * for common tasks such as animating the progress of the boss bar, adding emojis, and more.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class Bossbar {

    private BossBar bossbar;

    /**
     * Creates a new boss bar with the specified title, color, and style.
     *
     * @param title The title of the boss bar.
     * @param color The color of the boss bar.
     * @param style The style of the boss bar.
     */
    public Bossbar(String title, BarColor color, BarStyle style) {
        bossbar = Bukkit.createBossBar(title, color, style);
    }

    /**
     * Adds a player to the boss bar.
     *
     * @param player The player to add.
     */
    public void addPlayer(Player player) {
        bossbar.addPlayer(player);
    }

    /**
     * Removes a player from the boss bar.
     *
     * @param player The player to remove.
     */
    public void removePlayer(Player player) {
        bossbar.removePlayer(player);
    }

    // Additional getters and setters for boss bar properties (omitted for brevity)

    /**
     * Animates the progress of the boss bar to the specified value over a given duration.
     *
     * @param progress The target progress value (between 0.0 and 1.0).
     * @param duration The duration over which to animate the progress (in ticks).
     */
    public void animateProgress(double progress, int duration) {
        double currentProgress = bossbar.getProgress();
        double increment = (progress - currentProgress) / duration;

        for (int i = 0; i <= duration; i++) {
            double newProgress = currentProgress + (increment * i);
            Bukkit.getScheduler().runTaskLater(CommonLib.getInstance(), () -> {
                if (newProgress >= 0 && newProgress <= 1) {
                    bossbar.setProgress(newProgress);
                }
            }, i);
        }
    }

    /**
     * Flashes the boss bar by alternating its color and style for a specified duration.
     *
     * @param duration The number of ticks to flash the boss bar for.
     */
    public void flash(int duration) {
        BarColor originalColor = bossbar.getColor();
        BarStyle originalStyle = bossbar.getStyle();
        Bukkit.getScheduler().runTaskTimer(CommonLib.getInstance(), () -> {
            bossbar.setColor(bossbar.getColor() == originalColor ? BarColor.WHITE : originalColor);
            bossbar.setStyle(bossbar.getStyle() == originalStyle ? BarStyle.SEGMENTED_10 : originalStyle);
        }, 0, 20); // Flash every second

        Bukkit.getScheduler().runTaskLater(CommonLib.getInstance(), () -> {
            bossbar.setColor(originalColor);
            bossbar.setStyle(originalStyle);
        }, duration);
    }

    /**
     * Updates the color and style of the boss bar after a specified delay.
     *
     * @param color  The new color to set after the delay.
     * @param style  The new style to set after the delay.
     * @param delay  The delay in ticks before applying the new color and style.
     */
    public void updateAfterDelay(BarColor color, BarStyle style, long delay) {
        Bukkit.getScheduler().runTaskLater(CommonLib.getInstance(), () -> {
            bossbar.setColor(color);
            bossbar.setStyle(style);
        }, delay);
    }

    /**
     * Adds an emoji to the beginning of the boss bar title.
     *
     * @param emoji The emoji to prepend to the title.
     */
    public void addEmojiToTitle(String emoji) {
        bossbar.setTitle(emoji + " " + bossbar.getTitle());
    }

    // Additional utility methods can be added here as needed.
}
