package tk.airshipcraft.commonlib.gui.objects.impl;

import java.util.List;

/**
 * Interface defining the structure and basic functionalities of a team.
 * This interface allows different plugins to implement their own team logic and hierarchies.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public interface ITeam {

    /**
     * Gets the name of the team.
     *
     * @return The name of the team.
     */
    String getName();

    /**
     * Sets the name of the team.
     *
     * @param name The new name of the team.
     */
    void setName(String name);

    /**
     * Gets the child teams of this team.
     * Child teams can represent sub-groups or divisions within the team.
     *
     * @return A list of child teams.
     */
    List<ITeam> getChildTeams();

    /**
     * Adds a child team to this team.
     * This method is used to build hierarchical structures of teams.
     *
     * @param team The child team to be added.
     */
    void addChildTeam(ITeam team);

    /**
     * Removes a child team from this team.
     * This method allows for dynamic modification of the team's structure.
     *
     * @param team The child team to be removed.
     */
    void removeChildTeam(ITeam team);

    /**
     * Retrieves the parent team of this team.
     * The parent team is the team that this team is a sub-group or division of.
     *
     * @return The parent team, or null if this team is a top-level team.
     */
    ITeam getParentTeam();

    /**
     * Sets the parent team of this team.
     * This method is used to establish hierarchical relationships between teams.
     *
     * @param parent The parent team to set.
     */
    void setParentTeam(ITeam parent);
}
