package tk.airshipcraft.commonlib.gui.objects;

import tk.airshipcraft.commonlib.gui.objects.impl.ITeam;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages teams across different plugins, providing a common interface for team operations.
 * This class allows for registering, retrieving, and managing teams of different types.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class TeamManager {

    private final Map<String, ITeam> teams = new HashMap<>();

    /**
     * Registers a new team with the manager.
     * If a team with the same name already exists, it will be replaced.
     *
     * @param team The team to register.
     */
    public void registerTeam(ITeam team) {
        teams.put(team.getName(), team);
    }

    /**
     * Retrieves a team by its name.
     *
     * @param name The name of the team to retrieve.
     * @return The team with the given name, or null if no such team exists.
     */
    public ITeam getTeam(String name) {
        return teams.get(name);
    }

    /**
     * Unregisters a team from the manager.
     *
     * @param name The name of the team to unregister.
     */
    public void unregisterTeam(String name) {
        teams.remove(name);
    }

    /**
     * Sets the parent team for a given team.
     * This method establishes a hierarchical relationship between two teams.
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
