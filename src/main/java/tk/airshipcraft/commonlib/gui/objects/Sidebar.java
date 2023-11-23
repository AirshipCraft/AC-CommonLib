package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a sidebar scoreboard in Minecraft, displayed on the side of a player's screen.
 * This class facilitates the creation and management of custom scoreboard objectives and scores,
 * allowing for dynamic updating and display of information to players.
 * It includes methods for adding, removing, and manipulating scores and text on the sidebar.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class Sidebar {

    private Scoreboard scoreboard;
    private Objective objective;
    private Map<String, Score> scores;

    /**
     * Initializes a new Sidebar instance with a given title.
     *
     * @param title The title to be displayed at the top of the sidebar.
     */
    public Sidebar(String title) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("sidebar", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        scores = new HashMap<>();
    }

    /**
     * Adds a line of text with a specified score to the sidebar.
     * Updates the score of the text if it already exists.
     *
     * @param text  The text to be displayed as a line on the sidebar.
     * @param score The score to order the line on the sidebar.
     */
    public void add(String text, int score) {
        Score s = objective.getScore(text);
        s.setScore(score);
        scores.put(text, s);
    }

    /**
     * Adds a line of text to the sidebar using the next available score.
     *
     * @param text The text to be added as a line on the sidebar.
     */
    public void add(String text) {
        add(text, scores.size() + 1);
    }

    /**
     * Removes a specific line of text from the sidebar.
     *
     * @param text The text line to be removed from the sidebar.
     */
    public void remove(String text) {
        scores.remove(text);
        scoreboard.resetScores(text);
    }

    /**
     * Sets or updates the score for a specific line of text.
     * If the text does not exist, it is added to the sidebar.
     *
     * @param text  The text line to update.
     * @param score The new score to assign to the text line.
     */
    public void setLine(String text, int score) {
        if (scores.containsKey(text)) {
            remove(text);
        }
        add(text, score);
    }

    /**
     * Clears all lines of text from the sidebar.
     */
    public void clear() {
        scores.keySet().forEach(scoreboard::resetScores);
        scores.clear();
    }

    /**
     * Applies a specified color and style to the sidebar's title and text lines.
     *
     * @param color The ChatColor to apply to the sidebar text.
     * @param style The ChatColor style (e.g., bold, italic) to apply to the sidebar text.
     */
    public void applyStyle(ChatColor color, ChatColor style) {
        objective.setDisplayName(color + "" + style + objective.getDisplayName());
        scores.forEach((text, score) -> {
            scoreboard.resetScores(text);
            add(color + "" + style + text, score.getScore());
        });
    }

    /**
     * Shows the sidebar to a specific player.
     *
     * @param player The player to whom the sidebar will be displayed.
     */
    public void show(Player player) {
        player.setScoreboard(scoreboard);
    }

    /**
     * Hides the sidebar from a specific player, reverting to their main scoreboard.
     *
     * @param player The player from whom the sidebar will be hidden.
     */
    public void hide(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    /**
     * Retrieves the text of a specific line from the sidebar.
     *
     * @param line The line number from which to retrieve the text.
     * @return The text of the specified line, or {@code null} if no text is set at that line.
     */
    public String getTextFromLine(int line) {
        return scores.entrySet().stream()
                .filter(entry -> entry.getValue().getScore() == line)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    /**
     * Checks if a specific line on the sidebar is empty (i.e., has no set text).
     *
     * @param line The line number to check.
     * @return {@code true} if the line is empty, {@code false} otherwise.
     */
    public boolean isLineEmpty(int line) {
        return getTextFromLine(line) == null;
    }

    /**
     * Renames an existing line or adds it if it does not exist.
     *
     * @param oldText The current text of the line to find and rename.
     * @param newText The new text to replace the existing text.
     */
    public void renameLine(String oldText, String newText) {
        Score score = scores.get(oldText);
        if (score != null) {
            scores.remove(oldText);
            scoreboard.resetScores(oldText);
            add(newText, score.getScore());
        }
    }

    /**
     * Increments the score of a given line by a specified amount.
     *
     * @param text   The text line whose score is to be incremented.
     * @param amount The amount by which to increment the score.
     */
    public void incrementScore(String text, int amount) {
        Score score = scores.get(text);
        if (score != null) {
            score.setScore(score.getScore() + amount);
        }
    }

    /**
     * Decrements the score of a given line by a specified amount.
     *
     * @param text   The text line whose score is to be decremented.
     * @param amount The amount by which to decrement the score.
     */
    public void decrementScore(String text, int amount) {
        incrementScore(text, -amount);
    }

    /**
     * Updates the display name of the sidebar.
     *
     * @param name The new display name for the sidebar.
     */
    public void updateDisplayName(String name) {
        objective.setDisplayName(name);
    }

    /**
     * Retrieves the underlying Scoreboard object associated with this sidebar.
     *
     * @return The Scoreboard object.
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
