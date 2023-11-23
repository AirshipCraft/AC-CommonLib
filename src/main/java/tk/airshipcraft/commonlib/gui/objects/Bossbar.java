package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.CommonLib;

/**
 * Encapsulates the Bukkit API's BossBar feature, offering a simplified and enhanced interface for creating and manipulating boss bars.
 * This class includes utility methods for animating boss bar progress, flashing the bar, updating its appearance after a delay,
 * and adding emojis to the title, providing a richer in-game experience.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class Bossbar {

    private BossBar bossbar;

    /**
     * Constructs a new Bossbar instance with the specified title, color, and style.
     *
     * @param title The title text of the boss bar.
     * @param color The color of the boss bar, using BarColor.
     * @param style The style of the boss bar, using BarStyle.
     */
    public Bossbar(String title, BarColor color, BarStyle style) {
        bossbar = Bukkit.createBossBar(title, color, style);
    }

    /**
     * Adds a player to this boss bar, making it visible to them.
     *
     * @param player The Player to add to the boss bar's visibility.
     */
    public void addPlayer(Player player) {
        bossbar.addPlayer(player);
    }

    /**
     * Removes a player from this boss bar, hiding it from their view.
     *
     * @param player The Player to remove from the boss bar's visibility.
     */
    public void removePlayer(Player player) {
        bossbar.removePlayer(player);
    }

    // Additional getters and setters for boss bar properties (omitted for brevity)

    /**
     * Animates the progress of the boss bar from its current value to a specified target value over a given duration.
     * The animation is achieved by gradually updating the progress value at regular intervals.
     *
     * @param progress The target progress value to animate to (between 0.0 and 1.0).
     * @param duration The duration in game ticks over which to animate the progress.
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
     * Flashes the boss bar by alternating its color and style at regular intervals for a specified duration.
     * This effect creates a noticeable flashing animation to draw players' attention.
     *
     * @param duration The duration in ticks for which the boss bar should flash.
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
     * This method allows for delayed customization of the boss bar's appearance.
     *
     * @param color  The new color to set for the boss bar, using BarColor.
     * @param style  The new style to set for the boss bar, using BarStyle.
     * @param delay  The delay in ticks before applying the new color and style.
     */
    public void updateAfterDelay(BarColor color, BarStyle style, long delay) {
        Bukkit.getScheduler().runTaskLater(CommonLib.getInstance(), () -> {
            bossbar.setColor(color);
            bossbar.setStyle(style);
        }, delay);
    }

    /**
     * Adds an emoji or special character to the beginning of the boss bar's title.
     * This method is useful for adding visual flair or emphasizing the boss bar's message.
     *
     * @param emoji The emoji or character string to prepend to the boss bar's title.
     */
    public void addEmojiToTitle(String emoji) {
        bossbar.setTitle(emoji + " " + bossbar.getTitle());
    }

    // Additional utility methods can be added here as needed.
}
