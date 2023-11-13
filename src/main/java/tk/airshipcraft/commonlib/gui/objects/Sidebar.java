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
 * This class represents a scoreboard sidebar for a player in Minecraft.
 * <p>
 * It allows adding and removing lines of text to the scoreboard, setting their position,
 * <p>
 * and applying a color and style to the scoreboard title and lines.
 */
public class Sidebar {
    private Scoreboard scoreboard;
    private Objective objective;
    private Map<String, Score> scores;

    /**
     * Creates a new Sidebar object with the given title.
     *
     * @param title the title of the scoreboard sidebar
     */
    public Sidebar(String title) {
        scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        objective = scoreboard.registerNewObjective("sidebar", "dummy", title);
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        scores = new HashMap<>();
    }

    /**
     * Adds a line of text to the scoreboard at the specified score.
     *
     * @param text  the text to add to the scoreboard
     * @param score the score at which to add the text
     */
    public void add(String text, int score) {
        Score s = objective.getScore(text);
        s.setScore(score);
        scores.put(text, s);
    }

    /**
     * Adds a line of text to the scoreboard with the next available score.
     *
     * @param text the text to add to the scoreboard
     */
    public void add(String text) {
        int score = scores.size() + 1;
        Score s = objective.getScore(text);
        s.setScore(score);
        scores.put(text, s);
    }

    /**
     * Adds a line of text to the scoreboard with the next available score.
     *
     * @param text the text to add to the scoreboard
     */
    public void remove(String text) {
        Score s = scores.get(text);
        if (s != null) {
            objective.getScoreboard().resetScores(text);
            scores.remove(text);
        }
    }

    /**
     * Sets the position of a line of text on the scoreboard.
     * This method removes the line from its current position and adds it to the new position.
     *
     * @param text  the text to set the position of
     * @param score the new position of the text
     */
    public void setLine(String text, int score) {
        remove(text);
        add(text, score);
    }

    /**
     * Clears all lines of text from the scoreboard.
     */
    public void clear() {
        for (String text : scores.keySet()) {
            objective.getScoreboard().resetScores(text);
        }
        scores.clear();
    }

    /**
     * Applies a color and style to the scoreboard title and lines of text.
     *
     * @param color the color to apply to the title and lines
     * @param style the style to apply to the title and lines
     */
    public void applyStyle(ChatColor color, ChatColor style) {
        objective.setDisplayName(color + "" + style + objective.getDisplayName());
        for (String text : scores.keySet()) {
            Score s = scores.get(text);
            int oldScore = s.getScore();
            objective.getScoreboard().resetScores(text);
            Score newScore = objective.getScore(color + "" + style + text);
            newScore.setScore(oldScore);
            scores.put(text, newScore);
        }
    }

    /**
     * shows a hidden sidebar to a given player
     *
     * @param player the player to show the sidebar to
     */
    public void show(Player player) {
        player.setScoreboard(scoreboard);
    }

    /**
     * hides a sidebar to a given player
     *
     * @param player the player to hide the sidebar from
     */
    public void hide(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
    }

    /**
     * get the text from a specific line in a sidebar
     *
     * @param line the line to get the text from
     * @return returns the text in the given line. Returns null if the line is out of range or if the sidebar does not exist.
     */
    public String getTextFromLine(int line) {
        for (Map.Entry<String, Score> entry : scores.entrySet()) {
            if (entry.getValue().getScore() == line) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Checks if a given line is out of range, or does not have a value
     *
     * @param line the line to check
     * @return a boolean; if true the line is either out of range or does not have a value, if false the line has a value.
     */
    public boolean isLineNull(int line) {
        for (Map.Entry<String, Score> entry : scores.entrySet()) {
            return entry.getValue().getScore() == line;
        }
        return true;
    }
}
