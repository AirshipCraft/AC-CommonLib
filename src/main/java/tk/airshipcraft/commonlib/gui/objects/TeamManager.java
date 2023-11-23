package tk.airshipcraft.commonlib.gui.objects;

import tk.airshipcraft.commonlib.gui.objects.impl.ITeam;

import java.util.HashMap;
import java.util.Map;

/**
 * Provides a centralized management system for teams across different plugins.
 * This class facilitates the registration, retrieval, and overall management of teams,
 * offering a common interface for various team operations.
 * It allows plugins to interact with a unified team structure, simplifying team-related functionalities.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class TeamManager {

    private final Map<String, ITeam> teams = new HashMap<>();

    /**
     * Registers a new team with the manager.
     * If a team with the same name already exists, it will be replaced with the new team.
     *
     * @param team The ITeam instance representing the team to register.
     */
    public void registerTeam(ITeam team) {
        teams.put(team.getName(), team);
    }

    /**
     * Retrieves a team by its name.
     * This method allows for quick access to team information based on the team's name.
     *
     * @param name The name of the team to retrieve.
     * @return The ITeam instance representing the team with the given name, or null if no such team exists.
     */
    public ITeam getTeam(String name) {
        return teams.get(name);
    }

    /**
     * Unregisters a team from the manager.
     * This method removes the team from the centralized team management system.
     *
     * @param name The name of the team to unregister.
     */
    public void unregisterTeam(String name) {
        teams.remove(name);
    }

    /**
     * Sets the parent team for a given team, establishing a hierarchical relationship.
     * This method is used to create structured team hierarchies, such as sub-teams or divisions.
     *
     * @param childName  The name of the child team.
     * @param parentName The name of the parent team.
     */
    public void setParentTeam(String childName, String parentName) {
        ITeam child = teams.get(childName);
        ITeam parent = teams.get(parentName);

        if (child != null && parent != null) {
            child.setParentTeam(parent);
            parent.addChildTeam(child);
        }
    }
}
