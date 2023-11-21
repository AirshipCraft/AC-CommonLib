package tk.airshipcraft.commonlib.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tk.airshipcraft.commonlib.CommonLib;
import tk.airshipcraft.commonlib.configuration.impl.IPlayerPreference;
import tk.airshipcraft.commonlib.configuration.impl.PlayerPref;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static tk.airshipcraft.commonlib.configuration.PreferenceProcessor.convertStringToFieldType;

public class PreferenceCommand extends CommandManager {

    public PreferenceCommand() {
        super("preference",
                "Manage player preferences",
                "commonlib.preference.manage",
                new String[]{"pref", "prefs"});
    }

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
                    Object convertedValue = convertStringToFieldType(newValue, field.getType());
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

    private void handleViewPreferences(Player player) {
        // Retrieve and display the player's preferences
        IPlayerPreference playerPreference = CommonLib.getInstance().getPreferencesManager().getPreference(player);
        if (playerPreference != null) {
            player.sendMessage(playerPreference.describe(player));
        } else {
            player.sendMessage("You have no preferences set.");
        }
    }

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
