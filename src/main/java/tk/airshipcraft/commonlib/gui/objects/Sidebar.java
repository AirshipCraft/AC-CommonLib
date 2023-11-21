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
import java.util.stream.IntStream;

/**
 * This class represents a sidebar scoreboard in Minecraft, which is displayed on the side of the player's screen.
 * It allows for the creation of custom scoreboard objectives and scores that can be displayed to players.
 * Additionally, this class provides methods to add, remove, and manipulate scores and text on the sidebar.
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
     * Initializes a new Sidebar instance with a specific title.
     *
     * @param title The title displayed at the top of the sidebar.
     */
    public Sidebar(String title) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("sidebar", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        scores = new HashMap<>();
    }

    /**
     * Adds a text line with a specific score to the sidebar.
     * If the text already exists, it will update its score.
     *
     * @param text  The text to add as a line on the sidebar.
     * @param score The score used to order the text on the sidebar.
     */
    public void add(String text, int score) {
        Score s = objective.getScore(text);
        s.setScore(score);
        scores.put(text, s);
    }

    /**
     * Adds a text line to the sidebar with the next available score.
     *
     * @param text The text to add as a line on the sidebar.
     */
    public void add(String text) {
        add(text, scores.size() + 1);
    }

    /**
     * Removes a specific line of text from the sidebar.
     *
     * @param text The text line to remove from the sidebar.
     */
    public void remove(String text) {
        scores.remove(text);
        scoreboard.resetScores(text);
    }

    /**
     * Sets the score for an existing line of text. If the text does not exist, it is added.
     *
     * @param text  The line of text to update the score for.
     * @param score The new score for the text line.
     */
    public void setLine(String text, int score) {
        if (scores.containsKey(text)) {
            remove(text);
        }
        add(text, score);
    }

    /**
     * Clears all text lines from the sidebar.
     */
    public void clear() {
        scores.keySet().forEach(scoreboard::resetScores);
        scores.clear();
    }

    /**
     * Applies a color and style to the sidebar title and text lines.
     *
     * @param color The color to apply to the sidebar text.
     * @param style The style to apply to the sidebar text.
     */
    public void applyStyle(ChatColor color, ChatColor style) {
        objective.setDisplayName(color + "" + style + objective.getDisplayName());
        scores.forEach((text, score) -> {
            scoreboard.resetScores(text);
            add(color + "" + style + text, score.getScore());
        });
    }

    /**
     * Displays the sidebar to a specified player.
     *
     * @param player The player to display the sidebar to.
     */
    public void show(Player player) {
        player.setScoreboard(scoreboard);
    }

    /**
     * Hides the sidebar from a specified player, reverting them to the main scoreboard.
     *
     * @param player The player to hide the sidebar from.
     */
    public void hide(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    /**
     * Retrieves the text from a specific line on the sidebar.
     *
     * @param line The line number to retrieve text from.
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
     * Checks whether a specific line on the sidebar has text set.
     *
     * @param line The line number to check.
     * @return {@code true} if the line has no text set, {@code false} otherwise.
     */
    public boolean isLineEmpty(int line) {
        return getTextFromLine(line) == null;
    }

    /**
     * Renames a line if it exists or adds it if it does not.
     *
     * @param oldText The existing text to find and rename.
     * @param newText The new text to replace the old text.
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
     * @param text   The line of text whose score to increment.
     * @param amount The amount to increment the score by.
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
     * @param text   The line of text whose score to decrement.
     * @param amount The amount to decrement the score by.
     */
    public void decrementScore(String text, int amount) {
        incrementScore(text, -amount);
    }

    /**
     * Updates the display name of the sidebar.
     *
     * @param name The new name to set for the sidebar.
     */
    public void updateDisplayName(String name) {
        objective.setDisplayName(name);
    }

    /**
     * Gets the Scoreboard object this sidebar is based on.
     *
     * @return The Scoreboard object.
     */
    public Scoreboard getScoreboard() {
        return scoreboard;
    }
}
