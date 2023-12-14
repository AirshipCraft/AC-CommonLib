package tk.airshipcraft.commonlib.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.configuration.IPlayerPreference;
import tk.airshipcraft.commonlib.configuration.PlayerPref;
import tk.airshipcraft.commonlib.configuration.impl.PreferenceProcessor;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * PreferenceCommand class extends CommandManager to provide custom command handling
 * specifically for managing player preferences in the game.
 * This class handles the 'preference' command and its sub-commands to allow players to set and view their preferences.
 *
 * @author notzune
 * @version 1.0.0
 * @since 2023-11-20
 */
public class PreferenceCommand extends CommandManager {

    /**
     * Constructs a new PreferenceCommand.
     * Sets up the command with the name 'preference', a description, required permission, and command aliases.
     */
    public PreferenceCommand() {
        super("preference",
              "Manage player preferences",
              "commonlib.preference.manage",
              new String[]{"pref", "prefs"});
    }

    /**
     * Executes the 'preference' command when invoked by a player or console.
     * Supports sub-commands for setting and viewing player preferences.
     *
     * @param sender The entity that issued the command.
     * @param args   Arguments provided with the command.
     * @throws SQLException If a database access error occurs.
     */
    @Override
    public void execute(CommandSender sender, String[] args) throws SQLException {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be used by a player.");
            return;
        }

        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage("Usage: /preference <set/view> [preference] [value]");
            return;
        }

        String subCommand = args[0];
        switch (subCommand.toLowerCase()) {
            case "set":
                handleSetPreference(player, args);
                break;
            case "view":
                handleViewPreferences(player);
                break;
            default:
                player.sendMessage("Invalid sub-command. Use 'set' or 'view'.");
        }
    }

    /**
     * Handles the 'set' sub-command of the 'preference' command.
     * Allows players to set a specific preference to a new value.
     *
     * @param player The player executing the command.
     * @param args   Command arguments including the preference key and new value.
     */
    private void handleSetPreference(Player player, String[] args) {
        String preferenceKey = args[1];
        String newValue = args[2];

        IPlayerPreference playerPreference = CommonLib.getInstance().getPreferencesManager().getPreference(player);
        if (playerPreference == null) {
            player.sendMessage("No preferences found for your account.");
            return;
        }

        try {
            boolean updated = false;
            for (Field field : playerPreference.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(PlayerPref.class) && field.getName().equalsIgnoreCase(preferenceKey)) {
                    field.setAccessible(true);
                    // Convert newValue to the field's type and set it
                    Object convertedValue = PreferenceProcessor.convertStringToFieldType(newValue, field.getType());
                    field.set(playerPreference, convertedValue);
                    updated = true;
                    break;
                }
            }
            if (updated) {
                player.sendMessage("Preference '" + preferenceKey + "' updated to '" + newValue + "'.");
                CommonLib.getInstance().getPreferencesManager().savePreferences(player);
            } else {
                player.sendMessage("Preference key '" + preferenceKey + "' not found.");
            }
        } catch (IllegalAccessException e) {
            player.sendMessage("Error updating preference. Please contact an administrator.");
            e.printStackTrace(); // Log this error
        }
    }

    /**
     * Handles the 'view' sub-command of the 'preference' command.
     * Allows players to view their current set preferences.
     *
     * @param player The player executing the command.
     */
    private void handleViewPreferences(Player player) {
        // Retrieve and display the player's preferences
        IPlayerPreference playerPreference = CommonLib.getInstance().getPreferencesManager().getPreference(player);
        if (playerPreference != null) {
            player.sendMessage(playerPreference.describe(player));
        } else {
            player.sendMessage("You have no preferences set.");
        }
    }

    /**
     * Provides tab completion options for the 'preference' command.
     * Customizes the tab completion behavior based on the current argument level.
     *
     * @param sender The entity that sent the command.
     * @param args   Command arguments passed to the command.
     * @return A list of strings containing potential tab completion options.
     */
    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        // Implement tab completion logic
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("set");
            completions.add("view");
        }
        // Add more logic for deeper argument levels
        return completions;
    }
}
