package tk.airshipcraft.commonlib.Objects;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

/**
 * A utility class for customizing the belowname scoreboard in PaperMC.
 */
public class BelowName {

    private ScoreboardManager manager;
    private Scoreboard scoreboard;

    /**
     * Constructs a new Belowname instance with the specified ScoreboardManager.
     * @param manager the ScoreboardManager to use for creating a new Scoreboard
     */
    public BelowName(ScoreboardManager manager) {
        this.manager = manager;
        this.scoreboard = manager.getNewScoreboard();
    }

    /**
     * Sets the display name of the belowname scoreboard for the specified player.
     * @param player the player whose scoreboard display name will be set
     * @param displayName the new display name for the belowname scoreboard
     */
    public void setDisplayName(Player player, String displayName) {
        Objective objective = scoreboard.getObjective(DisplaySlot.BELOW_NAME);
        if (objective == null) {
            objective = scoreboard.registerNewObjective("Belowname", "dummy");
            objective.setDisplaySlot(DisplaySlot.BELOW_NAME);
        }
        objective.setDisplayName(displayName);
        player.setScoreboard(scoreboard);
    }

    /**
     * Adds a new team to the belowname scoreboard with the specified name and color.
     * @param name the name of the team to add
     * @param color the color of the team
     * @return the newly created Team object
     */
    public Team addTeam(String name, ChatColor color) {
        Team team = scoreboard.registerNewTeam(name);
        team.setPrefix(color.toString());
        return team;
    }

    /**
     * Sets the team for the specified player on the belowname scoreboard.
     * @param player the player whose team will be set
     * @param team the team to set for the player
     */
    public void setTeam(Player player, Team team) {
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
    }

    /**
     * Sets the belowname for the specified player on the belowname scoreboard.
     * @param player the player whose belowname will be set
     * @param belowname the new belowname for the player
     */
    public void setBelowname(Player player, String belowname) {
        Team team = scoreboard.getEntryTeam(player.getName());
        if (team == null) {
            team = scoreboard.registerNewTeam(player.getName());
            team.addEntry(player.getName());
        }
        team.setSuffix(" " + belowname);
        player.setScoreboard(scoreboard);
    }
}
