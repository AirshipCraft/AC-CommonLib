package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

/**
 * A utility class for managing and customizing the below-name scoreboard in Minecraft.
 * This class allows for creating and updating a scoreboard display below player names.
 *
 * @author Locutusque, notzune
 * @version 1.0.0
 * @since 2023-04-11
 */
public class BelowName {

    private static final String OBJECTIVE_NAME = "BelowName";
    private ScoreboardManager manager;
    private Scoreboard scoreboard;

    /**
     * Constructs a new BelowName instance using the provided ScoreboardManager.
     *
     * @param manager The ScoreboardManager for creating scoreboards.
     */
    public BelowName(ScoreboardManager manager) {
        this.manager = manager;
        this.scoreboard = manager.getNewScoreboard();
        // Ensure the objective is properly set up.
        setupObjective();
    }

    /**
     * Sets up the objective for the below-name display.
     * If the objective doesn't exist, it's created.
     */
    private void setupObjective() {
        Objective objective = scoreboard.getObjective(OBJECTIVE_NAME);
        if (objective == null) {
            objective = scoreboard.registerNewObjective(OBJECTIVE_NAME, "dummy", "BelowName");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
    }

    /**
     * Sets the display name of the below-name scoreboard for the specified player.
     *
     * @param player      The player whose scoreboard display name is to be set.
     * @param displayName The display name for the below-name scoreboard.
     */
    public void setDisplayName(Player player, String displayName) {
        Objective objective = scoreboard.getObjective(OBJECTIVE_NAME);
        objective.setDisplayName(displayName);
        player.setScoreboard(scoreboard);
    }

    /**
     * Adds a new team to the below-name scoreboard with a specified name and color.
     *
     * @param name  The name of the team to add.
     * @param color The color of the team.
     * @return The newly created Team object.
     */
    public Team addTeam(String name, ChatColor color) {
        Team team = scoreboard.registerNewTeam(name);
        team.setPrefix(color.toString());
        return team;
    }

    /**
     * Sets the team for a specified player on the below-name scoreboard.
     *
     * @param player The player whose team is to be set.
     * @param team   The team to set for the player.
     */
    public void setTeam(Player player, Team team) {
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    /**
     * Sets the below-name text for a specified player on the below-name scoreboard.
     *
     * @param player    The player whose below-name text is to be set.
     * @param belowName The text to display below the player's name.
     */
    public void setBelowName(Player player, String belowName) {
        Team team = getOrCreateTeam(player);
        team.setSuffix(" " + belowName);
        player.setScoreboard(scoreboard);
    }

    /**
     * Gets or creates a team for a player.
     * If a team for the player doesn't exist, it is created.
     *
     * @param player The player for whom the team is to be retrieved or created.
     * @return The Team associated with the player.
     */
    private Team getOrCreateTeam(Player player) {
        Team team = scoreboard.getEntryTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
            team.addEntry(player.getName());
        }
        return team;
    }
}
