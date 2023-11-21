package tk.airshipcraft.commonlib.commands;

import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

/**
 * CommandManager class. Forces the command to be registered to the CommandMap without the use of a plugin.yml.
 *
 * @author notzune
 * @version 0.0.1
 * @since 2022-07-28
 */
public abstract class CommandManager extends BukkitCommand {

    /**
     * CommandManager class. Forces the command to be registered to the CommandMap without the use of a plugin.yml.
     *
     * @param command     the name of the command to be registered
     * @param description the description of the command to show up in /help
     * @param permission  the permission required to execute this command
     * @param aliases     aliases that users type to access this command (i.e. /tp, /teleport)
     */
    protected CommandManager(@NotNull String command,
                             @NotNull String description,
                             @NotNull String permission,
                             @NotNull String[] aliases) {
        super(command);

        this.setDescription(description);
        this.setPermission(permission);
        this.setAliases(Arrays.asList(aliases));

        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap map = (CommandMap) field.get(Bukkit.getServer());
            map.register(command, this);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes the command.
     *
     * @param sender       Source object which is executing this command
     * @param commandLabel The alias of the command used
     * @param args         All arguments passed to the command, split via ' '
     */
    @SneakyThrows
    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        try {
            execute(sender, args);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * @param sender Source object which is executing this command
     * @param args   Command arguments, remember that /args (1) (2) (3) ...
     * @throws SQLException
     */
    public abstract void execute(CommandSender sender, String[] args) throws SQLException;

    /**
     * Lists the aliases for the command in the TabComplete list in-order to be iterated over.
     *
     * @param sender Source object which is executing this command
     * @param alias  the alias being used
     * @param args   All arguments passed to the command, split via ' '
     * @throws IllegalArgumentException
     */
    @Override
    public @NotNull List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) throws IllegalArgumentException {
        return onTabComplete(sender, args);
    }

    /**
     * Lists the aliases for the command in the TabComplete list in-order to be iterated over.
     * <p>
     * This here is an example for a way to loop through player names in tab complete
     * <p>
     * {@code
     * if (args.length == 2) {
     * *
     * *      List<String> names = new ArrayList<>();
     * *      *
     * *      for (Player player : Bukkit.getOnlinePlayers()) {
     * *      *      names.add(player.getName());
     * *      }
     * *      *
     * *      return StringUtil.copyPartialMatches(args[1], names, new ArrayList<>());
     * }
     * }
     *
     * @param sender Source object which is executing this command
     * @param args   All arguments passed to the command, split via ' '
     */
    public abstract List<String> onTabComplete(CommandSender sender, String[] args);
}
