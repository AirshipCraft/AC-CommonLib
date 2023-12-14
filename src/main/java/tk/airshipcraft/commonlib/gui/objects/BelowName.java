package tk.airshipcraft.commonlib.gui.objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * A utility class for managing and customizing the below-name scoreboard in Minecraft.
 * This class facilitates the creation and management of scoreboards that display information below player names,
 * such as health, scores, or custom text. It provides methods to set up and modify these displays per player.
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
     * Initializes a new scoreboard and sets up the objective for below-name display.
     *
     * @param manager The ScoreboardManager used for creating and managing scoreboards.
     */
    public BelowName(ScoreboardManager manager) {
        this.manager = manager;
        this.scoreboard = manager.getNewScoreboard();
        setupObjective();  // Initialize the objective for below-name display.
    }

    /**
     * Sets up the objective for the below-name display on the scoreboard.
     * If the objective does not already exist, it is created and configured.
     */
    private void setupObjective() {
        Objective objective = scoreboard.getObjective(OBJECTIVE_NAME);
        if (objective == null) {
            objective = scoreboard.registerNewObjective(OBJECTIVE_NAME, "dummy", "BelowName");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
    }

    /**
     * Sets the display name of the below-name scoreboard for a specific player.
     * This display name appears below the player's name in the game.
     *
     * @param player      The player for whom the display name is to be set.
     * @param displayName The display name to be shown below the player's name.
     */
    public void setDisplayName(Player player, String displayName) {
        Objective objective = scoreboard.getObjective(OBJECTIVE_NAME);
        objective.setDisplayName(displayName);
        player.setScoreboard(scoreboard);
    }

    /**
     * Adds a new team to the scoreboard with a specified name and color.
     * This team can be used to group players and customize their below-name displays.
     *
     * @param name  The name of the team to be added.
     * @param color The color to be associated with the team, using ChatColor.
     * @return The newly created Team object.
     */
    public Team addTeam(String name, ChatColor color) {
        Team team = scoreboard.registerNewTeam(name);
        team.setPrefix(color.toString());
        return team;
    }

    /**
     * Assigns a player to a specific team on the below-name scoreboard.
     * This can be used to group players and apply common display settings.
     *
     * @param player The player to be added to the team.
     * @param team   The team to which the player is to be added.
     */
    public void setTeam(Player player, Team team) {
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    /**
     * Sets a custom text to display below the name of a specific player.
     * This text appears below the player's name and can be used for various purposes, such as showing scores or statuses.
     *
     * @param player    The player for whom the below-name text is to be set.
     * @param belowName The custom text to display below the player's name.
     */
    public void setBelowName(Player player, String belowName) {
        Team team = getOrCreateTeam(player);
        team.setSuffix(" " + belowName);
        player.setScoreboard(scoreboard);
    }

    /**
     * Retrieves or creates a team for a given player on the scoreboard.
     * If no team exists for the player, a new team is created and associated with them.
     *
     * @param player The player for whom the team is to be retrieved or created.
     * @return The Team object associated with the player.
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
