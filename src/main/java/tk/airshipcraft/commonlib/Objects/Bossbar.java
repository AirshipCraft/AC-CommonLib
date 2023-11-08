package tk.airshipcraft.commonlib.Objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.CommonLib;

/**
 * Represents a Minecraft boss bar.
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

    /**
     * Sets the title of the boss bar.
     *
     * @param title The new title.
     */
    public void setTitle(String title) {
        bossbar.setTitle(title);
    }

    /**
     * Sets the color of the boss bar.
     *
     * @param color The new color.
     */
    public void setColor(BarColor color) {
        bossbar.setColor(color);
    }

    /**
     * Sets the style of the boss bar.
     *
     * @param style The new style.
     */
    public void setStyle(BarStyle style) {
        bossbar.setStyle(style);
    }

    /**
     * Sets the progress of the boss bar.
     *
     * @param progress The new progress (a value between 0 and 1).
     */
    public void setProgress(double progress) {
        bossbar.setProgress(progress);
    }

    /**
     * Sets the visibility of the boss bar.
     *
     * @param visible Whether the boss bar should be visible.
     */
    public void setVisible(boolean visible) {
        bossbar.setVisible(visible);
    }

    /**
     * Gets the title of the boss bar.
     *
     * @return The title of the boss bar.
     */
    public String getTitle() {
        return bossbar.getTitle();
    }

    /**
     * Gets the color of the boss bar.
     *
     * @return The color of the boss bar.
     */
    public BarColor getColor() {
        return bossbar.getColor();
    }

    /**
     * Gets the style of the boss bar.
     *
     * @return The style of the boss bar.
     */
    public BarStyle getStyle() {
        return bossbar.getStyle();
    }

    /**
     * Gets the progress of the boss bar.
     *
     * @return The progress of the boss bar (a value between 0 and 1).
     */
    public double getProgress() {
        return bossbar.getProgress();
    }

    /**
     * Returns whether the boss bar is visible.
     *
     * @return true if the boss bar is visible, false otherwise.
     */
    public boolean isVisible() {
        return bossbar.isVisible();
    }
    /**
     * Animates the progress of the boss bar to the specified value.
     *
     * @param progress The final progress value.
     * @param duration The duration of the animation (in ticks).
     */
    public void animateProgress(double progress, int duration) {
        double currentProgress = bossbar.getProgress();
        double increment = (progress - currentProgress) / duration;
        for (int i = 0; i < duration; i++) {
            final double newProgress = currentProgress + increment;
            Bukkit.getScheduler().scheduleSyncDelayedTask(CommonLib.mainInstance, () -> bossbar.setProgress(newProgress), i);
        }
    }
    /**
     * Adds a message to the boss bar with a specified emoji. (without the object symbol)
     *
     * @param message The message to add to the boss bar.
     * @param emoji The emoji to use.
     */
    public void addEmoji(String message, String emoji) {
        String translated = ChatColor.translateAlternateColorCodes('&', message).replace("&e", emoji);
        bossbar.setTitle(translated);
    }
}
