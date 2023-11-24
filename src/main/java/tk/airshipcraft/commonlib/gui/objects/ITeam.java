package tk.airshipcraft.commonlib.gui.objects;

import java.util.List;

/**
 * Interface for defining the structure and functionality of a team in a plugin environment.
 * This interface provides a standard way to manage teams, including their names, hierarchy, and relationships.
 * Implementing this interface allows different plugins to create and manage their own team logic, structures, and hierarchies.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public interface ITeam {

    /**
     * Retrieves the name of the team.
     * This method should return the current name under which the team is recognized.
     *
     * @return The name of the team as a String.
     */
    String getName();

    /**
     * Sets the name of the team.
     * This method is used to change the team's name.
     *
     * @param name The new name of the team as a String.
     */
    void setName(String name);

    /**
     * Gets a list of child teams associated with this team.
     * Child teams represent sub-groups or divisions within the larger team structure.
     *
     * @return A list of ITeam instances representing the child teams.
     */
    List<ITeam> getChildTeams();

    /**
     * Adds a child team to this team.
     * This method is used to expand the team hierarchy by adding a new sub-team.
     *
     * @param team The ITeam instance representing the child team to be added.
     */
    void addChildTeam(ITeam team);

    /**
     * Removes a specific child team from this team.
     * This method allows for the dynamic reorganization or simplification of the team structure.
     *
     * @param team The ITeam instance representing the child team to be removed.
     */
    void removeChildTeam(ITeam team);

    /**
     * Retrieves the parent team of this team, if any.
     * The parent team is the higher-level team to which this team belongs.
     * A return value of null indicates that this team is at the top of its hierarchy.
     *
     * @return The ITeam instance representing the parent team, or null if there is no parent.
     */
    ITeam getParentTeam();

    /**
     * Sets the parent team for this team.
     * This method establishes or updates the hierarchical relationship of this team within a larger team structure.
     *
     * @param parent The ITeam instance representing the new parent team.
     */
    void setParentTeam(ITeam parent);
}
